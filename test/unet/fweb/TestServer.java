package unet.fweb;

import unet.fweb.pages.Index;
import unet.fweb.pages.Test;
import unet.fweb.server.WebServer;

import java.io.IOException;

public class TestServer {

    //FIX HEADER READING - READ AS IT COMES...
    //ALLOW FOR DOMAIN SYSTEM AND USE APPLICATION - SO WE CAN ALLOW FOR MULTIPLE SOCKETS EASILY...
    //PLANNED SYSTEM
    // <$ VARIABLE $>
    // <$ CODE $>
    //CACHE EVERYTHING BUT CODE AND VARS FOR EASY USE

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
