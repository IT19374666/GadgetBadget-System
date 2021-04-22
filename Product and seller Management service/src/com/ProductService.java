package com;

import model.Product; 



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


@Path("/Product")
public class ProductService 
{
	Product productObj = new Product();
	
	//Read All Product from the Product table
	@GET
	@Path("/read") 
	@Produces(MediaType.TEXT_HTML) 
	//call read all product method
	public String readProduct() 
	{ 
		return productObj.readAllProduct();
	}
	
	//Insert the Product details to Database
	@POST
	@Path("/Insert/{SellerID}") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct(
			@FormParam("ProductName") String ProductName, 
			@FormParam("ProductType") String ProductType, 
			@FormParam("MinimumPrice") String MinimumPrice, 
			@FormParam("ProductDescription") String ProductDescription,
			@FormParam("ClosingDate") String ClosingDate,
			@PathParam("SellerID") String SellerID)
	{
	 		String output = productObj.insertProduct(ProductName, ProductType, MinimumPrice, ProductDescription, ClosingDate,SellerID); 
	 		return output;
	}
	
	
	//Update the product details  
	@PUT
	@Path("/Update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String ProductData) 
	{
		//Convert the input string to a JSON object 
		JsonObject pObject = new JsonParser().parse(ProductData).getAsJsonObject(); 
	 
		//Read the values from the JSON object
		String productID = pObject.get("ProductID").getAsString(); 
		String productName = pObject.get("ProductName").getAsString(); 
		String productType = pObject.get("ProductType").getAsString();  
		String productDescription = pObject.get("ProductDescription").getAsString();
		String productEndDate = pObject.get("ClosingDate").getAsString();
	 
		//call the update method
		String output = productObj.updateProduct(productID, productName, productType, productDescription, productEndDate);
		return output; 
	}
	
	
	//Delete the product Details
	@DELETE
	@Path("/Delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String productData) 
	{ 
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <ProductID>
	 String productID = doc.select("ProductID").text();
	 
	 //Call the Delete method
	 String output = productObj.deleteProduct(productID); 
	 return output; 
	}
	
	
	//Implement start for Intercommunication
	
	//Call the Buyer's API to update the buyer's Bid Accepted column
	@PUT
	@Path("/acceptBid/{BID}") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String acceptBid(@PathParam(value = "BID")String bidId) 
	{
		//call the client to run Buyer's API
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8085/BuyerandPaymentManagement/BuyerandPaymentService/Bid/accepted/" + bidId);
		ClientResponse response = webResource.type("application/xml").put(ClientResponse.class);
		String queryResponse = response.getEntity(String.class);
		
		System.out.println(queryResponse);
	
		return queryResponse;
	}
	
	
	/*@GET
	@Path("/readBit") 
	@Produces(MediaType.TEXT_HTML) 
	//call read all product method
	public String readBid()
	{
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8085/BuyerandPaymentManagement/BuyerandPaymentService/Bid/readBids/");
		ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
		String queryResponse = response.getEntity(String.class);
		
		System.out.println(queryResponse);
	
		return queryResponse;
	}*/
	
	
	//Create API to Filter Product Details giving by the Product Type 
    @GET
    @Path("/readProductType/{productType}") 
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML) 
    //call read all product method
    public String readProduct( @PathParam(value = "productType")String ProductType)
    {
    	//Call the product details filter method
        return productObj.readProductTypes(ProductType);
    }
    
    
    //Create API for return product minimum value - Use this API buyer
    @GET
    @Path("/readProductMinimumPrice/{productID}") 
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML) 
    //call read all product method
    public String readProductMinimumPrice( @PathParam(value = "productID")String ProductID)
    {
    	//Call the product minimum value retrieve method
        return productObj.readProductMinimumValue(ProductID);
    }
    
    
    //call the API for buyer's Minimum bid amount retrieve function
    @GET
	@Path("/readLowestBid/{BID}") 
	@Produces(MediaType.TEXT_HTML) 
	//call read all product method
	public String readBid(@PathParam(value = "BID")String bidId)
	{
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8085/BuyerandPaymentManagement/BuyerandPaymentService/Bid/LowestBid/" + bidId);
		ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
		String queryResponse = response.getEntity(String.class);
		
		System.out.println(queryResponse);
	
		return queryResponse;
	}
    
    //Create API to Buyer for Update product table as product is sold
    @PUT
	@Path("/updateStatus/{productId}/{CustomerID}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBidtoAccepted( @PathParam(value = "productId")String ProductId,@PathParam(value = "CustomerID")String customerID) 
    {
    	//call the method for update to Product status and Buyer's Id
    	return productObj.updateSoldProduct(ProductId,customerID);	 	
	}
	
    
     //Main Intercommunication - use API with client for validate to minimum value when updating the product minimum price
     @PUT
	 @Path("/updateProductMinimumPrice/{productId}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateProductMinimumPrice( @PathParam(value = "productId")String ProductId,String ProductPriceData) 
     {
    	 
    	 //Convert the input string to a JSON object 
		 JsonObject pObject = new JsonParser().parse(ProductPriceData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object
		 String productPrice = pObject.get("MinimumPrice").getAsString();
		 double insertedProductPrice = Double.parseDouble(productPrice);
    	
    	 //call the buyer's API to return to Minimum Bid Price 
		 Client client = Client.create();
		 WebResource webResource = client.resource("http://localhost:8085/BuyerandPaymentManagement/BuyerandPaymentService/Bid/LowestBid/" +ProductId);
		 ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
		 String queryResponse = response.getEntity(String.class);
		 System.out.println("1" + queryResponse);
		 double minimumBidPrice = Double.parseDouble(queryResponse);
		 
		 //Call the Own API to return to Minimum Product Price 
		 Client client1 = Client.create();
		 WebResource webResource1 = client1.resource("http://localhost:8085/ProductAndSellerManagementService/ProductService/Product/readProductMinimumPrice/" +ProductId);
		 System.out.println(ProductId);
		 ClientResponse response1 = webResource1.type("application/xml").get(ClientResponse.class);
		 String queryResponse1 = response1.getEntity(String.class);
		 System.out.println("2" + queryResponse1);
		 double minimumProductPrice = Double.parseDouble(queryResponse1);
		
		 //validate the entered product price before the update
		 
		 //first validate - validate with inserted product price greater than to the return minimum product price(From the Product table)
		 if(minimumProductPrice < insertedProductPrice) {
			 
			//Second validate - validate with inserted product price less than to the return minimum Bid price(From the Bid table)
			 if(minimumBidPrice > insertedProductPrice) {
				 
				 //Call the Update method to update minimum price
				 return productObj.updateCurrentProductPrice(ProductId, productPrice);
			 }
			 else {
				 
				 //Generate error when Inserted Product Minimum Price greater than to the Buyers Minimum Bid Price
				 return "Error in Inserted Product Minimum Price greater than to the Buyers Minimum Bid Price";
			 }
		 }
		 else {
			 
			 //Generate error when Inserted Product Minimum Price greater than to the Buyers Minimum Bid Price
			 return "Error in Inserted Product Minimum Price less than to the Previous Minimum Product Price ";
		 }
		
		 		 	
	 }
	
	
}
