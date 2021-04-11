package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


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
			 @FormParam(value = "cardNo")String cardNo,
			 @FormParam(value = "paymentDate")String paymentDate) {
		 
		 	paymentObj.insertPayment(itemCode,customerId, amount, pMethod, cardNo, paymentDate);
		 
		 	
	 }
	 
	 @GET
	 @Path("/{cId}")
	 public String getPayment(@PathParam(value="cId") String customerId) {
		return paymentObj.getPayment(customerId);
		 
		 
	 }
	 
	 

}
