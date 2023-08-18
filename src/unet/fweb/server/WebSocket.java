package unet.fweb.server;

import unet.fweb.cookies.Cookie;
import unet.fweb.headers.RequestHeaders;
import unet.fweb.headers.ResponseHeaders;
import unet.fweb.sessions.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static unet.fweb.cookies.Cookie.COOKIE_BYTES;
import static unet.fweb.cookies.Cookie.SET_COOKIE_BYTES;
import static unet.fweb.sessions.SessionFactory.SESSION_BYTES;

public class WebSocket {

    private WebServer server;
    protected Socket socket;
    protected InputStream in;
    protected OutputStream out;

    protected RequestHeaders requestHeaders;
    protected ResponseHeaders responseHeaders;
    protected List<Cookie> requestCookies, responseCookies;
    protected Session session;

    public WebSocket(WebServer server, Socket socket){
        this.server = server;
        this.socket = socket;
        responseCookies = new ArrayList<>();
    }

    public void init(){
        try{
            in = socket.getInputStream();
            out = socket.getOutputStream();

            requestHeaders = new RequestHeaders();
            requestHeaders.read(in);

            if(requestHeaders.contains(COOKIE_BYTES)){
                requestCookies = Cookie.parse(requestHeaders.get(COOKIE_BYTES).getBytes());

                for(Cookie cookie : requestCookies){
                    if(Arrays.equals(cookie.getByteName(), SESSION_BYTES)){
                        session = server.sessionFactory.get(UUID.fromString(cookie.getVariable()));
                        break;
                    }
                }
            }

            if(session == null){
                session = server.sessionFactory.create();
                responseCookies.add(new Cookie(SESSION_BYTES, session.getUUID().toString().getBytes()));
            }

            Object c = null;

            if(server.postMethods.containsKey(requestHeaders.getLocation())){
                Method m = server.postMethods.get(requestHeaders.getLocation());
                c = m.getDeclaringClass().getConstructor().newInstance();
                m.setAccessible(true);
                m.invoke(c, new PostEvent(this));
            }

            responseHeaders = new ResponseHeaders();
            responseHeaders.add("Content-Type", "text/html");
            if(!responseCookies.isEmpty()){
                responseHeaders.add(SET_COOKIE_BYTES, Cookie.searialize(responseCookies));
            }
            responseHeaders.write(out);

            //SENT HEADERS SEND RESPONSE...

            if(server.getMethods.containsKey(requestHeaders.getLocation())){
                Method m = server.getMethods.get(requestHeaders.getLocation());

                if(c != null && m.getDeclaringClass().equals(c.getClass())){
                    m.setAccessible(true);
                    m.invoke(c, new GetEvent(this));

                }else{
                    c = m.getDeclaringClass().getConstructor().newInstance();
                    m.setAccessible(true);
                    m.invoke(c, new GetEvent(this));
                }
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
