package com.xavier.webservices.upandrunning.ch04.team.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.io.IOException;
import java.net.URI;


/**
 * Created by Xavier on 2018/2/13.
 */
public class JerseyPublisher {
    public static void main(String[] args) throws IOException {
        final String BASE_URI = "http://localhost:9876/upandrunning/";
        final ResourceConfig rc = new ResourceConfig( ).packages("com.xavier.webservices.upandrunning.ch04.team.jersey.resources");
        //Grizzly HTTP Server
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        System.in.read( );
        server.shutdownNow( );
    }
}
