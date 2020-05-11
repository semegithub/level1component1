package edu.cloudnative.poc;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@Path("/")
public class PostgreSQLResource {

	@Inject
	MyRepository repository;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello() {
		return "hello";
	}

	@POST
	@Transactional
	@Path("/save")
	public Response create(MyEntity2 myEntity) {
		myEntity.persist();
		return Response.ok(myEntity).status(201).build();
	}

	@GET
	@Path("/entities")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAll() {
		return Response.ok(repository.findAll().list()).build();
	}

	@GET
	@Path("/entities/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response findById(@PathParam long id) {
		return Response.ok(MyEntity2.findById(id)).build();
	}

//    @GET
//    @Path("/paginatedentities")
//    public Response getAll(@BeanParam PageRequest pageRequest) {
//        return Response.ok(((PanacheRepository) petRepository).findAll()
//                        .page(Page.of(pageRequest.getPageNum(), pageRequest.getPageSize()))
//                        .list()).build();
//    }

	public class PageRequest {

		@QueryParam("pageNum")
		@DefaultValue("0")
		private int pageNum;

		@QueryParam("pageSize")
		@DefaultValue("10")
		private int pageSize;

	}
}