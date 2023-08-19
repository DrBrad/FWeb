package unet.fweb.pages;

import unet.fweb.server.events.GetEvent;
import unet.fweb.server.inet.Controller;
import unet.fweb.server.inet.GetMapping;

import java.io.IOException;

@Controller(host = "127.0.0.1:8080")
public class Test {

    /*
    @WebEvent(location = "/")
    public void onRequest(Event event){
        System.out.println("REQUEST EVENT TRIGGERED");
    }
    */

    @GetMapping(location = "/test")
    public void onResponse(GetEvent event)throws IOException {
        if(event.getSession().contains("HELLO")){
            event.getOutputStream().write(((String) event.getSession().get("HELLO")).getBytes());
        }else{
            event.getSession().add("HELLO", "NEW");
        }

        System.out.println("TEST FILE");
        event.getOutputStream().write("TEST FILE BLA BLA".getBytes());
    }
}
