package com;

import model.FundingBoady; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/FundingBoady")
public class FundingService {
	
	FundingBoady FundingBoadyObj = new FundingBoady(); 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertFBody(@FormParam("name") String name, 
	 @FormParam("email") String email, 
	 @FormParam("address") String address, 
	 @FormParam("phone") String phone,
	 @FormParam("interestArea") String interestArea, 
	 @FormParam("fund_range") String fund_range)
	{ 
	 String output = FundingBoadyObj.insertFundingBody(name, email, address, phone,interestArea,fund_range); 
	return output; 
	}
	
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
	 return FundingBoadyObj.readFundingBodies() ; 
	 }
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFbody(String FbodyData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject fBodyObject = new JsonParser().parse(FbodyData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String idFundingBody = fBodyObject.get("idFundingBody").getAsString(); 
	 String name = fBodyObject.get("name").getAsString(); 
	 String email = fBodyObject.get("email").getAsString(); 
	 String address = fBodyObject.get("address").getAsString(); 
	 String phone = fBodyObject.get("phone").getAsString();
	 String interestArea = fBodyObject.get("interestArea").getAsString();
	 String fund_range = fBodyObject.get("fund_range").getAsString();
	 String output = FundingBoadyObj.updateFundingBody(idFundingBody, name, email, address, phone,interestArea,fund_range); 
	return output; 
	}
	
	
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFbody(String fBodyData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fBodyData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String idFundingBody = doc.select("idFundingBody").text(); 
	 String output = FundingBoadyObj.deleteFundingBody(idFundingBody); 
	return output; 
	}
	
	//intercommunication - researcher request to retrieve all funding bodies
	@GET
	@Path("/readFbody/{interestArea}") 
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML) 
	//call read all funding bodies method
	public String readFbody( @PathParam(value = "interestArea")String InterestArea)
	{
		

		return FundingBoadyObj.RequestReadFundingBodies(InterestArea) ;
	}
	
	
	//Return currently funded stage to the researcher for a particular research

	@GET
	@Path("/readCurrentStage/{researchID}") 
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML) 
	//call read all funding bodies method
	public String readCurrentStage( @PathParam(value = "researchID")String ResearchID)
	{
		

		return FundingBoadyObj.currentFundedStage(ResearchID) ;
	}
	
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
	 String output = FundingBoadyObj.insertFundingBody(researchID, fundingBodyID, currentStage, fundsForCurrentStage,totalFunds,description); 
	return output; 
	}
	
	
	
	
	
	
	/*Client client = Client.create();
	WebResource webResource = client.resource("");
	ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
	String queryResponse = response.getEntity(String.class);
	
	System.out.println(queryResponse);

	return queryResponse;*/

}
