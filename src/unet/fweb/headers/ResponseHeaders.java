package unet.fweb.headers;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseHeaders extends Headers {

    //================================================
    //| PROTOCOL | STATUS_CODE | STATUS_CODE_MESSAGE |
    //================================================

    public static final byte[] CONTENT_TYPE = new byte[]{ 'C', 'o', 'n', 't', 'e', 'n', 't', 0x20, 'T', 'y', 'p', 'e' };
    public static final byte[] TEXT_HTML = new byte[]{ 't', 'e', 'x', 't', '/', 'h', 't', 'm', 'l' };

    private Protocol protocol = Protocol.HTTP11;
    private StatusCode statusCode = StatusCode.OK;

    public ResponseHeaders(){
        h.put(new BytesKey(CONTENT_TYPE), TEXT_HTML);
    }

    public void setProtocol(Protocol protocol){
        this.protocol = protocol;
    }

    public void setStatusCode(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public void write(OutputStream out)throws IOException {
        out.write(protocol.byteValue());
        out.write(0x20);
        out.write(statusCode.getValue());
        out.write(0x20);
        out.write(statusCode.getByteDescription());
        out.write(0x0D);
        out.write(0x0A);

        for(BytesKey k : h.keySet()){
            out.write(k.getBytes());
            out.write(':');
            out.write(0x20);
            out.write(h.get(k));
            out.write(0x0D);
            out.write(0x0A);
        }

        out.write(0x0D);
        out.write(0x0A);
        out.flush();
    }
}
