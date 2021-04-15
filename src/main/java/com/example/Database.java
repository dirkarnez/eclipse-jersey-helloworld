package com.example;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Hashtable;

@Singleton
@Path("/mark")
public class Database {
	private Hashtable<String, Integer> table = new Hashtable<>();

	@POST
	@Path("/record")
	public Response addRecord(@QueryParam("id") String id, @QueryParam("mark") Integer mark) {
		if (table.containsKey(id)) {
			return Response.status(404).build();
		}

		table.put(id, mark);
		return Response.status(200).build();
	}

	@GET
	@Path("/record")
	@Produces(MediaType.TEXT_HTML)
	public Response getMark(@QueryParam("id") String id) {
		if (!table.containsKey(id)) {
			return Response.status(404).build();
		}

		return Response.status(200).entity(String.format("%d", table.get(id))).build();
	}

	@PUT
	@Path("/record")
	public Response updateRecord(@QueryParam("id") String id, @QueryParam("mark") Integer mark) {
		if (!table.containsKey(id)) {
			return Response.status(404).build();
		}

		table.put(id, mark);
		return Response.status(200).build();
	}
}