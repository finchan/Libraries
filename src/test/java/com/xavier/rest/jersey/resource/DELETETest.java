package com.xavier.rest.jersey.resource;

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

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * Created by Xavier on 2017-04-10.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DELETETest extends JerseyTest {

    private static final Logger logger = Logger.getLogger(DELETETest.class);

    @Override
    protected Application configure() {
        return new ResourceConfig(EBookResourceImpl.class);
    }

    @Test
    public void testDELETE( ){
        final Response response = target("book").queryParam("bookId", 37L).request().delete();
        int status = response.getStatus();
        DI.info(logger, status);
        Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(), status);
    }
}
