package unet.fweb.headers;

import java.util.Arrays;

public enum Protocol {

    HTTP1(new byte[]{ 'H', 'T', 'T', 'P', '/', '1', '.', '0' }),
    HTTP11(new byte[]{ 'H', 'T', 'T', 'P', '/', '1', '.', '1' }),
    HTTP12(new byte[]{ 'H', 'T', 'T', 'P', '/', '1', '.', '2' }),
    HTTP2(new byte[]{ 'H', 'T', 'T', 'P', '/', '2' }),
    HTTP3(new byte[]{ 'H', 'T', 'T', 'P', '/', '3' });

    private byte[] name;

    Protocol(byte[] name){
        this.name = name;
    }

    public String value(){
        return new String(name);
    }

    public byte[] byteValue(){
        return name;
    }

    public static Protocol getByValue(byte[] name){
        for(Protocol p : Protocol.values()){
            if(Arrays.equals(p.name, name)){
                return p;
            }
        }
        throw new IllegalArgumentException("Protocol is unknown: "+name);
    }
}
