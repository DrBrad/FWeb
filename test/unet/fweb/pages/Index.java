package unet.fweb.pages;

import unet.fweb.server.events.PostEvent;
import unet.fweb.server.events.GetEvent;
import unet.fweb.server.inet.Controller;
import unet.fweb.server.inet.Mapping;

import java.io.IOException;

@Controller(host = "127.0.0.1:8080")
public class Index {

    public void onRequest(PostEvent event)throws IOException {
    }

    @Mapping(location = "/")
    public void onResponse(GetEvent event)throws IOException {
        System.out.println("INDEX FILE");
        event.getOutputStream().write("INDEX FILE BLAA BLA".getBytes());
    }
}
