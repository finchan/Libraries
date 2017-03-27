package com.xavier.rest.jersey.resource;

import com.xavier.rest.jersey.resource.implementation.EBookResourceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * Created by Xavier on 2017/3/27.
 */
public class GETTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig(EBookResourceImpl.class);
    }

    @Test
    public void testGet( ) {
        Response response = target("book").request().get();
        Assert.assertEquals("150M", response.readEntity(String.class));
    }
}
