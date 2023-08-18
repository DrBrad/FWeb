package unet.fweb;

import unet.fweb.pages.Index;
import unet.fweb.pages.Test;
import unet.fweb.server.WebServer;

import java.io.IOException;

public class TestServer {

    //FIX HEADER READING
    //CHANGE STATUS CODES TO BYTE FORM

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
