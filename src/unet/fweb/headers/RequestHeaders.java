package unet.fweb.headers;

import unet.fweb.server.WebSocket;

import java.io.IOException;
import java.io.InputStream;

public class RequestHeaders extends Headers {

    //=========================================
    //| REQUEST_TYPE | LOCATION | STATUS_CODE |
    //=========================================

    public static final int HTTP_HEADER_SIZE = 8190;
    public static final byte[] HOST_KEY = new byte[]{ 'H', 'o', 's', 't' };

    private GetRequests requestGet;

    private RequestType requestType;
    private byte[] location;
    private Protocol protocol;

    public RequestHeaders(GetRequests requestGet){
        this.requestGet = requestGet;
    }

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
                                    location = new byte[j-s];
                                    System.arraycopy(b, s, location, 0, j-s);
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
                                    requestGet.add(k, null);
                                    x = j+1;
                                }
                                break;

                            case 2:
                                if(b[j] == '&'){
                                    byte[] v = new byte[j-x];
                                    System.arraycopy(b, x, v, 0, j-x);
                                    requestGet.add(k, v);
                                    z--;
                                    x = j+1;

                                    //ADD KEY VALUE PAIR
                                }
                                break;
                        }
                    }

                    switch(z){
                        case 0:
                            location = new byte[i-s];
                            System.arraycopy(b, s, location, 0, i-s);
                            break;

                        case 1:
                            k = new byte[i-x];
                            System.arraycopy(b, x, k, 0, i-x);
                            requestGet.add(k, null);
                            break;

                        case 2:
                            byte[] v = new byte[i-x];
                            System.arraycopy(b, x, v, 0, i-x);
                            requestGet.add(k, v);
                            break;
                    }

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
