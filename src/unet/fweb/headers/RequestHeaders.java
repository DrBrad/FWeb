package unet.fweb.headers;

import java.io.IOException;
import java.io.InputStream;

public class RequestHeaders extends Headers {

    //=========================================
    //| REQUEST_TYPE | LOCATION | STATUS_CODE |
    //=========================================

    public static final int HTTP_HEADER_SIZE = 8190;
    public static final byte[] HOST_KEY = new byte[]{ 'H', 'o', 's', 't' };

    private RequestType requestType;
    private byte[] location;
    private Protocol protocol;

    public void read(InputStream in)throws IOException {
        byte[] b = new byte[HTTP_HEADER_SIZE];
        int l = in.read(b);

        //DONT READ BASED OFF OF HEADERS...
        //READ BASED OFF OF \r\n\r\n

        int t = 1;
        int s = 0, i = 0;
        for(; i < l; i++){
            if(b[i] == 0x20){
                if(t == 0){
                    byte[] n = new byte[i-s];
                    System.arraycopy(b, s, n, 0, i-s);
                    location = n;

                }else{
                    byte[] n = new byte[i-s];
                    System.arraycopy(b, s, n, 0, i-s);
                    requestType = RequestType.getByValue(n);
                    t = 0;
                }
                s = i+1;
                continue;
            }

            if(b[i] == 0x0D && b[i+1] == 0x0A){ //  \r\n CHECK
                byte[] n = new byte[i-s];
                System.arraycopy(b, s, n, 0, i-s);
                protocol = Protocol.getByValue(n);
                i += 2;
                break;
            }
        }

        //PARSE HEADERS
        s = i;
        byte[] k = null;
        for(; i < b.length; i++){
            switch(t){
                case 0:
                    if(b[i] == 0x3A){ //  : CHECK
                        k = new byte[i-s];
                        System.arraycopy(b, s, k, 0, i-s);
                        t = 1;
                        i++;
                        s = i;
                    }
                    break;

                case 1:
                    if(b[i] != 0x20 && b[i] != '\t'){ //  SPACE / TAB CHECK
                        t = 2;
                        s = i;
                    }
                    break;

                case 2:
                    if(b[i] == 0x0D && b[i+1] == 0x0A){ //  \r\n CHECK
                        byte[] v = new byte[i-s];
                        System.arraycopy(b, s, v, 0, i-s);
                        h.put(new BytesKey(k), v);
                        t = 0;
                        i += 2;
                        s = i;
                    }
                    break;
            }
        }
    }

    public RequestType getRequestType(){
        return requestType;
    }

    public String getLocation(){
        return new String(location);
    }

    public byte[] getByteLocation(){
        return location;
    }

    public Protocol getProtocol(){
        return protocol;
    }
}
