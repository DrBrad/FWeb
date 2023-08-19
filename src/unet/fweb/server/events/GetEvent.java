package unet.fweb.server.events;

import unet.fweb.server.WebSocket;

public class GetEvent extends Event {

    public GetEvent(WebSocket socket){
        super(socket);
    }
}
