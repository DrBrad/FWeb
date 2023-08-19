package unet.fweb.server;

import java.util.TimerTask;

public class GarbageCollector extends TimerTask {

    private WebServer server;

    public GarbageCollector(WebServer server){
        this.server = server;
    }

    @Override
    public void run(){
        int size = server.sessionFactory.size();
        server.sessionFactory.evictOldSessions();
        System.out.println("[ GC ] : EVICTED ("+(size-server.sessionFactory.size())+") SESSIONS");
    }
}
