package com;


import model.Research;

//rest
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//json
import com.google.gson.*;

//xml
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Research")
public class ResearchService {
	
	Research researchObj = new Research();
	
	
	
	@GET 
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearches() {
		return researchObj.readResearches();
	}
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(String researchData) {
		
		//convert the input string to a JSON object
		JsonObject resObj = new JsonParser().parse(researchData).getAsJsonObject();
		
		//read the values from the JSON object
		String res_topic = resObj.get("research_topic").getAsString();
		String res_area = resObj.get("research_area").getAsString();
		String status = resObj.get("status").getAsString();
		String progress = resObj.get("progress").getAsString();
		String res_ID = resObj.get("res_ID").getAsString();
		
		String output = researchObj.insertResearch(res_topic, res_area, status, progress, res_ID);
		
		
		
		
		return output;
	}
	

}
