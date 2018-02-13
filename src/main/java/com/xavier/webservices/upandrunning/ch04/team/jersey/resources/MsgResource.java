package com.xavier.webservices.upandrunning.ch04.team.jersey.resources;

import javax.ws.rs.*;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

/**
 * Created by Xavier on 2018/2/12.
 */

@Path("/")
public class MsgResource {
    private static String msg = "Hello, world!";

    @GET
    @Produces("text/plain")
    public String read() {
        return msg + "\n";
    }

    @GET
    @Produces("text/plain")
    @Path("{extra}")
    public String personalized_read(@PathParam("extra") String cus) {
        return this.msg + ": " + cus + "\n";
    }

    @POST
    @Produces("text/html")
    public String create(@FormParam("msg") String new_msg) {
        this.msg = new_msg;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        XMLEncoder enc = new XMLEncoder(stream);
        enc.writeObject(new_msg);
        enc.close();
        return new String(stream.toByteArray()) + "\n";
    }

    @DELETE
    @Produces("text/plain")
    public String delete() {
        this.msg = null;
        return "Message deleted. \n";
    }
}
