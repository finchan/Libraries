package com.xavier.rest.jersey.resource;

import com.xavier.rest.jersey.domain.Book;
import com.xavier.rest.jersey.domain.Books;
import com.xavier.rest.jersey.service.BookService;
import com.xavier.utilities.DI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
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

    @Path("/book")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getBookByQuery(@QueryParam("id") final Long bookId) {
        final Book book = bookService.getBook(bookId);
        DI.debug(logger, book);
        return book;
    }

    @Path("{bookId:[0-9]*}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getBookByPath(@PathParam("bookId") final Long bookId) {
        final Book book = bookService.getBook(bookId);
        DI.debug(logger, book);
        return book;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book saveBook(final Book book) {
        return bookService.saveBook(book);
    }
}
