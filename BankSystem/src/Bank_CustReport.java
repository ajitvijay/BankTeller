import javax.swing.*;

import oracle.net.aso.e;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Bank_CustReport {
	private DatabaseConnection db;
	
	public Bank_CustReport() {
		
		this.db = new DatabaseConnection();
		
		JFrame frame = new JFrame("Generate Customer Report");
		frame.setSize(500,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(); // 
		JLabel cust = new JLabel("Enter Customer ID: ");
		JTextField cust_input = new JTextField(10);
		panel.add(BorderLayout.NORTH,cust); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,cust_input);
		
		JPanel enterpanel = new JPanel();
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String customer_id = cust_input.getText();
				System.out.print("Customer Report for the customer id: " + customer_id);
				frame.dispose();
				new Bank_Options();
				generate_customer_report(customer_id, db);
			}
        });
        enterpanel.add(BorderLayout.SOUTH,enter);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, enterpanel);
		frame.setVisible(true);
		
	}
	
	public void generate_customer_report(String customer_id, DatabaseConnection db) {
		try {
			//generate a list of all accounts associated with the particular customer, and whether they are open or closed
			
			String query = "SELECT Account.account_id, Account.account_status, Account.account_type FROM Account, AccountCustomer "
					+ "WHERE AccountCustomer.account_id = Account.account_id AND AccountCustomer.customer_id = '" + customer_id + "'";
			
			ResultSet rs = db.query(query);
			
			System.out.println("GENERATING CUSTOMER REPORT for Customer: " + customer_id);
			
		    while(rs.next()){	    
		        //Retrieve by column name
		        String account_id  = rs.getString("account_id");
		        String account_status = rs.getString("account_status");
		        String account_type = rs.getString("account_type");

		        //Display values
		        System.out.print("Account: " + account_id.trim());
		        System.out.print(", Account Type: " + account_type.trim());
		        System.out.println(", Account Status: " + account_status.trim());
		    }
		    rs.close();
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
}