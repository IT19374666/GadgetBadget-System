package com;

import model.Payment;

//For generating  date
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;


//For REST Service

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/*
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

*/
/**
 * @author IT19374666
 *
 */
@Path("/payment")
public class PaymentService {
	/*String itemCode, String amount, String pMethod, String cardNo,String paymentDate*/
	
	 Payment paymentObj = new Payment();
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public void insertPayment(@FormParam(value = "itemCode") String itemCode,
			 @FormParam(value = "customerId") String customerId,
			 @FormParam(value = "amount") String amount,
			 @FormParam(value = "pMethod") String pMethod,
			 @FormParam(value = "cardNo")String cardNo) {
		 
		 	//Get current date
		 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		 	String paymentDate = formatter.format( new Date());
		 	
		 
		 	//System.out.println(paymentDate);
		 	paymentObj.insertPayment(itemCode,customerId, amount, pMethod, cardNo, paymentDate);
		 
		 	
	 }
	 
	 @GET
	 @Path("/{cId}")
	 public String getPayment(@PathParam(value="cId") String customerId) {
		return paymentObj.getPayment(customerId);
		 
		 
	 }
	 
	 

}
