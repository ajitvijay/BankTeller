import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bank_AddAccount {
	private DatabaseConnection db;
	
	public Bank_AddAccount() {
		this.db = new DatabaseConnection();
		
		final JFrame frame = new JFrame("Add a new account, using existing customer ID");
		frame.setSize(550,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel panel = new JPanel(); // 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		final JLabel cust = new JLabel("Enter EXISTING Customer ID: ");
		final JTextField cust_input = new JTextField(10);
		
		final JLabel acc = new JLabel("Enter NEW Account ID: ");
		final JTextField acc_input = new JTextField(10);
		
		final JLabel branch = new JLabel("Enter NEW Account Branch Name: ");
		final JTextField branch_input = new JTextField(10);
		
		final JLabel type = new JLabel("Enter NEW Account Type (interest_checking, student_checking, savings, or pocket: ");
		final JTextField type_input = new JTextField(10);
		
		final JLabel bal = new JLabel("Enter NEW Account Balance ");
		final JTextField bal_input = new JTextField(10);
		
		final JLabel rate = new JLabel("Enter NEW Account Interest Rate: ");
		final JTextField rate_input = new JTextField(10);
		
		final JLabel fee = new JLabel("Enter NEW Account Monthly Fee (POCKET ONLY or NULL): ");
		final JTextField fee_input = new JTextField(10);
		
		final JLabel link = new JLabel("Enter NEW Account Linked Account (POCKET ONLY or NULL): ");
		final JTextField link_input = new JTextField(10);
	
	
		panel.add(BorderLayout.NORTH,cust); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,cust_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, acc);
		panel.add(BorderLayout.AFTER_LINE_ENDS, acc_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, branch);
		panel.add(BorderLayout.AFTER_LINE_ENDS, branch_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, type);
		panel.add(BorderLayout.AFTER_LINE_ENDS, type_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, bal);
		panel.add(BorderLayout.AFTER_LINE_ENDS, bal_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, rate);
		panel.add(BorderLayout.AFTER_LINE_ENDS, rate_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, fee);
		panel.add(BorderLayout.AFTER_LINE_ENDS, fee_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, link);
		panel.add(BorderLayout.AFTER_LINE_ENDS, link_input);
		
		final JPanel enterpanel = new JPanel();
		final JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String customer_id = cust_input.getText();
				String account_id = acc_input.getText();
				String branch_name = branch_input.getText();
				String account_type = type_input.getText();
				String balance = bal_input.getText();
				String interest_rate = rate_input.getText();
				String pocket_monthly_fee = fee_input.getText();
				String pocket_linked_account_id = link_input.getText();
				System.out.print("Creating new account for the EXISTING customer id: " + customer_id + " account id: " + account_id);
				frame.dispose();
				new Bank_Options();
				add_account(customer_id, account_id, branch_name, account_type, balance, interest_rate, pocket_monthly_fee, pocket_linked_account_id, db);
			}
        });
        

        enterpanel.add(BorderLayout.SOUTH,enter);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, enterpanel);
		frame.setVisible(true);
		
	}
	
	public void add_account(String customer_id, String account_id, String branch_name, String account_type, String balance, String interest_rate, String pocket_monthly_fee, String pocket_linked_account_id, DatabaseConnection db) {
		try {
			
			if(account_type.equals("pocket")) { //CREATING A POCKET ACCOUNT
				String query1 = "INSERT INTO Account (branch_name, account_id, account_type, balance,"
	    		  		+ "interest_rate, account_status, closed_date, current_month_interest_added, pocket_monthly_fee,"
	    		  		+ "pocket_linked_account_id) VALUES ('" + branch_name + "','" + account_id + "','" + account_type + "',"
	    		  		+ balance + "," + interest_rate + ", 'open', NULL, 'no', " + pocket_monthly_fee + ",'" + pocket_linked_account_id + "')";
				
				System.out.println(query1);
				db.queryUpdate(query1);
				
				String query3 = "INSERT INTO AccountCustomer (account_id, customer_id, account_type, is_primary_owner) VALUES ('" + account_id + "','" 
						+ customer_id + "','" + account_type + "', 'yes' ) ";
				
				System.out.println(query3);
				db.queryUpdate(query3);
				
				
			}
			else {
				String query1 = "INSERT INTO Account (branch_name, account_id, account_type, balance,"
	    		  		+ "interest_rate, account_status, closed_date, current_month_interest_added, pocket_monthly_fee,"
	    		  		+ "pocket_linked_account_id) VALUES (" + branch_name + "," + account_id + "," + account_type + "," 
	    		  		+ balance + "," + interest_rate + ", 'open', NULL, 'no', NULL, NULL); ";
				
				System.out.println(query1);
				db.queryUpdate(query1);
				
				String query3 = "INSERT INTO AccountCustomer (account_id, customer_id, account_type, is_primary_owner) VALUES ('" + account_id + "','" 
						+ customer_id + "','" + account_type + "', 'yes' ) ";
				
				System.out.println(query3);
				db.queryUpdate(query3);
				
			}
			
			
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	
	
}