package fr.ub.m2gl;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/json")
public class UserRessource {
	MyObjectMapperProvider myObj = new MyObjectMapperProvider();
	FileOutputStream file;

	@Path("/write")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String addUser() throws FileNotFoundException, JsonProcessingException {
		file = new FileOutputStream("/home/zakaria/Documents/Web-Td/HelloWorld/carnet.json", false);
		LinkedList<User> userList = new LinkedList<User>();
		userList.add(new User("anas", "belmekki"));
		userList.add(new User("zakaria", "mallouky"));
		try {
			myObj.defaultObjectMapper.writeValue(file, userList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		return myObj.defaultObjectMapper.writeValueAsString(userList);
	}

	@Path("/read")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser() throws IOException {
		FileInputStream in = new FileInputStream("/home/zakaria/Documents/Web-Td/HelloWorld/carnet.json");
		User[] myUsers = myObj.defaultObjectMapper.readValue(in, User[].class);
		StringBuilder myBuffer = new StringBuilder("");
		for (User user : myUsers)
			myBuffer.append(user.toString() + "\n");
		return myBuffer.toString();

	}

}
