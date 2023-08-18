package unet.fweb.headers;

import java.util.Arrays;

public enum RequestType {

    GET(new byte[]{ 'G', 'E', 'T' }),
    POST(new byte[]{ 'P', 'O', 'S', 'T' }),
    PUT(new byte[]{ 'P', 'U', 'T' }),
    HEAD(new byte[]{ 'H', 'E', 'A', 'D' });

    private byte[] name;

    RequestType(byte[] name){
        this.name = name;
    }

    public String value(){
        return new String(name);
    }

    public byte[] byteValue(){
        return name;
    }

    public static RequestType getByValue(byte[] name){
        for(RequestType p : RequestType.values()){
            if(Arrays.equals(p.name, name)){
                return p;
            }
        }
        throw new IllegalArgumentException("Protocol is unknown: "+name);
    }
}
