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

/*import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;*/

@Path("/Product")
public class ProductService {
	Product productObj = new Product();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	//call read all product method
	public String readProduct() 
	 { 
		return productObj.readAllProduct();
	 }
	
	
	@POST
	@Path("/Insert/{SellerID}") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct(
			@FormParam("ProductName") String ProductName, 
			@FormParam("ProductType") String ProductType, 
			@FormParam("MinimumPrice") String MinimumPrice, 
			@FormParam("ProductDescription") String ProductDescription,
			@FormParam("AddDate") String AddDate,
			@FormParam("ClosingDate") String ClosingDate,
			@PathParam("SellerID") String SellerID)
	{
	 		String output = productObj.insertProduct(ProductName, ProductType, MinimumPrice, ProductDescription, AddDate, ClosingDate,SellerID); 
	 		return output;
	}
	
	
	@PUT
	@Path("/") 
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
	 String productPrice = pObject.get("MinimumPrice").getAsString(); 
	 String productDescription = pObject.get("ProductDescription").getAsString();
	 String productAddDate = pObject.get("AddDate").getAsString();
	 String productEndDate = pObject.get("ClosingDate").getAsString();
	 
	 String output = productObj.updateProduct(productID, productName, productType, productPrice, productDescription, productAddDate, productEndDate);
	 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String productData) 
	{ 
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <ProductID>
	 String productID = doc.select("ProductID").text(); 
	 String output = productObj.deleteProduct(productID); 
	 return output; 
	}
	
	
	// Intercommunication
	@PUT
	@Path("/acceptBid/{BID}") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String acceptBid(@PathParam(value = "BID")String bidId) 
	{
		//interprocess communication
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
	
	
	//intercommunication - 
    @GET
    @Path("/readProductType/{productType}") 
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML) 
    //call read all product method
    public String readProduct( @PathParam(value = "productType")String ProductType)
    {
        return productObj.readProductTypes(ProductType);
    }
    
    
  //intercommunication - for return product minimum value
    @GET
    @Path("/readProductMinimumPrice/{productID}") 
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML) 
    //call read all product method
    public String readProductMinimumPrice( @PathParam(value = "productID")String ProductID)
    {
        return productObj.readProductMinimumValue(ProductID);
    }
    
    
    //Intercommunication - use API with client for validate to minimum value 
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
    
    //Update product table as product is sold
    @PUT
	 @Path("/updateStatus/{productId}/{CustomerID}")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateBidtoAccepted( @PathParam(value = "productId")String ProductId,@PathParam(value = "CustomerID")String customerID) {
		 
		 	return productObj.updateSoldProduct(ProductId,customerID);	 	
	 }
	
    
    //Update product table minimum price
     @PUT
	 @Path("/updateProductMinimumPrice/{productId}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateProductMinimumPrice( @PathParam(value = "productId")String ProductId,String ProductPriceData) {
    	 
    	//Convert the input string to a JSON object 
		 JsonObject pObject = new JsonParser().parse(ProductPriceData).getAsJsonObject(); 
		 
		//Read the values from the JSON object
		 String productPrice = pObject.get("MinimumPrice").getAsString();
		 double insertedProductPrice = Double.parseDouble(productPrice);
    	
    	Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8085/BuyerandPaymentManagement/BuyerandPaymentService/Bid/LowestBid/" +ProductId);
		ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
		String queryResponse = response.getEntity(String.class);
		System.out.println("1" + queryResponse);
		double minimumBidPrice = Double.parseDouble(queryResponse);
		
		Client client1 = Client.create();
		WebResource webResource1 = client1.resource("http://localhost:8085/ProductAndSellerManagementService/ProductService/Product/readProductMinimumPrice/" +ProductId);
		System.out.println(ProductId);
		ClientResponse response1 = webResource1.type("application/xml").get(ClientResponse.class);
		String queryResponse1 = response1.getEntity(String.class);
		System.out.println("2" + queryResponse1);
		double minimumProductPrice = Double.parseDouble(queryResponse1);
		
		
		 
		 if(minimumProductPrice < insertedProductPrice) {
			 if(minimumBidPrice > insertedProductPrice) {
				 return productObj.updateCurrentProductPrice(ProductId, productPrice);
			 }
			 else {
				 return "Inserted Product Minimum Price greater than to the Buyers Minimum Bid Price";
			 }
		 }
		 else {
			 return "Inserted Product Minimum Price less than to the Previous Minimum Product Price ";
		 }
		
		 		 	
	 }
	
	
}
