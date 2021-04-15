package com;

import model.Proposal;

//rest
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//json
import com.google.gson.*;

//xml
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Proposal")
public class ProposalService {
	
	Proposal proposalObj = new Proposal();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(String propData) {
		
		//convert the input string to a JSON object
		JsonObject propObj = new JsonParser().parse(propData).getAsJsonObject();
		
		//read the values from the JSON object
		String desc = propObj.get("description").getAsString();
		String date = propObj.get("date").getAsString();
		String res_ID = propObj.get("research_ID").getAsString();
		
		
		String output = proposalObj.insertProposal(desc, date, res_ID);
		
		return output;
	}

}
