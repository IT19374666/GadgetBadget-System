package model;
import java.sql.*; 
public class Researcher {
	
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
	
	public String insertResearcher(String name, String email, String address, String phone, String interest, String type) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database to add a Researcher";
			}
			
			//creating a prepared statement
			String query = "INSERT INTO researcher(stakeholder_ID,name,email,address,phone_no,interest_area,researcher_type)"
						+ " VALUES(?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, interest);
			preparedStmt.setString(7, type);
			
			//execute the statement
			
			preparedStmt.execute();
			con.close();
			
			output = "Reaseachr added successfully";
		}
		catch(Exception e) {
			output = "Error while adding new researcher";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	

}
