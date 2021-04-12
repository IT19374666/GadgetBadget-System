package com;

import model.Product; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

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
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct(
			@FormParam("ProductName") String ProductName, 
			@FormParam("ProductType") String ProductType, 
			@FormParam("MinimumPrice") String MinimumPrice, 
			@FormParam("ProductDescription") String ProductDescription,
			@FormParam("AddDate") String AddDate,
			@FormParam("ClosingDate") String ClosingDate) 
	{
	 		String output = productObj.insertProduct(ProductName, ProductType, MinimumPrice, ProductDescription, AddDate, ClosingDate); 
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
}
