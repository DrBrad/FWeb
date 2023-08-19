package unet.fweb.server.io;

import unet.fweb.cookies.Cookie;
import unet.fweb.headers.StatusCode;
import unet.fweb.server.WebSocket;

import java.io.IOException;
import java.io.OutputStream;

import static unet.fweb.cookies.Cookie.SET_COOKIE_BYTES;

public class WebOutputStream extends OutputStream {

    private WebSocket web;
    private boolean sentHeaders;
    private OutputStream out;

    public WebOutputStream(WebSocket web, OutputStream out)throws IOException {
        this.web = web;
        this.out = out;
    }

    @Override
    public void write(int b)throws IOException {
        if(!sentHeaders){
            writeHeaders();
        }
        out.write(b);
    }

    @Override
    public void write(byte[] buf)throws IOException {
        if(!sentHeaders){
            writeHeaders();
        }
        out.write(buf);
    }

    @Override
    public void write(byte[] buf, int off, int len)throws IOException {
        if(!sentHeaders){
            writeHeaders();
        }
        out.write(buf, off, len);
    }

    @Override
    public void flush()throws IOException {
        out.flush();
    }

    @Override
    public void close()throws IOException {
        out.close();
    }

    public boolean hasSentHeaders(){
        return sentHeaders;
    }

    public void writeError(StatusCode statusCode)throws IOException {
        web.responseHeaders.setStatusCode(statusCode);

        if(!web.responseCookies.isEmpty()){
            web.responseHeaders.add(SET_COOKIE_BYTES, Cookie.searialize(web.responseCookies));
        }
        web.responseHeaders.write(out);

        out.flush();
        out.close();
    }

    public void writeHeaders()throws IOException {
        if(sentHeaders){
            throw new IllegalArgumentException("Headers have already been sent.");
        }

        if(!web.responseCookies.isEmpty()){
            web.responseHeaders.add(SET_COOKIE_BYTES, Cookie.searialize(web.responseCookies));
        }
        web.responseHeaders.write(out);
        out.flush();

        sentHeaders = true;
    }
}
