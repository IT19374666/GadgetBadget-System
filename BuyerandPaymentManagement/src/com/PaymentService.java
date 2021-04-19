package com;

import model.Payment;

//For generating  date
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;


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
			 @FormParam(value = "customerId") String customerId,
			 @FormParam(value = "amount") String amount,
			 @FormParam(value = "pMethod") String pMethod,
			 @FormParam(value = "cardNo")String cardNo) {
		 
		 	//Get current date
		 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		 	String paymentDate = formatter.format( new Date());
		 	
		 	
		 	//Implementation of the interprocess communication for payment 
		 	Client client = Client.create();
		 
		 	WebResource webResource = client.resource("http://localhost:8088/ProductAndSellerManagementService/ProductService/Product/updateStatus/" +itemCode +"/" +customerId);
		 	ClientResponse response = webResource.type("application/xml").put(ClientResponse.class);
		 	//System.out.println("Res :" +response);
		 	
		 	String queryRespose = response.getEntity(String.class);
		 	
		 	//System.out.println(queryRespose);
		 	
		 	
		 	return paymentObj.insertPayment(itemCode,customerId, amount, pMethod, cardNo, paymentDate);
		 
		 	
	 }
	 
	 @GET
	 @Path("/{cId}")
	 public String getPayment(@PathParam(value="cId") String customerId) {
		return paymentObj.getPayment(customerId);
		 
		 
	 }
	 
	 

}
