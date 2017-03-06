package com.xavier.rest.jersey.tutorial;

import com.xavier.rest.jersey.domain.Device;
import com.xavier.rest.jersey.server.Main;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by Xavier on 2017-02-22.
 */
public class DeviceResourceTest {
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp( ) {
        server = Main.startServer( );
        final Client client = ClientBuilder.newClient( );
        target = client.target(Main.BASE_URI);
    }

    @After
    public void tearDown( ) {
        server.shutdownNow( );
    }

    @Test
    public void testGetDevice ( ){
        final String testIp = "10.11.58.163";
        Device device = target.path("device").queryParam("ip", testIp).request( ).get(Device.class);
        Assert.assertEquals(testIp, device.getDeviceIp());
    }

    @Test
    public void testUpdateDevice( ) {
        final String testIp = "10.11.58.163";
        final Device device = new Device( testIp);
        device.setDeviceStatus(1);

        Entity<Device> entity = Entity.entity(device, MediaType.APPLICATION_XML_TYPE);
        final Device result = target.path("device").request( ).put(entity, Device.class);

        Assert.assertEquals(1, result.getDeviceStatus( ));
    }
}
