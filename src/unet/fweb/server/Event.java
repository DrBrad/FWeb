package unet.fweb.server;

import unet.fweb.cookies.Cookie;
import unet.fweb.headers.RequestHeaders;
import unet.fweb.headers.ResponseHeaders;
import unet.fweb.sessions.Session;

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

    public int getPort(){
        return web.socket.getPort();
    }

    public RequestHeaders getRequestHeaders(){
        return web.requestHeaders;
    }

    public ResponseHeaders getResponseHeaders(){
        return web.responseHeaders;
    }
}
