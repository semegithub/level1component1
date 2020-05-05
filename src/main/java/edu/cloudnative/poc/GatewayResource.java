package edu.cloudnative.poc;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/")
public class GatewayResource {

	@Inject
    GatewayService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "Hello!";
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/cpustress/{cpucounter}")
    public String cpustress(@PathParam Integer cpucounter) {
        return service.cpustress(cpucounter);
    }
}