package com;
import model.Bid;

import java.sql.Date;

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
	 @Path("/bid/{itemId}/{customerId}")
	
	 @Produces(MediaType.TEXT_HTML)
	 public String getBid(@PathParam(value = "itemId") String itemId,@PathParam(value = "customerId") String customerId) {
		 return bidObj.getBid(itemId, customerId);
	 }
	 
	 
	 
	 
	 
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public void insertBid(@FormParam(value = "itemCode") String itemCode,
			 @FormParam(value = "customerId")String customerId,
			 @FormParam(value = "amount") String amount,
			 @FormParam(value = "sConditions")String sConditions,
			 @FormParam(value = "dueDate")String dueDate) {
		 
		 	bidObj.insertBid(itemCode, customerId, amount, sConditions, dueDate);
		 
		 	
	 }
	 

	 @PUT
	 @Path("/{bid}")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public void updateBids(@FormParam(value = "amount") String amount,
			 @FormParam(value = "sConditions")String sConditions,
			 @FormParam(value = "dueDate")String dueDate,  @PathParam(value = "bid")String bidId) {
		 
		 	bidObj.updateBids(bidId, amount, sConditions, dueDate);
		 
		 	
	 }
	 
	 
	 @DELETE
	 @Path("/{bid}")
	 public void deleteBid(@PathParam(value = "bid") String bidId) {
		 bidObj.deleteBid(bidId);
	 }
	 
	 
	 

}


