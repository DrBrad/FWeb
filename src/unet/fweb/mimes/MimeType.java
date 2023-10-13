package unet.fweb.mimes;

public enum MimeType {

    PNG("png", new byte[]{ 'i', 'm', 'a', 'g', 'e', '/', 'p', 'n', 'g' }),
    JPG("jpg", new byte[]{ 'i', 'm', 'a', 'g', 'e', '/', 'j', 'p', 'e', 'g' }),
    JPEG("jpeg", new byte[]{ 'i', 'm', 'a', 'g', 'e', '/', 'j', 'p', 'e', 'g' }),
    MP4("mp4", new byte[]{ 'v', 'i', 'd', 'e', 'o', '/', 'm', 'p', '4' });

    private String ext;
    private byte[] mime;

    MimeType(String ext, byte[] mime){
        this.ext = ext;
        this.mime = mime;
    }

    public String value(){
        return new String(mime);
    }

    public String getExtension(){
        return ext;
    }

    public byte[] byteValue(){
        return mime;
    }

    /*
    public static RequestType getByValue(byte[] name){
        for(RequestType p : RequestType.values()){
            if(Arrays.equals(p.name, name)){
                return p;
            }
        }
        throw new IllegalArgumentException("Protocol is unknown: "+name);
    }
    */
}
