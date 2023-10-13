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
                    //byte[] n = new byte[i-s];
                    //System.arraycopy(b, s, n, 0, i-s);

                    //SPLIT THE LOCATION FOR GET...


                    byte[] k = null;
                    int z = 0, x = s;

                    for(int j = s; j < i; j++){
                        switch(z){
                            case 0:
                                if(b[j] == '?'){
                                    z++;
                                    x = j+1;
                                }
                                break;

                            case 1:
                                if(b[j] == '='){
                                    k = new byte[j-x];
                                    System.arraycopy(b, x, k, 0, j-x);
                                    z++;
                                    x = j+1;

                                }else if(b[j] == '&'){
                                    //SAVE KEY NO VALUE
                                    k = new byte[j-x];
                                    System.arraycopy(b, x, k, 0, j-x);
                                    System.out.println("KEY: "+new String(k));
                                    x = j+1;
                                }
                                break;

                            case 2:
                                if(b[j] == '&'){
                                    byte[] v = new byte[j-x];
                                    System.arraycopy(b, x, v, 0, j-x);
                                    z--;
                                    System.out.println("KEY: "+new String(k)+" VAL: "+new String(v));
                                    x = j+1;

                                    //ADD KEY VALUE PAIR
                                }
                                break;
                        }
                    }

                    switch(z){
                        case 1:
                            k = new byte[i-x];
                            System.arraycopy(b, x, k, 0, i-x);
                            System.out.println("KEY 1: "+new String(k));
                            break;

                        case 2:
                            byte[] v = new byte[i-x];
                            System.arraycopy(b, x, v, 0, i-x);
                            System.out.println("KEY 1: "+new String(k)+" VAL: "+new String(v));
                            break;
                    }

                    //IF CASE 2 SAVE KEY PAIR  IF CASE 1 SAVE KEY

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
