package unet.fweb.headers;

import java.util.Arrays;

public class BytesKey {

    private byte[] b;

    public BytesKey(byte[] b){
        this.b = b;
    }

    public byte[] getBytes(){
        return b;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        if(o == null || getClass() != o.getClass()){
            return false;
        }

        BytesKey bytesKey = (BytesKey) o;
        return Arrays.equals(b, bytesKey.b);
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(b);
    }
}
