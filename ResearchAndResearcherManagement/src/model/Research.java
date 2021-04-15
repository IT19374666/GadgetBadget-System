package model;
import java.sql.*; 

public class Research {
	
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
	
	public String insertResearch(String res_topic, String res_area, String status, String progress, String res_ID) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database to add a Researcher";
			}
			
			//creating a prepared statement
			String query = "INSERT INTO research(research_ID,research_topic,research_area,status,progress,stakeholder_ID)"
						+ " VALUES(?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2, res_topic);
			preparedStmt.setString(3, res_area);
			preparedStmt.setString(4, status);
			preparedStmt.setString(5, progress);
			preparedStmt.setInt(6, Integer.parseInt(res_ID));
			
			
			//execute the statement
			
			preparedStmt.execute();
			con.close();
			
			output = "Reasearch added successfully";
		}
		catch(Exception e) {
			output = "Error while adding new research";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readResearches() {
		String output = "";
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for reading"; 
			}
			//prepare the html table
			output = "<table border='1'>"
					+ "<tr>"
					+ "<th>Research ID</th>"
					+ "<th>Research Topic</th>"
					+ "<th>Research Area</th>"
					+ "<th>Status</th>"
					+ "<th>Progress</th>"
					+ "<th>Stakeholder ID</th>"
					+ "<th>Name</th>"
					+ "<th>Email</th>"
					+ "<th>Phone</th>"
					+ "</tr>";
					
			
			//String query = "SELECT * FROM research";
			String query = "SELECT * FROM research research, researcher res WHERE research.stakeholder_ID = res.stakeholder_ID";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//iterating through the rows in rs
			while(rs.next()) {
				String resID = Integer.toString(rs.getInt("research_ID"));
				String topic = rs.getString("research_topic");
				String area = rs.getString("research_area");
				String status = rs.getString("status");
				String progress = rs.getString("progress");
				String stkID = rs.getString("stakeholder_ID");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone_no");
				
				
				//add into the table
				output += "<tr><td>" +resID+ "</td>";
				output += "<td>" +topic+ "</td>";
				output += "<td>" +area+ "</td>";
				output += "<td>" +status+ "</td>";
				output += "<td>" +progress+ "</td>";
				output += "<td>" +stkID+ "</td>";
				output += "<td>" +name+ "</td>";
				output += "<td>" +email+ "</td>";
				output += "<td>" +phone+ "</td></tr>";
				
				
			}
			con.close();
			output += "</table>";
		}
		catch(Exception e) {
			output = "Error while reading research data";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String updateResearch(String ID, String topic, String area, String status, String progress) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null) {
				return "Error while connecting to the database for updating";
				
			}
			//create prepared statement
			String query = "UPDATE research SET research_topic=?, research_area=?, status=?, progress=? WHERE research_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			
			preparedStmt.setString(1, topic);
			preparedStmt.setString(2, area);
			preparedStmt.setString(3, status);
			preparedStmt.setString(4, progress);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
			
		}
		catch(Exception e) {
			output = "Error while updating the record";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteResearch(String ID) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the dtabase for deleting";
			}
			
			//create a prepared statement 
			String query = "DELETE FROM research WHERE research_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, Integer.parseInt(ID));
			
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch(Exception e) {
			output = "Error while deleting the record";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
