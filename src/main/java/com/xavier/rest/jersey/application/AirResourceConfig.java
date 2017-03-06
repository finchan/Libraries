package com.xavier.rest.jersey.application;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;
/**
 * Created by Xavier on 2017-02-27.
 */

@ApplicationPath("/rest/jersey/webapi3/*")
//Don't need to config this Class in web.xml, instance will be automatically generated
public class AirResourceConfig extends ResourceConfig{
    public AirResourceConfig() {
        packages("com.xavier.rest.jersey.resource");
    }
}
