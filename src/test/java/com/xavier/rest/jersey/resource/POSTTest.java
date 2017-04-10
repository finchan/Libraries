package com.xavier.rest.jersey.resource;

import com.xavier.rest.jersey.domain.Book;
import com.xavier.rest.jersey.resource.implementation.EBookResourceImpl;
import com.xavier.utilities.DI;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 * Created by Xavier on 2017-04-10.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class POSTTest extends JerseyTest {
    private static final Logger logger = Logger.getLogger(POSTTest.class);
    @Override
    protected Application configure() {
        return new ResourceConfig(EBookResourceImpl.class);
    }

    @Test
    public void testPOST( ) {
        Book newBook = new Book("RESTFUL", "CHINA");
        MediaType contentType = MediaType.APPLICATION_XML_TYPE;
        MediaType acceptType = MediaType.APPLICATION_XML_TYPE;
        Entity<Book> bookEntity = Entity.entity(newBook, contentType);
        //TODO: Learn Java Reflaction!!!
        Book book = target("book").request(acceptType).post(bookEntity, Book.class);
        DI.info(logger, book.getBookId());
        Assert.assertNotNull(book.getBookId());
    }
}
