package unet.fweb.server;

import java.util.TimerTask;

public class GarbageCollector extends TimerTask {

    private WebServer server;

    public GarbageCollector(WebServer server){
        this.server = server;
    }

    @Override
    public void run(){
        server.sessionFactory.evictOldSessions();
    }
}
