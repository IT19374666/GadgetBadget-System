package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.FundingBoady;
import model.FundsGiving;

@Path("/FundsGiving")
public class FundsGivingService {

	FundsGiving FundingDetailObj = new FundsGiving(); 
	
	
	@POST
	@Path("/insertFundsStartDetails") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertFundsStartDetails(@FormParam("researchID") String researchID, 
	 @FormParam("fundingBodyID") String fundingBodyID, 
	 @FormParam("currentStage") String currentStage, 
	 @FormParam("fundsForCurrentStage") String fundsForCurrentStage,
	 @FormParam("totalFunds") String totalFunds, 
	 @FormParam("description") String description)
	{ 
	 String output = FundingDetailObj.insertStartupFundsDetails(researchID, fundingBodyID, currentStage, fundsForCurrentStage,totalFunds,description); 
	return output; 
	}
	
	
	//request from researcher to retrieve current completed stage(progress)
	
	@GET
	@Path("/readCurrentCompletedStage/{researchID}") 
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML) 
	//call read all product method
	public String readCurrentCompletedStage( @PathParam(value = "researchID")String ResearchID) {
	//check
		System.out.println(ResearchID);
	Client client = Client.create();
	WebResource webResource = client.resource("http://localhost:8088/ResearchAndResearcherManagement/ResearchService/Researcher/readCurrentCompletedStage/"+ResearchID);
	ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
	String queryResponse = response.getEntity(String.class);
	
	System.out.println(queryResponse);

	return queryResponse;
	
	}
	


	// update funding details according to the progress of the research
	
	@PUT
	@Path("/updateFundingDetails/{researchID}") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFundingDetails(@PathParam(value = "researchID")String ResearchID) 
	{ 
	/*//Convert the input string to a JSON object 
	 JsonObject fBodyObject = new JsonParser().parse(FundingData).getAsJsonObject(); 
	//Read the values from the JSON object
	 
	 String UpdatefundedStage = fBodyObject.get("currentStage").getAsString();
	 ,String FundingData
	*/
	 
	 	Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8088/ResearchAndResearcherManagement/ResearchService/Researcher/readCurrentCompletedStage/"+ResearchID);
		ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
		String queryResponse = response.getEntity(String.class);
		
		if(queryResponse.equals("stage1")) {
			
			return FundingDetailObj.updateCurrentFudsStage(ResearchID, 2);
			
		}else if (queryResponse.equals("stage2")) {
			
			return FundingDetailObj.updateCurrentFudsStage(ResearchID, 3);
		}
		else if (queryResponse.equals("stage3")) {
			return FundingDetailObj.updateCurrentFudsStage(ResearchID, 4);
		}
		else {
			
			return "Invalid completed stage!!!";
		}

	}
	
	
	
}
