package com.xavier.rest.jersey.resource;

import com.xavier.rest.jersey.domain.Book;
import com.xavier.rest.jersey.domain.Books;
import com.xavier.utilities.DI;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Xavier on 2017-03-16.
 */
public class JerseyTestBookResourceTest extends JerseyTest {
    private static final Logger logger = Logger.getLogger(JerseyTestBookResourceTest.class);
    private static final String BASEURI = "books/";
    private static String CONTAINER_GRIZZLY = "org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory";
    private static String CONTAINER_MEMORY = "org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory";
    private static String CONTAINER_JDK = "org.glassfish.jersey.test.jdkhttp.JdkHttpServerTestContainerFactory";
    private static String CONTAINER_SIMPLE = "org.glassfish.jersey.test.simple.SimpleTestContainerFactory";

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(BookResource.class);
    }

    @Test
    public void getGetAll( ) {
        final Invocation.Builder builder = target(JerseyTestBookResourceTest.BASEURI).request();
        final Books result = builder.get(Books.class);
        DI.info(logger, result);
        Assert.assertNotNull(result);
    }

    @Test
    public void testQueryGetXML( ) {
        final WebTarget queryTarget = target(JerseyTestBookResourceTest.BASEURI+ "book").queryParam("id", 1L);
        final Invocation.Builder builder = queryTarget.request(MediaType.APPLICATION_XML);
        final Response response = builder.get();
        final Book result = response.readEntity(Book.class);
        DI.info(logger, result);
        Assert.assertNotNull(result);
    }

    @Test
    public void testPathGetJSON( ) {
        final WebTarget pathTarget = target(JerseyTestBookResourceTest.BASEURI + "1");
        final Invocation.Builder builder = pathTarget.request(MediaType.APPLICATION_JSON);
        final Book book  = builder.get(Book.class);
        DI.info(logger, book);
        Assert.assertNotNull(book);
    }

    @Test
    public void testPostAndDelete( ) {
        final Book book = new Book("CXF", "N/A");
        final Entity<Book> entity = Entity.entity(book, MediaType.APPLICATION_JSON_TYPE);
        final Book savedBook = target(JerseyTestBookResourceTest.BASEURI).request(MediaType.APPLICATION_JSON).post(entity, Book.class);
        DI.info(logger, savedBook);
        Assert.assertNotNull(savedBook.getBookId());

        final WebTarget deletedTarget = target(JerseyTestBookResourceTest.BASEURI).path(savedBook.getBookId().toString());
        final Invocation.Builder builder = deletedTarget.request();
        final String result = builder.delete(String.class);
        DI.info(logger, result);
        Assert.assertNotNull(result);
    }
}
