package com.xavier.rest.jersey.tutorial;

import com.xavier.rest.jersey.domain.Book;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URI;

/**
 * Created by Xavier on 2017-02-28.
 */
public class TIMyResourceTest {
    private final static Logger LOGGER = Logger.getLogger(TIMyResourceTest.class);
    //TODO: there is bug here - 404 NOT Found Exception...
    public static final String BASE_URI = "http://localhost:8080/rest/jersey/bookjpa/*/";
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        final ResourceConfig rc = new ResourceConfig().packages("com.xavier.rest.jersey.resource");
        final URI uri = URI.create(BASE_URI);
        server = GrizzlyHttpServerFactory.createHttpServer(uri, rc, false);
        server.start();
        final ClientConfig cc = new ClientConfig();
        final Client client = ClientBuilder.newClient(cc);
        target = client.target(BASE_URI).path("books");
    }

    @After
    public void tearDown() throws Exception {
        if (server != null) {
            server.shutdown();
        }
    }

    @Test
    public void testStoreBook( ) {
        final Book newBook = new Book("Java Restful Web Service实战-" + System.nanoTime());
        final Entity<Book> bookEntity = Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE);
        final Book savedBook = target.request(MediaType.APPLICATION_JSON_TYPE).post(bookEntity, Book.class);
        Assert.assertNotNull(savedBook.getBookId());
        LOGGER.debug("<<Test Post");
    }
}