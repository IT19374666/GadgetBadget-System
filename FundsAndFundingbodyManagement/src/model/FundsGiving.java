package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class FundsGiving {
	

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



	//insert funds table specific columns when a research is accepted by a funding body
			public String insertStartupFundsDetails(String researchID,String fundingBodyID,String currentStage,String fundsForCurrentStage,String totalFunds, String description) {
				
				String output = "";
				
				try
				 { 
				 Connection con = connect(); 
				
				 if (con == null) 
				 {return "Error while connecting to the database for inserting funds details."; } 
				 // create a prepared statement
				 String query = "insert into funds (`FundID`,`researchID`,`fundingBodyID`,`currentStage`,`fundsForCurrentStage`,`totalFunds`,`description`)"+ "values (?,?,?,?,?,?,?)"; 
			
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setInt(1, 0); 
				
				 preparedStmt.setInt(2, Integer.parseInt(researchID)); 
				 preparedStmt.setInt(3, Integer.parseInt(fundingBodyID)); 
				 preparedStmt.setInt(4, Integer.parseInt(currentStage));;
				 preparedStmt.setDouble(5, Double.parseDouble(fundsForCurrentStage));
				 preparedStmt.setDouble(6, Double.parseDouble(totalFunds));
				 preparedStmt.setString(7, description); 
				 
				 
				
				 //execute statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Inserted funds details for start up resaerches successfully"; 
				 } 
				 catch (Exception e) 
				 { 
				 output = "Error while inserting the funds details for start up resaerches."; 
				 System.err.println(e.getMessage()); 
				 }
				 
				 
				return output;
				
				
			}
			
			//update method
	
			public String updateFunds(String ReserachID,int updateStage,Double totalFundedUptoNow)
		      {
		          String output = "";
		          try {
		            Connection con = connect();
		            if (con == null){
		                return "Error while connecting to the database";
		            }
		            // create a prepared statement
		            String query = "update funds set `currentStage`=? ,`totalFundedAmount`=? where `researchID` = ?";
		            
		            PreparedStatement preparedStmt = con.prepareStatement(query);
		            
		            // binding values
		            preparedStmt.setInt(1, updateStage);
		            preparedStmt.setDouble(2, totalFundedUptoNow);
		            preparedStmt.setInt(3, Integer.parseInt(ReserachID));

		 

		            //execute the statement
		            preparedStmt.executeUpdate();
		            con.close();
		            output = "Currently funded stage and total funded amount upto now stage for researchID = "+ReserachID+" is Updated successfully";
		        }catch (Exception e) {
		            output = "Error while updating  Current funded stage and total funded amount";
		            System.err.println(e.getMessage());
		         }
		         
		        return output;
		        
		        
		    }

			////////////change start
			public String readFundAmoutForOneStage(String ResearchID)
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

		 

		                    String query = "select fundsForCurrentStage from funds where researchID = ?";

		 

		                    PreparedStatement preparedStatement = con.prepareStatement(query);
		                    preparedStatement.setString(1, ResearchID);
		                    ResultSet rs = preparedStatement.executeQuery();
		                    // iterate through the rows in the result set
		                    while (rs.next())
		                    {
		                        String fundForOneStage = Double.toString(rs.getDouble("fundsForCurrentStage")); 
		                        // Add into the html table
		                        output =  fundForOneStage;
		                    }
		                    con.close();
		                    // Complete the html table
		          }
		          catch (Exception e)
		          {
		              output = "Error while reading the fund amount for one stage.";
		              System.err.println(e.getMessage());
		          }
		          return output;
		      }
			


			
			
		
	
	
	
	
}
