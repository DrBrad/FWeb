package unet.fweb.server.events;

import unet.fweb.server.WebSocket;

import java.io.InputStream;

public class PutEvent extends Event {

    public PutEvent(WebSocket socket){
        super(socket);
    }

    public InputStream getInputStream(){
        return web.in;
    }
}
