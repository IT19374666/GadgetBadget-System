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
	 @FormParam("interestArea") String interestArea) 
	{ 
	 String output = FundingBoadyObj.insertFundingBody(name, email, address, phone,interestArea); 
	return output; 
	}
	
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
	 return FundingBoadyObj.readFundingBodies() ; 
	 } 


}
