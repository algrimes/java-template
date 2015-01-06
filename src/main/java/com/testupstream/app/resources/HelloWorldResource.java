package com.testupstream.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HelloWorldResource {

    @GET
    public Response getHelloWorld(){
        return Response.ok("<html><body><h1>Hello, World!</h1></body></html>").build();
    }

}
