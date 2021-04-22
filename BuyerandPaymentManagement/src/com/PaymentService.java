package com;

import model.Payment;

//For generating  date
import java.text.SimpleDateFormat;
import java.util.Date;


//For REST Service

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


//For interprocess communication 
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


/**
 * @author IT19374666
 *
 */
@Path("/payment")
public class PaymentService {

	
	 Payment paymentObj = new Payment();
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertPayment(@FormParam(value = "itemCode") String itemCode,
			 @FormParam(value = "bidId") String bidId,
			 @FormParam(value = "customerId") String customerId,
			 @FormParam(value = "amount") String amount,
			 @FormParam(value = "pMethod") String pMethod,
			 @FormParam(value = "cardNo")String cardNo) {
		 
		 	//Get current date
		 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		 	String paymentDate = formatter.format( new Date());
		 	
		 	//Implementation of payment validation to check the state of the bids before making the payment
		 	Client client = Client.create();
			 
		 	WebResource webResource= client.resource("http://localhost:8088/BuyerandPaymentManagement/BuyerandPaymentService/Bid/bidState/" + bidId);
		 	ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
		 	
		 	
		 	String accepted = response.getEntity(String.class);
		 	System.out.println("State" +accepted);
		 	if(accepted.equals("Yes") ) {
		 	
		 	
			 	//Implementation of the interprocess communication for payment 
			 	//Client requestClient = Client.create();
			 
			 	webResource = client.resource("http://localhost:8088/ProductAndSellerManagementService/ProductService/Product/updateStatus/" +itemCode +"/" +customerId);
			 	response = webResource.type("application/xml").put(ClientResponse.class);
			 	
			 	
			 	String queryRespose = response.getEntity(String.class);
			 	
			 	
			 	return paymentObj.insertPayment(itemCode,bidId,customerId, amount, pMethod, cardNo, paymentDate);
		 	}else {
		 		return "Bid is not accepted";
		 	}
		 	
	 }
	 
	 @GET
	 @Path("/{cId}")
	 public String getPayment(@PathParam(value="cId") String customerId) {
		return paymentObj.getPayment(customerId);
		 
		 
	 }
	 
	 

}
