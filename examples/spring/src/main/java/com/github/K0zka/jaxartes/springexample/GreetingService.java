package com.github.K0zka.jaxartes.springexample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("greeting")
public class GreetingService {
    @GET
    public String greet(@QueryParam("name") String name) {
        return "Hello " + (name == null ? "world" : name) + "!";
    }
}
