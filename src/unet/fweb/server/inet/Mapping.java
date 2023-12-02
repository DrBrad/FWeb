package unet.fweb.server.inet;

import unet.fweb.headers.RequestType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {

    String location() default "/";
    RequestType type() default RequestType.GET;
}
