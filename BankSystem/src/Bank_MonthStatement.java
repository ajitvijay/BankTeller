import javax.swing.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import oracle.net.aso.e;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Bank_MonthStatement {
	private DatabaseConnection db;
	
	public Bank_MonthStatement() {
		
		this.db = new DatabaseConnection();
		
		final JFrame frame = new JFrame("Generate Customer's Monthly Statement");
		frame.setSize(500,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel panel = new JPanel(); // 
		final JLabel cust = new JLabel("Enter Customer ID: ");
		final JTextField cust_input = new JTextField(10);
		panel.add(BorderLayout.NORTH,cust); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,cust_input);
		
		final JPanel enterpanel = new JPanel();
		final JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String customer_id = cust_input.getText();
				//System.out.print("Customer Monthly Statement for the customer id: " + customer_id);
				frame.dispose();
				new Bank_Options();
				generate_monthly_statement(customer_id, db);
			}
        });
        enterpanel.add(BorderLayout.SOUTH,enter);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, enterpanel);
		frame.setVisible(true);
		
	}
	
	public void generate_monthly_statement(String customer_id, DatabaseConnection db) {
		try {
			/*
			 * Given a customer
			 * 		For all accounts owned (include names and addresses of other owners)
			 * 				Generate List of all transactions from last month
			 * 					Print the FIRST balance of the month, print the LAST balance of the month
			 * 				For all accounts owned, calculate the sum, and print message if it is over $100,000
			 * 
			 */
			
			System.out.println("GENERATING MONTHLY STATEMENT for Customer: " + customer_id + "\n");
			
			
			System.out.println("-- Accounts owner by this customer and it's other owners: \n"); 
			String query = "SELECT Customer.owner_name, Customer.address, AccountCustomer.account_id FROM Customer , AccountCustomer "
					+ "WHERE Customer.tax_id = ( SELECT customer_id FROM AccountCustomer WHERE account_id = "
					+ "(SELECT account_id FROM AccountCustomer WHERE customer_id = '" + customer_id + "')) AND Customer.tax_id = AccountCustomer.customer_id"; 
			
			ResultSet rs = db.querySelect(query);
		    while(rs.next()){	    
		        //Retrieve by column name
		        String owner_name = rs.getString("owner_name");
		        String address = rs.getString("address");
		        String account_id = rs.getString("account_id");
		       
		        System.out.print("For the account id: " + account_id.trim() + ", Name: " + owner_name.trim() + " [Address: " + address.trim() + "] is an owner.");
		    }
		    rs.close();
		    
		    System.out.println("\n\n");
		    
		    //transaction generator for all accounts that the Customer owns
		    System.out.println("-- All transaction statements for all accounts owns by this Customer: \n"); 
		    String query2 = "SELECT transaction_date, old_balance, new_balance, account_id, customer_id, transaction_info FROM Transaction WHERE account_id = ( SELECT account_id FROM AccountCustomer WHERE customer_id = '" + customer_id + "')";
		    ResultSet rs1 = db.querySelect(query2);
		    while(rs1.next()){	    
		        //Retrieve by column name
		        Date transaction_date = rs1.getDate("transaction_date");
		        String old_balance = rs1.getString("old_balance");
		        String new_balance = rs1.getString("new_balance");
		        String account_id = rs1.getString("account_id");
		        String cust_id = rs1.getString("customer_id");
		        String transaction_info = rs1.getString("transaction_info");
		        
		        DateFormat date_formatted = new SimpleDateFormat("yyyy-MM-dd");  
                String str_date = date_formatted.format(transaction_date);  
		        
		        System.out.println("TRANSACTION: " + transaction_info + ", DATE: " + str_date + ", ACCOUNT_ID: " + account_id + ", CUSTOMER_ID: " + cust_id + ", Old BAL: " + old_balance + ", NEW BAL: " + new_balance);
		    }
		    rs1.close();
		    
		    
		    //Calculate the sum for all accounts owned by this customer
		    String query4 = "SELECT SUM(Account.balance) FROM AccountCustomer, Account WHERE Account.account_id = AccountCustomer.account_id AND AccountCustomer.customer_id = '" + customer_id + "')";
		    ResultSet rs2 = db.querySelect(query2);
		    while(rs2.next()){	 
		    	int sum = rs2.getInt("sum");
		    	System.out.println("-- For customer_id: " + customer_id + " the sum of all balances of their accounts is: " + sum + ". Warning, sum of over $100,000 will result in more insurance!");
		    }
		    rs2.close();
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
}
