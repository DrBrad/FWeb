package unet.fweb.server;

import java.io.OutputStream;

public class GetEvent extends Event {

    public GetEvent(WebSocket socket){
        super(socket);
    }

    public OutputStream getOutputStream(){
        return web.out;
    }
}
