package com.xavier.rest.jersey.servlet;

import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by Xavier on 2017-02-26.
 */
@WebServlet(
    initParams = @WebInitParam(
            name = "jersey.config.server.provider.packages",
            value="com.xavier.rest.jersey.resource"
    ),
    urlPatterns = "/rest/jersey/webapi2/*",
    loadOnStartup = 1
)
public class AirServlet extends ServletContainer {

}
