package com;

import model.FundingBoady; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

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

	


}
