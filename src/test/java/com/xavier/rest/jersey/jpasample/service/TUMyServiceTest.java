package com.xavier.rest.jersey.jpasample.service;

import com.xavier.rest.jersey.domain.Book;
import com.xavier.rest.jersey.service.BookService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Xavier on 2017-02-28.
 */

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUMyServiceTest {
    private final static Logger LOGGER = Logger.getLogger(TUMyServiceTest.class);
    @Autowired
    private BookService bookService;

    @Test
    public void testGetAndSave() {
        final Book result = bookService.getBook(1L);
        LOGGER.info(result.getBookName());
        Assert.assertNotNull(result.getBookName());
    }
}
