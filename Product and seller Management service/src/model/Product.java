package model;

import java.sql.*; 

public class Product {
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/product_and_seller_management", "root", ""); 
	 } 
	 catch (Exception e) 
	 {
		 e.printStackTrace();
	 } 
	 return con; 
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
				String productPrice = Double.toString(rs.getDouble("Minimum Price"));
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
}
