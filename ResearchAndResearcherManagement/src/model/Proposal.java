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
	
	public String readProposals() {
		String output = "";
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for reading"; 
			}
			//prepare the html table
			output = "<table border='1'>"
					+ "<tr>"
					+ "<th>Propoosal ID</th>"
					+ "<th>Description</th>"
					+ "<th>Submit date</th>"
					+ "<th>Research topic</th>"
					+ "<th>Researcher name</th>"
					+ "<th>Email</th>"
					+ "<th>Phone</th>"
					+ "</tr>";
					
			
			//String query = "SELECT * FROM research";
			String query = "SELECT * FROM proposal p,research res,researcher reschr where p.research_ID = res.research_ID and res.stakeholder_ID = reschr.stakeholder_ID";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//iterating through the rows in rs
			while(rs.next()) {
				String propID = Integer.toString(rs.getInt("proposal_ID"));
				String desc = rs.getString("description");
				String date = rs.getString("submit_date");
				String topic = rs.getString("research_topic");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone_no");
				
				
				//add into the table
				output += "<tr><td>" +propID+ "</td>";
				output += "<td>" +desc+ "</td>";
				output += "<td>" +date+ "</td>";
				output += "<td>" +topic+ "</td>";
				output += "<td>" +name+ "</td>";
				output += "<td>" +email+ "</td>";
				output += "<td>" +phone+ "</td></tr>";

				
				
			}
			con.close();
			output += "</table>";
		}
		catch(Exception e) {
			output = "Error while reading proposal data";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
