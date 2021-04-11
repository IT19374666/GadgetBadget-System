package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author IT19374666
 *
 */
public class Payment {
	//Create connection
		public Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/buyerandpaymentdb","root", "");
				 	
			//For testing
				 System.out.print("Successfully connected");
			
			} catch(Exception e){
					e.printStackTrace();
			}

				
			return con;
		}
		
	//Add a payment
	public String insertPayment(String itemCode,String customerId, String amount, String pMethod, String cardNo,String paymentDate) {
		
		String output = "";
		System.out.println(itemCode);
		try {
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into payments(`itemCode`,`customerId`,`amount`,`pMethod`,`cardNo`,`paymentDate`)"
				+ " values (?, ?, ?, ?, ?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
		
			preparedStmt.setString(1, itemCode);
			preparedStmt.setString(2, customerId);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setString(4, pMethod);
			preparedStmt.setString(5, cardNo); 
			preparedStmt.setString(6, paymentDate); 

			//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}catch (Exception e) {
				output = "Error while inserting";
				System.err.println(e.getMessage());
		}
		 	
		return output;
	}
	
	//select  payments for a customer
			public String getPayment(String customerId) {
				String output = "";
				
				try {
					Connection con = this.connect();
					if (con == null) {
						return "Error while connecting to retrieving payment details";
					}		
					
					String query = "select * from payments where customerId = ?";
					PreparedStatement preparedStatement = con.prepareStatement(query);
					
					preparedStatement.setString(1, customerId);
					ResultSet rs = preparedStatement.executeQuery();
					
					if (rs.next()) {	
						String payId = rs.getString("payid");
						String itemCode = rs.getString("itemCode");
						customerId = rs.getString("customerId");
						String amount = Double.toString(rs.getDouble("Amount"));
						String pMethod = rs.getString("pMethod");
						String cardNo = rs.getString("cardNo");
						String paymentDate = rs.getString("paymentDate");
						
						output = "<form method='post' action='items.jsp'>";	
						output += "<h2>Payment ID: " + payId + "</h2>";
						output += "Item code: <input name=\"itemCode\" type=\"text\" value=\""+ itemCode + "\" class=\"form-control\"><br>";
						output += "Customer Id: <input name=\"amount\" type=\"text\" value=\""+ amount + "\" class=\"form-control\"><br>";
						output += "Bid Amount: <input name=\"payMethod\" type=\"text\" value=\""+ pMethod + "\" class=\"form-control\"><br>";
						output += "Special Conditions: <input name=\"cardNo\" type=\"text\" value=\""+cardNo+ "\" class=\"form-control\"><br>";
						output += "Due date: <input name=\"payDate\" type=\"text\" value=\""+paymentDate+ "\" class=\"form-control\"><br>";
						output += "<input name='payID' type='hidden' value='" +payId+"'>";
					
					

					}
					
					con.close();			
					
				} catch (Exception e) {
					output = "Error while getting Payment details ";
					System.err.println(e.getMessage());
				}
				
				return output;
			}
	


	
}
