package unet.fweb.headers;

import java.util.HashMap;
import java.util.Map;

public class Headers {

    protected Map<BytesKey, byte[]> h;

    public Headers(){
        h = new HashMap<>();
    }

    public void add(String key, String var){
        h.put(new BytesKey(key.getBytes()), var.getBytes());
    }

    public void add(byte[] key, byte[] var){
        h.put(new BytesKey(key), var);
    }

    public String get(String key){
        return new String(h.get(new BytesKey(key.getBytes())));
    }

    public String get(byte[] key){
        return new String(h.get(new BytesKey(key)));
    }

    public boolean contains(String key){
        return h.containsKey(new BytesKey(key.getBytes()));
    }

    public boolean contains(byte[] key){
        return h.containsKey(new BytesKey(key));
    }

    public void remove(String key){
        h.remove(new BytesKey(key.getBytes()));
    }

    public void remove(byte[] key){
        h.remove(new BytesKey(key));
    }

    public Map<String, String> getList(){
        Map<String, String> m = new HashMap<>();
        for(BytesKey k : h.keySet()){
            m.put(new String(k.getBytes()), new String(h.get(k)));
        }
        return m;
    }

    public int size(){
        return h.size();
    }
}
