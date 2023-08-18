package unet.fweb.pages;

import unet.fweb.server.PostEvent;
import unet.fweb.server.GetEvent;
import unet.fweb.server.inet.Controller;
import unet.fweb.server.inet.GetMapping;
import unet.fweb.server.inet.PostMapping;

import java.io.IOException;

@Controller
public class Index {

    @PostMapping(location = "/")
    public void onRequest(PostEvent event){
    }

    @GetMapping(location = "/")
    public void onResponse(GetEvent event)throws IOException {
        System.out.println("INDEX FILE");
        event.getOutputStream().write("INDEX FILE BLAA BLA".getBytes());
    }
}
