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
	 public String readItems() {
		 return bidObj.readBids();
	  }

}


