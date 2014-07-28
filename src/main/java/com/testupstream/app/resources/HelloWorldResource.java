package com.testupstream.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloWorldResource {

    @GET
    public Response getHelloWorld(){
        return Response.ok("Hello, World!").build();
    }

}
