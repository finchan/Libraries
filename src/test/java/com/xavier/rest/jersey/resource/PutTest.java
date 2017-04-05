package com.xavier.rest.jersey.resource;

import com.xavier.rest.jersey.domain.Book;
import com.xavier.utilities.DI;
import org.apache.log4j.Logger;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * Created by Xavier on 2017/4/5.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PutTest extends JerseyTest {
    private static final Logger logger = Logger.getLogger(JerseyTestBookResourceTest.class);

    @Test
    public void testNew( ) {
        Book book = new Book("AAA", "ZZZ");
        MediaType contentTypeMediaType = MediaType.APPLICATION_XML_TYPE;
        MediaType acceptTypeMediaType = MediaType.TEXT_PLAIN_TYPE;
        final Entity<Book> bookEntity = Entity.entity(book, contentTypeMediaType);
        final String lastUpdate = target("book").request(acceptTypeMediaType).put(bookEntity, String.class);
        Assert.assertNotNull(lastUpdate);
        DI.info(logger, lastUpdate);
    }
}
