package unet.fweb.server;

import unet.fweb.cookies.Cookie;
import unet.fweb.headers.GetRequests;
import unet.fweb.headers.RequestHeaders;
import unet.fweb.headers.ResponseHeaders;
import unet.fweb.headers.StatusCode;
import unet.fweb.server.events.GetEvent;
import unet.fweb.server.events.PostEvent;
import unet.fweb.server.handlers.MethodKey;
import unet.fweb.server.io.WebOutputStream;
import unet.fweb.sessions.Session;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static unet.fweb.cookies.Cookie.COOKIE_BYTES;
import static unet.fweb.headers.RequestHeaders.HOST_KEY;
import static unet.fweb.sessions.SessionFactory.SESSION_BYTES;

public class WebSocket {

    private WebServer server;
    private Socket socket;
    public InputStream in;
    public WebOutputStream out;

    public RequestHeaders requestHeaders;
    public ResponseHeaders responseHeaders;
    public GetRequests requestGet;
    public List<Cookie> requestCookies, responseCookies;
    public Session session;

    public WebSocket(WebServer server, Socket socket){
        this.server = server;
        this.socket = socket;
        responseCookies = new ArrayList<>();
    }

    public void init(){
        try{
            in = socket.getInputStream();
            out = new WebOutputStream(this, socket.getOutputStream());

            requestGet = new GetRequests();
            requestHeaders = new RequestHeaders(requestGet);
            requestHeaders.read(in);

            if(requestHeaders.contains(COOKIE_BYTES)){
                requestCookies = Cookie.parse(requestHeaders.get(COOKIE_BYTES).getBytes());

                for(Cookie cookie : requestCookies){
                    if(Arrays.equals(cookie.getByteName(), SESSION_BYTES)){
                        UUID uuid = UUID.fromString(cookie.getVariable());
                        if(server.sessionFactory.contains(uuid)){
                            session = server.sessionFactory.get(UUID.fromString(cookie.getVariable()));
                            session.setSeen();
                        }
                        break;
                    }
                }
            }

            if(session == null){
                session = server.sessionFactory.create();
                responseCookies.add(new Cookie(SESSION_BYTES, session.getUUID().toString().getBytes()));
            }

            responseHeaders = new ResponseHeaders();

            if(!requestHeaders.contains(HOST_KEY)){
                out.writeError(StatusCode.BAD_REQUEST);
                return;
            }

            //PARSE GET AND POST AT THIS POINT...
            System.err.println(requestHeaders.getLocation());

            MethodKey k = new MethodKey(requestHeaders.get(HOST_KEY), requestHeaders.getLocation());

            switch(requestHeaders.getRequestType()){
                case POST:
                    if(server.postMethods.containsKey(k)){
                        responseHeaders.setStatusCode(StatusCode.OK);
                        Method m = server.postMethods.get(k);
                        Object c = m.getDeclaringClass().getConstructor().newInstance();
                        m.setAccessible(true);
                        m.invoke(c, new PostEvent(this));
                    }
                    break;

                case GET:
                    if(server.getMethods.containsKey(k)){
                        responseHeaders.setStatusCode(StatusCode.OK);
                        Method m = server.getMethods.get(k);
                        Object c = m.getDeclaringClass().getConstructor().newInstance();
                        m.setAccessible(true);
                        m.invoke(c, new GetEvent(this));
                    }
                    break;
            }

            if(!out.hasSentHeaders()){
                out.writeHeaders();
            }

            in.close();
            out.flush();
            out.close();
            socket.close();

        }catch(ReflectiveOperationException | IOException e){
            e.printStackTrace();
        }
    }
}
