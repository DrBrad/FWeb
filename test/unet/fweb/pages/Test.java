package unet.fweb.pages;

import unet.fweb.headers.StatusCode;
import unet.fweb.mimes.MimeType;
import unet.fweb.server.events.GetEvent;
import unet.fweb.server.inet.Controller;
import unet.fweb.server.inet.GetMapping;

import java.io.*;

import static unet.fweb.headers.ResponseHeaders.CONTENT_TYPE;

@Controller(host = "127.0.0.1:8080")
public class Test {

    @GetMapping(location = "/image")
    public void onResponse(GetEvent event)throws IOException {
        if(!event.getRequestGET().contains("id")){
            event.getResponseHeaders().setStatusCode(StatusCode.NOT_FOUND);
            event.getOutputStream().write("FILE DOESN'T EXIST".getBytes());
            return;
        }

        File f = new File("/home/brad/Downloads/"+event.getRequestGET().get("id"));

        event.getResponseHeaders().add(CONTENT_TYPE, MimeType.JPEG.byteValue());
        InputStream in = new FileInputStream(f);

        OutputStream out = event.getOutputStream();

        byte[] buf = new byte[4096];
        int len;
        while((len = in.read(buf)) > -1){
            out.write(buf, 0, len);
        }
        out.flush();
    }
}
