package unet.fweb.pages;

import unet.fweb.server.GetEvent;
import unet.fweb.server.inet.Controller;
import unet.fweb.server.inet.GetMapping;

import java.io.IOException;

@Controller
public class Test {

    /*
    @WebEvent(location = "/")
    public void onRequest(Event event){
        System.out.println("REQUEST EVENT TRIGGERED");
    }
    */

    @GetMapping(location = "/test")
    public void onResponse(GetEvent event)throws IOException {
        System.out.println("TEST FILE");
        event.getOutputStream().write("TEST FILE BLA BLA".getBytes());
    }
}
