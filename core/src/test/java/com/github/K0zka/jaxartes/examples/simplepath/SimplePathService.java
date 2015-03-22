package com.github.K0zka.jaxartes.examples.simplepath;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/simple/")
public class SimplePathService {
    @Path("/hello")
    @GET
    public String sayHello() {
        return "Hello world!";
    }
}
