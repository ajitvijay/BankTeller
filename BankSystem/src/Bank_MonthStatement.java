import javax.swing.*;
//import java.time.format.DateTimeFormatter;  
//import java.time.LocalDateTime;    
import oracle.net.aso.e;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Bank_MonthStatement {
	private DatabaseConnection db;
	
	public Bank_MonthStatement() {
		
		this.db = new DatabaseConnection();
		
		final JFrame frame = new JFrame("Generate Customer's Monthly Statement");
		frame.setSize(500,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel panel = new JPanel();
		final JLabel cust = new JLabel("Enter Customer ID: ");
		final JTextField cust_input = new JTextField(10);
		panel.add(BorderLayout.NORTH,cust); 
		panel.add(BorderLayout.AFTER_LINE_ENDS,cust_input);
		
		final JPanel enterpanel = new JPanel();
		final JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String customer_id = cust_input.getText();
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
			
			System.out.println("GENERATING MONTHLY STATEMENT for Customer: " + customer_id + "\n");
			
			System.out.println("-- Accounts owner by this customer and it's other owners' information: \n"); 
			
			String query = "SELECT Customer.owner_name, Customer.address, AccountCustomer.account_id FROM Customer, AccountCustomer "
					+ "WHERE AccountCustomer.customer_id = Customer.tax_id AND AccountCustomer.account_id IN "
					+ "(SELECT account_id FROM AccountCustomer WHERE customer_id = '" + customer_id + "') ORDER BY AccountCustomer.account_id";
			
			//System.out.println(query);
			ResultSet rs = db.querySelect(query);
		    while(rs.next()){	    
		        //Retrieve by column name
		    	System.out.println("test");
		        String owner_name = rs.getString("owner_name");
		        String address = rs.getString("address");
		        String account_id = rs.getString("account_id");
		       
		        System.out.println("For the account id: " + account_id + ", Customer Name: " + owner_name + " [Address: " + address.trim() + "] is an owner.");
		    }
		    rs.close();
		    
		    //transaction generator for all accounts that the Customer owns
		    System.out.println("-- All transaction statements for all accounts owns by this Customer: \n"); 
		    String query2 = "SELECT transaction_date, difference, account_id, customer_id, transaction_info FROM Transaction WHERE account_id IN ( SELECT account_id FROM AccountCustomer WHERE customer_id = '" + customer_id + "')";
		    ResultSet rs1 = db.querySelect(query2);
		    while(rs1.next()){	    
		        //Retrieve by column name
		        Date transaction_date = rs1.getDate("transaction_date");
		        String difference = rs1.getString("difference");
		        String account_id = rs1.getString("account_id");
		        String cust_id = rs1.getString("customer_id");
		        String transaction_info = rs1.getString("transaction_info");
		        
		        DateFormat date_formatted = new SimpleDateFormat("yyyy-MM-dd");  
                String str_date = date_formatted.format(transaction_date);  
		        
		        System.out.println("TRANSACTION: " + transaction_info + ", DATE: " + str_date + ", ACCOUNT_ID: " + account_id + ", CUSTOMER_ID: " + cust_id + ", DIFFERENCE: " + difference);
		    }
		    rs1.close();
		    
		    
		    //Calculate the sum for all accounts owned by this customer
		    String query4 = "SELECT SUM(Account.balance) AS sumbal FROM AccountCustomer, Account WHERE Account.account_id = AccountCustomer.account_id AND AccountCustomer.customer_id = '" + customer_id + "'";
		    ResultSet rs2 = db.querySelect(query4);
		    while(rs2.next()){	 
		    	int sum = rs2.getInt("sumbal");
		    	System.out.println("-- For customer_id: " + customer_id + " the sum of all balances of their accounts is: $" + sum);
		    	
		    	if(sum > 100000){
		    		System.out.println("**Warning, this sum is over $100,000 and will result in more insurance!**");
		    	}
		    	
		    	
		    }
		    rs2.close();
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
}
