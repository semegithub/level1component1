package edu.cloudnative.poc;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/")
@Produces("application/json")
@Consumes("application/json")
public class PostgreSQLResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "hello";
    }
    
    @POST
    @Transactional
    @Path("/save")
    public Response create(MyEntity myEntity) {
    	myEntity.persist();
        return Response.ok(myEntity).status(201).build();
    }
    
    @GET
    @Path("/entites")
    public List<MyEntity> findAll() {
        return MyEntity.listAll();
    }
    
    @GET
    @Path("/entities/{id}")
    public List<MyEntity> findById(@PathParam int id) {
        return MyEntity.findById(id);
    }

}