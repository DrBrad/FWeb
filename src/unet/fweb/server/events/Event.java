package unet.fweb.server.events;

import unet.fweb.cookies.Cookie;
import unet.fweb.headers.GetRequests;
import unet.fweb.headers.RequestHeaders;
import unet.fweb.headers.ResponseHeaders;
import unet.fweb.server.WebSocket;
import unet.fweb.server.io.WebOutputStream;
import unet.fweb.sessions.Session;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Event {

    protected WebSocket web;

    public Event(WebSocket socket){
        this.web = socket;
    }

    public List<Cookie> getCookies(){
        return web.requestCookies;
    }

    public void setCookie(String name, String var){
        web.responseCookies.add(new Cookie(name, var));
    }

    public void setCookie(byte[] name, byte[] var){
        web.responseCookies.add(new Cookie(name, var));
    }

    public Session getSession(){
        return web.session;
    }

    //public int getPort(){
    //    return web.socket.getPort();
    //}

    public RequestHeaders getRequestHeaders(){
        return web.requestHeaders;
    }

    public ResponseHeaders getResponseHeaders(){
        return web.responseHeaders;
    }

    public GetRequests getRequestGET(){
        return web.requestGet;
    }

    public WebOutputStream getOutputStream(){
        return web.out;
    }
}
