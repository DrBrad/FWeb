package unet.fweb.server.events;

import unet.fweb.server.WebSocket;

public class HeadEvent extends Event {

    public HeadEvent(WebSocket socket){
        super(socket);
    }
}
