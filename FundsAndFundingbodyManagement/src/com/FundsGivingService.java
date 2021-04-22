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
	
	//////return outputs to the client as response//////// @Consumes - input data type @Produces- output data type 
	
	
	@POST
	@Path("/insertFundsStartDetails") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertFundsStartDetails(@FormParam("researchID") String researchID, 
	 @FormParam("fundingBodyID") String fundingBodyID, 
	 @FormParam("currentStage") String currentStage, 
	 @FormParam("fundsForCurrentStage") String fundsForCurrentStage,
	 @FormParam("totalFunds") String totalFunds, 
	 @FormParam("description") String description,
	 @FormParam("totalFundedAmount") String totalFundedAmount)
	
	{ 
	 String output = FundingDetailObj.insertStartupFundsDetails(researchID, fundingBodyID, currentStage, fundsForCurrentStage,totalFunds,description,totalFundedAmount); 
	return output; 
	}
	
	
	
	@GET
    @Path("/readFundAmountForStage/{researchID}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML)
    //call read all product method
    public String readProductMinimumPrice( @PathParam(value = "researchID")String ResearchID)
    {
        return FundingDetailObj.readFundAmoutForOneStage(ResearchID);
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

	 	Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8088/ResearchAndResearcherManagement/ResearchService/Research/getProgress/"+ResearchID);
		ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
		String currentCompletedStage = response.getEntity(String.class);
	
		
		Client client1 = Client.create();
		WebResource webResource1 = client1.resource("http://localhost:8088/FundsAndFundingbodyManagement/FundingGivingService/FundsGiving/readFundAmountForStage/" +ResearchID);
		ClientResponse response1 = webResource1.type("application/xml").get(ClientResponse.class);
		String queryResponse1 = response1.getEntity(String.class);
		double fundsForOneStage = Double.parseDouble(queryResponse1);
		
		//research management micro-service return currently completed stage value as Stage1
		if(currentCompletedStage.equals("Stage1")) {
			double totalFundedUptoNow = 2 * fundsForOneStage;
			return FundingDetailObj.updateFunds(ResearchID, 2,totalFundedUptoNow);
			
			//research management micro-service return currently completed stage value as Stage2	
		}else if (currentCompletedStage.equals("Stage2")) {
			double totalFundedUptoNow = 3 * fundsForOneStage;
			return FundingDetailObj.updateFunds(ResearchID, 3,totalFundedUptoNow);
		}
		//research management micro-service return currently completed stage value as Stage3
		else if (currentCompletedStage.equals("Stage3")) {
			double totalFundedUptoNow = 4 * fundsForOneStage;
			return FundingDetailObj.updateFunds(ResearchID, 4,totalFundedUptoNow);
		}
		//research management micro-service return currently completed stage value as Stage4
		else if(currentCompletedStage.equals("Stage4")){
			return "Funding process completed for all 4 stages...";
		}
		else {	
			return "Invalid completed stage!!! Maximum Stage can be Stage 4";
		}

	}
	
	
	
}
