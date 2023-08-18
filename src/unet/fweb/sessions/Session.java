package unet.fweb.sessions;

import java.util.HashMap;
import java.util.UUID;

public class Session {

    private UUID uuid;
    private HashMap<String, Object> v;

    public Session(UUID uuid){
        this.uuid = uuid;
        v = new HashMap<>();
    }

    public UUID getUUID(){
        return uuid;
    }

    public void add(String key, String var){
        v.put(key, var);
    }

    public void remove(String key){
        v.remove(key);
    }

    public Object get(String key){
        return v.get(key);
    }

    public boolean contains(String key){
        return v.containsKey(key);
    }

    public void clear(){
        v.clear();
    }

    @Override
    public int hashCode(){
        return v.hashCode();
    }

    @Override
    public boolean equals(Object o){
        return hashCode() == o.hashCode();
    }
}
