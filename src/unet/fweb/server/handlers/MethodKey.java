package unet.fweb.server.handlers;

public class MethodKey {

    private String host, location;

    public MethodKey(String host, String location){
        this.host = host;
        this.location = location;
    }

    public String getHost(){
        return host;
    }

    public String getLocation(){
        return location;
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
