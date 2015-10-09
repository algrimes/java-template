package com.testupstream.app.resources;

import com.google.inject.Inject;
import com.testupstream.app.providers.ResponseProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HelloWorldResource {

    private ResponseProvider responseProvider;

    @Inject
    public HelloWorldResource(ResponseProvider responseProvider) {
        this.responseProvider = responseProvider;
    }

    @GET
    public Response getHelloWorld(){
        return Response.ok(responseProvider.get()).build();
    }

}
