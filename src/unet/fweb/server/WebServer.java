package unet.fweb.server;

import unet.fweb.server.handlers.MethodKey;
import unet.fweb.server.inet.Controller;
import unet.fweb.server.inet.GetMapping;
import unet.fweb.server.inet.PostMapping;
import unet.fweb.sessions.SessionFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static unet.fweb.sessions.SessionFactory.SESSION_EVICTION_TIME;

public class WebServer {

    private int port;
    private Executor executor;
    private Timer timer;
    private ServerSocket server;
    private Socket socket;

    protected SessionFactory sessionFactory;

    protected Map<MethodKey, Method> getMethods, postMethods;

    public WebServer(){
        this(0);
    }

    public WebServer(int port){
        this.port = port;
        executor = Executors.newFixedThreadPool(10);
        getMethods = new HashMap<>();
        postMethods = new HashMap<>();

        sessionFactory = new SessionFactory();
    }

    public void start()throws IOException {
        timer = new Timer(true);
        timer.schedule(new GarbageCollector(this), 0, SESSION_EVICTION_TIME);

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

    public int getPort(){
        return server.getLocalPort();
    }

    public void registerController(Class<?> c){
        //CHANGE KEY TO CUSTOM CLASS USING LOCATION AND HOST...
        if(!c.isAnnotationPresent(Controller.class)){
            throw new IllegalArgumentException("Class doesn't a valid controller");
        }

        String host = c.getAnnotation(Controller.class).host();

        for(Method m : c.getDeclaredMethods()){
            if(m.isAnnotationPresent(GetMapping.class)){
                getMethods.put(new MethodKey(host, m.getAnnotation(GetMapping.class).location()), m);
            }
        }

        for(Method m : c.getDeclaredMethods()){
            if(m.isAnnotationPresent(PostMapping.class)){
                postMethods.put(new MethodKey(host, m.getAnnotation(PostMapping.class).location()), m);
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
