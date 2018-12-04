import java.sql.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Load_DB{
	
	public static void main (String [] args) {
	
		try {
			Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		      System.out.println("Connected database successfully...");
		      
			  System.out.println("Creating Accounts table...");
			    stmt = conn.createStatement();
		      
		      populate_accounts_table("input_csv/accounts.csv");

		      
		
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
	    
		   }
	}
	
	public static void populate_accounts_table(String filename) {
		String line="";
	      
	      try (BufferedReader buff = new BufferedReader(new FileReader(filename))){
	    	  while((line = buff.readLine()) != null) {
	    		  String[] cols = line.split(",");
	    		  
	    		  String branch_name = cols[0];
	    		  String account_id = cols[1];
	    		  String account_type = cols[2];
	    		  String balance = cols[3];
	    		  String interest_rate = cols[4];
	    		  String account_status = cols[5];
	    		  String closed_date = cols[6];
	    		  String current_month_interest_added = cols[7];
	    		  String pocket_monthly_fee = cols[8];
	    		  String pocket_linked_account_id = cols[9];
	    		  
	    		  /*String query = "INSERT INTO Account (branch_name, account_id, account_type, balance, "
	    		  		+ "interest_rate, account_status, closed_date, current_month_interest_added, pocket_monthly_fee,"
	    		  		+ "pocket_linked_account) VALUES ('" + branch_name + "','" + account_id + "','" + account_type + "'," 
	    		  		+ balance + "," + interest_rate + ",'" + account_status + "'," + closed_date + ",'" + 
	    		  		current_month_interest_added + "'," + pocket_monthly_fee + ",'" + pocket_linked_account_id + ") ";
	    		  */
	    		  
	    		  String query = "INSERT INTO Account (branch_name, account_id, account_type, balance, "
		    		  		+ "interest_rate, account_status, closed_date, current_month_interest_added, pocket_monthly_fee,"
		    		  		+ "pocket_linked_account_id) VALUES ('xxxxx','265','student_checking', 3553.50, 1.02, 'open', NULL, 'no', 0.0, NULL)";
	    		  
	    		  ResultSet rs = stmt.executeQuery(query);
	    		  rs.close();
	    	  }
	      }
	      catch (IOException e) {
	    	  e.printStackTrace();
	      }
		
		
	
	}
	
	
}