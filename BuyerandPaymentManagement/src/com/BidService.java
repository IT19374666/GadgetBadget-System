package com;
import model.Bid;



//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


/**
 * @author IT19374666
 *
 */

@Path("/Bid")

public class BidService {
	 Bid bidObj = new Bid();
	 @GET
	 @Path("/")
	 @Produces(MediaType.TEXT_HTML)
	 public String readBids() {
		 return bidObj.readBids();
	 }
	
	 
	 @GET
	 @Path("/bid")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_HTML)
	 public String getBid( String bidData) {
		 
		// Converting the input  to a JSON object
		JsonObject bidDataJson = new JsonParser().parse(bidData).getAsJsonObject();
		
		// Read the values from the JSON object
		String itemId = bidDataJson.get("itemCode").getAsString();
		String customerId = bidDataJson.get("customerId").getAsString();
		
		 return bidObj.getBid(itemId, customerId);
	 }
	 
	 @GET
	 @Path("/{itemId}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_HTML)
	 public String getBidsForItem(@PathParam("itemId") String itemId ) {
		 
		
		 return bidObj.readBidsForItem(itemId);
	 }
	 
	 
	 
	 
	 
	 
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertBid(@FormParam(value = "itemCode") String itemCode,
			 @FormParam(value = "customerId")String customerId,
			 @FormParam(value = "amount") String amount,
			 @FormParam(value = "sConditions")String sConditions,
			 @FormParam(value = "dueDate")String dueDate) {
		 	System.out.println("IC" +itemCode);
		 	System.out.println("Amount"+amount);
		 	return bidObj.insertBid(itemCode, customerId, amount, sConditions, dueDate);
		 
		 	
	 }
	 

	 @PUT
	 @Path("/{bid}")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateBids(@FormParam(value = "amount") String amount,
			 @FormParam(value = "sConditions")String sConditions,
			 @FormParam(value = "dueDate")String dueDate,  @PathParam(value = "bid")String bidId) {
		 
		 	return bidObj.updateBids(bidId, amount, sConditions, dueDate);
		 
		 	
	 }
	 

	 @PUT
	 @Path("/accepted/{bid}")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateBidtoAccepted( @PathParam(value = "bid")String bidId) {
		 
		 	return bidObj.updateBidtoAccepted(bidId);
		 
		 	
	 }
	 
	 
	 @DELETE
	 @Path("/{bid}")
	 @Produces(MediaType.TEXT_PLAIN)
	 public String  deleteBid(@PathParam(value = "bid") String bidId) {
		 return bidObj.deleteBid(bidId);
	 }
	 
	 
	 

}


