package model;

import java.sql.*;

public class FundingBoady {

	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/funds_and_fundingbody_managment", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();
	 
	 } 
	 return con; 
	 }
	
	
	//Insert Funding body details
	public String insertFundingBody(String name,String email,String address,String phone,String interestArea, String fund_range) {
		
		String output = "";
		
		try
		 { 
		 Connection con = connect(); 
		
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 // create a prepared statement
		 String query = "insert into fundingbody (`idFundingBody`,`name`,`email`,`address`,`phone`,`interestArea`,`fund_range`)"+ "values (?,?,?,?,?,?,?)"; 
	
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, email); 
		 preparedStmt.setString(4, address);
		 preparedStmt.setInt(5, Integer.parseInt(phone));  
		 preparedStmt.setString(6, interestArea);
		 preparedStmt.setDouble(7, Double.parseDouble(fund_range));
		 
		
		 //execute statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted funding body details successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the funding body details."; 
		 System.err.println(e.getMessage()); 
		 }
		 
		 
		return output;
		
		
	}
	
	
	//read funding body details
	
	
	public String readFundingBodies() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Name</th><th>Email</th>" +
	 "<th>Address</th>" + 
	 "<th>Phone number</th>" +
	 "<th>Interest Area</th>" +
	 "<th>Fund Range</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from fundingbody"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String stakeholderID = Integer.toString(rs.getInt("idFundingBody")); 
	 String name = rs.getString("name"); 
	 String email = rs.getString("email"); 
	 String address = rs.getString("address"); 
	 String phone = Integer.toString(rs.getInt("phone")); 
	 String interestArea = rs.getString("interestArea"); 
	 String fund_range = Double.toString(rs.getDouble("fund_range"));    
	 // Add into the html table
	 output += "<tr><td>" + name + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + address + "</td>"; 
	 output += "<td>" + phone + "</td>"; 
	 output += "<td>" + interestArea + "</td>";
	 output += "<td>" + fund_range + "</td>"; 
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='stakeholderID' type='hidden' value='" + stakeholderID 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the funding bodies ."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	//update funding body details
	
	public String updateFundingBody(String idFundingBody,String name,String email,String address,String phone,String interestArea, String fund_range) {
		
		String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE fundingbody SET name=?,email=?,address=?,phone=?,interestArea=?,fund_range=? WHERE idFundingBody=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, email);
		 preparedStmt.setString(3, address);
		 preparedStmt.setInt(4, Integer.parseInt(phone));
		 preparedStmt.setString(5, interestArea); 
		 preparedStmt.setInt(6, Integer.parseInt(fund_range));
		 preparedStmt.setInt(7, Integer.parseInt(idFundingBody)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating funding body details..."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		
	}
	
	

	
	public String deleteFundingBody(String fundingBodyID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from fundingbody where `idFundingBody`=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(fundingBodyID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the funding body detail."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	//method for retrieve all funding bodies details when researcher request
	
	public String RequestReadFundingBodies(String InterestArea) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Funding Body ID</th><th>Name</th>" +
	 "<th>Email</th>" + 
	 "<th>Address</th>" + 
	 "<th>Phone number</th>" +
	 "<th>Interest Area</th>" +
	 "<th>Fund Range</th>" ; 
	 
	 String query = "select * from fundingbody order by interestArea = ? desc"; 
	 
	 PreparedStatement preparedStatement = con.prepareStatement(query);
	 preparedStatement.setString(1, InterestArea); 
	 ResultSet rs = preparedStatement.executeQuery();
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String stakeholderID = Integer.toString(rs.getInt("idFundingBody")); 
	 String name = rs.getString("name"); 
	 String email = rs.getString("email"); 
	 String address = rs.getString("address"); 
	 String phone = Integer.toString(rs.getInt("phone")); 
	 String interestArea = rs.getString("interestArea"); 
	 String fund_range = Double.toString(rs.getDouble("fund_range"));    
	 // Add into the html table
	 output += "<tr><td>" + stakeholderID + "</td>"; 
	 output += "<td>" + name + "</td>";
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + address + "</td>"; 
	 output += "<td>" + phone + "</td>"; 
	 output += "<td>" + interestArea + "</td>";
	 output += "<td>" + fund_range + "</td>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the funding bodies ."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	

	
}
