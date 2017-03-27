package com.xavier.rest.jersey.resource.interf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Xavier on 2017/3/27.
 */
@Path("book")
public interface BookResourceInterface {
    @GET
    String getWeight( );
}
