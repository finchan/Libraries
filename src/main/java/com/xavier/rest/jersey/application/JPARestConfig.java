package com.xavier.rest.jersey.application;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by Xavier on 2017-02-28.
 */
@ApplicationPath("/rest/jersey/bookjpa/*")
public class JPARestConfig extends ResourceConfig{
    public JPARestConfig() {
        packages("com.xavier.rest.jersey.resource");
    }
}
