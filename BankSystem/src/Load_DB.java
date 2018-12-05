import java.sql.*; 
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class Load_DB{

	public static void main (String [] args) {
		DatabaseConnection db = new DatabaseConnection(); 
		
		//fill_accounts_table("input_csv/accounts.csv", db);
		//fill_customers_table("input_csv/customers2.csv", db);
		fill_accountcustomers_table("input_csv/accountcustomers.csv",db);
		//fill_banktellers_table("input_csv/banktellers.csv",db);
	}
	
	public static void fill_accounts_table(String filename, DatabaseConnection db) {
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
	    		  
	    		  String query = "INSERT INTO Account (branch_name, account_id, account_type, balance,"
	    		  		+ "interest_rate, account_status, closed_date, current_month_interest_added, pocket_monthly_fee,"
	    		  		+ "pocket_linked_account_id) VALUES (" + branch_name + "," + account_id + "," + account_type + "," 
	    		  		+ balance + "," + interest_rate + "," + account_status + "," + closed_date + "," + 
	    		  		current_month_interest_added + "," + pocket_monthly_fee + "," + pocket_linked_account_id + "); ";
	    		  
//	    		  String query = "INSERT INTO Account (branch_name, account_id, account_type, balance, "
//		    		  		+ "interest_rate, account_status, closed_date, current_month_interest_added, pocket_monthly_fee,"
//		    		  		+ "pocket_linked_account_id) VALUES ('test_branch','888','student_checking', 423523.50, 1.02, 'open', NULL, 'no', 0.0, NULL)";
	    		  
	    		  //db.query(query);
	    	  }
	      }
	      catch (IOException e) {
	    	  e.printStackTrace();
	      }
	}
	
	
	public static void fill_customers_table(String filename, DatabaseConnection db) {
		String line="";
	      
	      try (BufferedReader buff = new BufferedReader(new FileReader(filename))){
	    	  while((line = buff.readLine()) != null) {
	    		  String[] cols = line.split(",");
	    		  
	    		  String owner_name = cols[0];
	    		  String tax_id = cols[1];
	    		  String address = cols[2];
	    		  String pin = cols[3];
	    		  
	    		  String query = "INSERT INTO Customer (owner_name, tax_id, address, pin) VALUES ('" + owner_name + "','" + tax_id + "','" + address + "','" + pin + "') ";
	    		  System.out.println(query);
	    		  //String query = "INSERT INTO Customer (owner_name, tax_id, address, pin) VALUES ('test','test','test','test')";
	    		  
	    		  //db.query(query);
	    	  }
	      }
	      catch (IOException e) {
	    	  e.printStackTrace();
	      }
	}
	
	public static void fill_accountcustomers_table(String filename, DatabaseConnection db) {
		String line="";
	      
	      try (BufferedReader buff = new BufferedReader(new FileReader(filename))){
	    	  while((line = buff.readLine()) != null) {
	    		  String[] cols = line.split(",");
	    		  
	    		  String account_id = cols[0];
	    		  String customer_id = cols[1];
	    		  String account_type = cols[2];
	    		  String is_primary_owner = cols[3];
	    		  
	    		  String query = "INSERT INTO AccountCustomer (account_id, customer_id, account_type, is_primary_owner) VALUES (" + account_id + "," + customer_id + "," + account_type + "," + is_primary_owner + ") ";
	    		  //String query = "INSERT INTO AccountCustomer (account_id, customer_id, account_type, is_primary_owner) VALUES ('test','test','test','test')";
	    		  
	    		  db.queryUpdate(query);
	    		  System.out.println(query);
	    		  
	    	  }
	      }
	      catch (IOException e) {
	    	  e.printStackTrace();
	      }
	}
	
	public static void fill_banktellers_table(String filename, DatabaseConnection db) {
		String line="";
	      
	      try (BufferedReader buff = new BufferedReader(new FileReader(filename))){
	    	  while((line = buff.readLine()) != null) {
	    		  String[] cols = line.split(",");
	    		  
	    		  String full_name = cols[0];
	    		  String password = cols[1];
	    		  String teller_id = cols[2];
	    		  
	    		  String query = "INSERT INTO BankTellerAccounts (full_name, password, teller_id) VALUES ('" + full_name + "','" + password + "','" + teller_id + "') ";
	  
	    		  //db.query(query);
	    	  }
	      }
	      catch (IOException e) {
	    	  e.printStackTrace();
	      }
	}
	
}