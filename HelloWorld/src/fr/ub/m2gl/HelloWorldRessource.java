package fr.ub.m2gl;

import fr.ub.m2gl.*;

import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.mongodb.client.MongoCursor;
import com.sun.org.apache.xerces.internal.util.URI;

@Path("/users")

public class HelloWorldRessource {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHelloWorld(@QueryParam("firstname") String f, @QueryParam("lastname") String l) {
		if(f == null || l == null)
			return Response.status(409).entity("value needed!").build();
		String result = MongoUser.addUserToMongo(new User(f, l));

		return Response.status(201).entity(result).build();
	}
	
	@Path("/{firstname}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("firstname") String f) {
		String result = MongoUser.deleteUserFromMongo(f);
		if(!result.equals("deleted"))
			return Response.status(404).entity("Not Found").build();
		return Response.status(200).entity("Deleted").build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateuser(@QueryParam("firstname") String f, @QueryParam("lastname") String l,
			@QueryParam("old") String n) {
		if(f == null || l == null || n == null)
			return Response.status(204).entity("value needed!").build();
		
		String result = MongoUser.updateUserToMongo(new User(f, l), n);
		if(result.equals("failed"))
			return Response.status(404).entity("Not found").build();
		
		return Response.status(200).entity("updated").build();
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getHelloWorld() {
 
		return MongoUser.showUsers();
	}
	
	@Path("/{firstname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User showUser(@PathParam("firstname") String f) {

		return MongoUser.showUser(f);
	}
	
	
	
	
	
	
	
	
	
	
}
