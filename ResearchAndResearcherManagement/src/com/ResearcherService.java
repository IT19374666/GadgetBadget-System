package com;

import model.Researcher;

//rest
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//json
import com.google.gson.*;

//xml
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Researcher")
public class ResearcherService {

	
	Researcher researcherobj = new Researcher();
	
	@GET 
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String read() {
		return "Hello";
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcher(@FormParam("name") String name,
									@FormParam("email") String email,
									@FormParam("address") String address,
									@FormParam("phone") String phone,
									@FormParam("interestArea") String interest,
									@FormParam("type") String type)
	{
		String output = researcherobj.insertResearcher(name, email, address, phone, interest, type);
		return output;
				
	}
}
