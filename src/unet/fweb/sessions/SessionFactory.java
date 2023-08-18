package unet.fweb.sessions;

import java.util.*;

public class SessionFactory {

    public static final byte[] SESSION_BYTES = new byte[]{ 'F', 'S', 'S', 'I', 'D' };

    private Map<UUID, Session> s;

    public SessionFactory(){
        s = new HashMap<>();
    }

    public synchronized Session create(){
        Session c = new Session(UUID.randomUUID());
        s.put(c.getUUID(), c);
        return c;
    }

    public synchronized void remove(UUID uuid){
        s.remove(uuid);
    }

    public synchronized Session get(UUID uuid){
        return s.get(uuid);
    }

    public synchronized boolean contains(UUID uuid){
        return s.containsKey(uuid);
    }

    public synchronized void clear(){
        s.clear();
    }

    public int size(){
        return s.size();
    }
}
