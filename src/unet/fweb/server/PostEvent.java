package unet.fweb.server;

import java.io.InputStream;

public class PostEvent extends Event {

    public PostEvent(WebSocket socket){
        super(socket);
    }

    public InputStream getInputStream(){
        return web.in;
    }
}
