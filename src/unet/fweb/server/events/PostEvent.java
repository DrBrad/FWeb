package unet.fweb.server.events;

import unet.fweb.server.WebSocket;

import java.io.InputStream;

public class PostEvent extends Event {

    public PostEvent(WebSocket socket){
        super(socket);
    }

    public InputStream getInputStream(){
        return web.in;
    }
}
