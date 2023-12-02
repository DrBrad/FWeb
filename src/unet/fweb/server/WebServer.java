package unet.fweb.server;

import unet.fweb.server.handlers.MethodKey;
import unet.fweb.server.inet.Controller;
import unet.fweb.server.inet.Mapping;
import unet.fweb.sessions.SessionFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static unet.fweb.sessions.SessionFactory.SESSION_EVICTION_TIME;

public class WebServer {

    private int port;
    private Executor executor;
    private Timer timer;
    private ServerSocket server;
    private Socket socket;

    protected String host;
    protected SessionFactory sessionFactory;
    protected Map<MethodKey, Method> methods;

    public WebServer(){
        this(0);
    }

    public WebServer(int port){
        this.port = port;
        executor = Executors.newFixedThreadPool(10);
        methods = new HashMap();

        try{
            host = InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException e){
            System.err.println("Server failed to get default host address using: localhost instead");
            host = "localhost";
        }

        sessionFactory = new SessionFactory();
    }

    public void start()throws IOException {
        timer = new Timer(true);
        timer.schedule(new GarbageCollector(this), SESSION_EVICTION_TIME, SESSION_EVICTION_TIME);
        System.out.println("Garbage Collector started at interval: "+SESSION_EVICTION_TIME);

        server = new ServerSocket(port);
        System.out.println("Server started on port: "+server.getLocalPort());

        while((socket = server.accept()) != null){
            executor.execute(new Runnable(){
                @Override
                public void run(){
                    new WebSocket(WebServer.this, socket).init();
                }
            });
        }

        server.close();
    }

    public void setDefaultHost(String host){
        this.host = host;
    }

    public int getPort(){
        return server.getLocalPort();
    }

    public void registerController(Class<?> c){
        String host = c.isAnnotationPresent(Controller.class) ? c.getAnnotation(Controller.class).host() : this.host;

        for(Method m : c.getDeclaredMethods()){
            if(m.isAnnotationPresent(Mapping.class)){
                methods.put(new MethodKey(host, m.getAnnotation(Mapping.class).location(), m.getAnnotation(Mapping.class).type()), m);
                System.out.println("Server registered method: "+m.getName()+" on host: "+host);
            }
        }
    }

    public void stop()throws IOException {
        server.close();
        timer.cancel();
        timer.cancel();
        timer.purge();
    }
}
