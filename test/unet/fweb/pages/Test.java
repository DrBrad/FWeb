package unet.fweb.pages;

import unet.fweb.mimes.MimeType;
import unet.fweb.server.events.GetEvent;
import unet.fweb.server.inet.Controller;
import unet.fweb.server.inet.GetMapping;

import java.io.*;

import static unet.fweb.headers.ResponseHeaders.CONTENT_TYPE;

@Controller(host = "127.0.0.1:8080")
public class Test {

    @GetMapping(location = "/video")
    public void onResponse(GetEvent event)throws IOException {
        /*
        if(event.getSession().contains("HELLO")){
            event.getOutputStream().write(((String) event.getSession().get("HELLO")).getBytes());
        }else{
            event.getSession().add("HELLO", "NEW");
        }

        System.out.println("TEST FILE");
        event.getOutputStream().write("TEST FILE BLA BLA".getBytes());
        */

        File f = new File("file.mp4");

        event.getResponseHeaders().add(CONTENT_TYPE, MimeType.MP4.byteValue());
        InputStream in = new FileInputStream(f);
        event.getOutputStream().write(in.read());
    }
}
