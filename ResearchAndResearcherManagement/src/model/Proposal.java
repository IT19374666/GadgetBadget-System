package model;

import java.sql.*; 

public class Proposal {
	
	private Connection connect() {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researchandresearcher", "root", "");
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String insertProposal(String desc, String date, String resID) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database to add a Proposal";
			}
			
			//creating a prepared statement
			String query = "INSERT INTO proposal(proposal_ID,description,submit_date,research_ID)"
						+ " VALUES(?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2, desc);
			preparedStmt.setDate(3, Date.valueOf(date));
			preparedStmt.setInt(4, Integer.parseInt(resID));
			
			
			//execute the statement
			
			preparedStmt.execute();
			con.close();
			
			output = "Proposal added successfully";
		}
		catch(Exception e) {
			output = "Error while adding new proposal";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
