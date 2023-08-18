package unet.fweb;

import unet.fweb.pages.Index;
import unet.fweb.pages.Test;
import unet.fweb.server.WebServer;

import java.io.IOException;

public class TestServer {

    //WE NEED TO PARSE COOKIES
    //KILL SESSIONS AFTER X TIME - GARBAGE COLLECT
    //ONLY USE BYTES - NO STRINGS...

    public static void main(String[] args){
        WebServer web = new WebServer(8080);
        web.registerController(Index.class);
        web.registerController(Test.class);

        new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    web.start();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
