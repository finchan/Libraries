package com.xavier.rest.jersey.resource;

import com.xavier.rest.jersey.domain.Book;
import com.xavier.rest.jersey.domain.Books;
import com.xavier.utilities.DI;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by Xavier on 2017-02-28.
 */
public class BookResourceTest {
    private final static Logger logger = Logger.getLogger(BookResourceTest.class);
    public static final String BASE_URI = "http://localhost:8080/rest/jersey/bookresource/";
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
    public void testGetAllBooks ( ) {
        final Invocation.Builder invocationBuilder = target.request( );
        final Books books = invocationBuilder.get(Books.class);
        DI.info(logger, books.getBookList());
        Assert.assertNotNull(books);
    }

    @Test
    public void testQueryGetJSON ( ) {
        final WebTarget queryTarget = target.path("/book").queryParam("id", 1L);
        final Invocation.Builder invocationBuilder = queryTarget.request(MediaType.APPLICATION_XML_TYPE);
        final Response response = invocationBuilder.get( );
        final Book result = response.readEntity(Book.class);
//        final Book result = invocationBuilder.get(Book.class);
        DI.info(logger, result);
        Assert.assertNotNull(result);
    }

}