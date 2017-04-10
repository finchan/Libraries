package com.xavier.rest.jersey.resource.interf;

import com.xavier.rest.jersey.domain.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Xavier on 2017/3/27.
 */
@Path("book")
public interface BookResourceInterface {
    @GET
    String getWeight( );

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_XML)
    String newBook(Book book);

    @DELETE
    void delete(@QueryParam("bookId") final long bookId);

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    Book createBook(Book book);
}
