package com.xavier.rest.jersey.resource;

import com.xavier.rest.jersey.domain.Books;
import com.xavier.rest.jersey.service.BookService;
import com.xavier.utilities.DI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Xavier on 2017/2/28.
 */

@Path("books")
public class BookResource {
    private static final Logger logger = Logger.getLogger(BookResource.class);
    @Autowired
    private BookService bookService;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Books getBooks( ) {
        final Books books = bookService.getBooks( );
        DI.debug(logger, books);
        return books;
    }
}
