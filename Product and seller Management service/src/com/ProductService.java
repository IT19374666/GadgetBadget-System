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
	
}
