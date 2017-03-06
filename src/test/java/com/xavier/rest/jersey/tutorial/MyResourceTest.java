package com.xavier.rest.jersey.tutorial;

import com.xavier.rest.jersey.server.Main;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

/**
 * Created by Xavier on 2017-02-22.
 */
public class MyResourceTest {
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp( ) throws Exception {
        server = Main.startServer( );
        Client c = ClientBuilder.newClient( );
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown( ) throws Exception {
        server.shutdownNow( );
    }

    @Test
    public void testGetIt( ) {
        String responseMsg = target.path("myresource").request( ).get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
