package unet.fweb.headers;

import java.util.HashMap;
import java.util.Map;

public class GetRequests {

    protected Map<BytesKey, byte[]> g;

    public GetRequests(){
        g = new HashMap<>();
    }

    public void add(String key, String var){
        g.put(new BytesKey(key.getBytes()), var.getBytes());
    }

    public void add(byte[] key, byte[] var){
        g.put(new BytesKey(key), var);
    }

    public String get(String key){
        return new String(g.get(new BytesKey(key.getBytes())));
    }

    public String get(byte[] key){
        return new String(g.get(new BytesKey(key)));
    }

    public boolean contains(String key){
        return g.containsKey(new BytesKey(key.getBytes()));
    }

    public boolean contains(byte[] key){
        return g.containsKey(new BytesKey(key));
    }

    public void remove(String key){
        g.remove(new BytesKey(key.getBytes()));
    }

    public void remove(byte[] key){
        g.remove(new BytesKey(key));
    }

    public Map<String, String> getList(){
        Map<String, String> m = new HashMap<>();
        for(BytesKey k : g.keySet()){
            m.put(new String(k.getBytes()), new String(g.get(k)));
        }
        return m;
    }

    public int size(){
        return g.size();
    }
}
