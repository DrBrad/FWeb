package unet.fweb.cookies;

import java.util.ArrayList;
import java.util.List;

public class Cookie {

    public static final byte[] COOKIE_BYTES = new byte[]{ 'C', 'o', 'o', 'k', 'i', 'e' };
    public static final byte[] SET_COOKIE_BYTES = new byte[]{ 'S', 'e', 't', '-', 'C', 'o', 'o', 'k', 'i', 'e' };

    private byte[] name, var;

    public Cookie(byte[] name, byte[] var){
        this.name = name;
        this.var = var;
    }

    public Cookie(String name, String var){
        this.name = name.getBytes();
        this.var = var.getBytes();
    }

    public static List<Cookie> parse(byte[] b){
        List<Cookie>l = new ArrayList<>();
        int s = 0, p = 0, t = 0;
        byte[] k = null;

        while(p < b.length){
            switch(t){
                case 0:
                    if(b[p] == '='){
                        k = new byte[p-s];
                        System.arraycopy(b, s, k, 0, k.length);
                        s = p+1;
                        t = 1;
                    }
                    break;

                case 1:
                    if(b[p] == ';'){
                        byte[] v = new byte[p-s];
                        System.arraycopy(b, s, v, 0, v.length);
                        l.add(new Cookie(k, v));
                        s = p+1;
                        t = 2;
                    }
                    break;

                case 2:
                    if(b[p] != 0x20 && b[p] != '\t'){
                        s++;
                        t = 0;
                    }
                    break;
            }

            p++;
        }

        if(p > s){
            byte[] v = new byte[p-s];
            System.arraycopy(b, s, v, 0, v.length);
            l.add(new Cookie(k, v));
        }

        return l;
    }

    public static byte[] searialize(List<Cookie> c){
        int l = 0;
        for(Cookie s : c){
            l += s.size();
        }

        byte[] b = new byte[l+c.size()-2];
        int p = 0;
        for(int i = 0; i < c.size()-1; i++){
            Cookie s = c.get(i);
            System.arraycopy(s.name, 0, b, p, s.name.length);
            p += s.name.length+1;
            b[p-1] = '=';
            System.arraycopy(s.var, 0, b, p, s.var.length);
            p += s.var.length+1;
            b[p-1] = ';';
            b[p] = 0x20;
            p++;
        }

        Cookie s = c.get(c.size()-1);
        System.arraycopy(s.name, 0, b, p, s.name.length);
        p += s.name.length+1;
        b[p-1] = '=';
        System.arraycopy(s.var, 0, b, p, s.var.length);

        return b;
    }

    public void setName(String name){
        this.name = name.getBytes();
    }

    public void setName(byte[] name){
        this.name = name;
    }

    public String getName(){
        return new String(name);
    }

    public byte[] getByteName(){
        return name;
    }

    public void setVariable(String var){
        this.var = var.getBytes();
    }

    public void setVariable(byte[] var){
        this.var = var;
    }

    public String getVariable(){
        return new String(var);
    }

    public int size(){
        return name.length+var.length+2;
    }
}
