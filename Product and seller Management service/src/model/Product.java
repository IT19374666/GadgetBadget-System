package model;

import java.sql.*; 

public class Product {
	
	/////////////////////////////// DB Connection ///////////////////////////////
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/product_and_seller_management", "root", ""); 
	 } 
	 catch (Exception e) 
	 {
		 e.printStackTrace();
	 } 
	 return con; 
	 } 
	
	
	////////////////Insert Product///////////////////////////////
	
	public String insertProduct(String productName, String ProductType, String productPrice, String productDescription,String productAddDate,String productEndDate,String SellerID) 
	{ 
		String output = "";

		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting."; 
			} 
			
			// create a prepared statement
			String query1 = " insert into product(`ProductID`,`ProductName`,`ProductType`,`MinimumPrice`,`ProductDescription`,`AddDate`,`ClosingDate`,`SellerID`) "+
							" values (?, ?, ?, ?, ?, ?, ?,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query1); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, productName);
			preparedStmt.setString(3, ProductType);
			preparedStmt.setDouble(4, Double.parseDouble(productPrice)); 
			preparedStmt.setString(5, productDescription);
			preparedStmt.setString(6, productAddDate);
			preparedStmt.setString(7, productEndDate);
			preparedStmt.setString(8, SellerID);
			
			// execute the statement3
			preparedStmt.execute(); 
			con.close(); 
			output = "Product Inserted successfully "; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the Product."; 
			System.err.println(e.getMessage());
		} 
		return output; 
	}
	
	
	///////////// read all product ////////////////
	
	public String readAllProduct() 
	{ 
		String output = ""; 
		try
		{
			Connection con = connect(); 
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Product ID</th><th>Product Name</th>" +"<th>Product Type</th>" +"<th>Product Price</th>" + 
					 "<th>Product Description</th>" +"<th>Product Add Date</th>" +"<th>Product End Date</th>" +
					 "<th>Update</th><th>Delete</th></tr>"; 
	 
			String query = "select * from product"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String productID = Integer.toString(rs.getInt("ProductID")); 
				String productName = rs.getString("ProductName"); 
				String ProductType = rs.getString("ProductType");
				String productPrice = Double.toString(rs.getDouble("MinimumPrice"));
				String productDescription = rs.getString("ProductDescription");
				String productAddDate = rs.getString("AddDate"); 
				String productEndDate = rs.getString("ClosingDate"); 
				
				// Add into the html table
				output += "<tr><td>" + productID + "</td>"; 
				output += "<td>" + productName + "</td>"; 
				output += "<td>" + ProductType + "</td>"; 
				output += "<td>" + productPrice + "</td>";
				output += "<td>" + productDescription + "</td>"; 
				output += "<td>" + productAddDate + "</td>"; 
				output += "<td>" + productEndDate + "</td>"; 
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"
					   + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
					   + "<input name='productID' type='hidden' value='" + productID + "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			
			// Complete the html table
			output += "</table>"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while reading the products."; 
				System.err.println(e.getMessage()); 
			} 
		return output; 
	 }
	
	//////////////////////////////// Upadate Item //////////////////////////////
	
	public String updateProduct(String ProductID, String productName, String ProductType, String productPrice, String productDescription,String productAddDate,String productEndDate)
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; 
			} 
			
			// create a prepared statement
			String query = "UPDATE product SET `ProductName`=?,`ProductType`=?,`MinimumPrice`=?,`ProductDescription`=?,`AddDate`=?,`ClosingDate`=? WHERE `ProductID`=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setString(1, productName); 
			preparedStmt.setString(2, ProductType); 
			preparedStmt.setDouble(3, Double.parseDouble(productPrice)); 
			preparedStmt.setString(4, productDescription); 
			preparedStmt.setString(5, productAddDate);
			preparedStmt.setString(6, productEndDate);
			preparedStmt.setInt(7, Integer.parseInt(ProductID)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Product Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the Product."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 
	}
	
	/////////////////////////// Delete Item ///////////////////////////////////////////
	
	public String deleteProduct(String productID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for deleting."; 
			} 
			
			// create a prepared statement
			String query = "delete from product where `ProductID`=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(productID)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Product Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the Product."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	///////////////////// return product Type /////////////////////////////////
	
	  public String readProductTypes(String ProductType)
	  {
	     String output = "";
	     try
	     {
	     Connection con = connect();
	     if (con == null)
	     {return "Error while connecting to the database for reading."; }
	     // Prepare the html table to be displayed
	     output = "<table border='1'><tr><th>Product ID</th><th>Product Name</th>" +
	     "<th>Product Type</th>" +
	     "<th>Minimum Price</th>" +
	     "<th>Prodyct Description</th>" +
	     "<th>Add Date</th>" +
	     "<th>End Date</th>" ;
	    
	     String query = "select * from product order by ProductType = ? desc";
	    
	     PreparedStatement preparedStatement = con.prepareStatement(query);
	     preparedStatement.setString(1, ProductType);
	     ResultSet rs = preparedStatement.executeQuery();
	     // iterate through the rows in the result set
	     while (rs.next())
	     {
	     String productID = Integer.toString(rs.getInt("ProductID"));
	     String productName = rs.getString("ProductName");
	     String productType = rs.getString("ProductType");
	     String minimumPrice = Double.toString(rs.getDouble("MinimumPrice")); 
	     String productDescription = rs.getString("ProductDescription");
	     String addDate = rs.getString("AddDate");
	     String endDate = rs.getString("ClosingDate");   
	     // Add into the html table
	     output += "<tr><td>" + productID + "</td>";
	     output += "<td>" + productName + "</td>";
	     output += "<td>" + productType + "</td>";
	     output += "<td>" + minimumPrice + "</td>";
	     output += "<td>" + productDescription + "</td>";
	     output += "<td>" + addDate + "</td>";
	     output += "<td>" + endDate + "</td>";
	     }
	     con.close();
	     // Complete the html table
	     output += "</table>";
	     }
	     catch (Exception e)
	     {
	     output = "Error while reading the Product table .";
	     System.err.println(e.getMessage());
	     }
	     return output;
	     }
	  
	  
	  ////////////////////////////////// return product minimum value //////////////////////////////	
	  public String readProductMinimumValue(String ProductID)
	  {
		  String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
			  {
				  return "Error while connecting to the database for reading."; }
			  	  // Prepare the html table to be displayed
			  	  //output = "<table border='1'><tr> <th>Minimum Price</th></tr>";

			  	  String query = "select ProductID,ProductName,MinimumPrice from product where ProductID = ?";

			  	  PreparedStatement preparedStatement = con.prepareStatement(query);
			  	  preparedStatement.setString(1, ProductID);
			  	  ResultSet rs = preparedStatement.executeQuery();
			  	  // iterate through the rows in the result set
			  	  while (rs.next())
			  	  {
			  		  String minimumPrice = Double.toString(rs.getDouble("MinimumPrice")); 
			  		  // Add into the html table
			  		  output +=  minimumPrice;
			  	  }
			  	  con.close();
			  	  // Complete the html table
			  	  output += "</table>";
		  }
		  catch (Exception e)
		  {
			  output = "Error while reading the Product table Minumum price.";
			  System.err.println(e.getMessage());
		  }
		  return output;
	  }
	
	  
	  ////////////////////////////////// update product Status//////////////////////////////	
	  public String updateSoldProduct(String ProductID,String CustomerID)
	  {
		  String output = "";
		  String status = "Sold";
		  try {
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "update product set `ProductStatus`=?,`CustomerID` = ?  where `ProductID` = ?";		
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, status);
			preparedStmt.setString(2, CustomerID);
			preparedStmt.setInt(3, Integer.parseInt(ProductID));

			//execute the statement
			preparedStmt.executeUpdate();
			con.close();
			output = "Product Status Updated successfully";
		}catch (Exception e) {
			output = "Error while updating Product Status";
			System.err.println(e.getMessage());
	 	}
	 	
		return output;
		
		
	}
}
