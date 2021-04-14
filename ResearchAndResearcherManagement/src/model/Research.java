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

}
