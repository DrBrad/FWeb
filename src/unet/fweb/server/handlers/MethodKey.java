package unet.fweb.server.handlers;

import unet.fweb.headers.RequestType;

public class MethodKey {

    private String host, location;
    private RequestType type;

    public MethodKey(String host, String location, RequestType type){
        this.host = host;
        this.location = location;
        this.type = type;
    }

    public String getHost(){
        return host;
    }

    public String getLocation(){
        return location;
    }

    public RequestType getType(){
        return type;
    }

    @Override
    public int hashCode(){
        return host.hashCode()+location.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        if(o == null || getClass() != o.getClass()){
            return false;
        }

        MethodKey methodKey = (MethodKey) o;
        return hashCode() == methodKey.hashCode();
    }
}
