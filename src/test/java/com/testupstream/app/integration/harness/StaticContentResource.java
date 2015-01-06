package com.testupstream.app.integration.harness;

import com.testupstream.app.routes.Urls;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path(Urls.ASSETS_PATH)
public class StaticContentResource {

    private final String ASSET_PATH_PARAM = "assetPath";

    @GET
    @Path("{" + ASSET_PATH_PARAM + " : .*}")
    public Response getAsset(@PathParam(ASSET_PATH_PARAM) String path) throws IOException {
        return Response.ok(ClassLoader.getSystemResource(Urls.ASSETS_PATH.substring(1) + "/" + path).getContent()).build();
    }
}