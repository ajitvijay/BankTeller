import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bank_AddCustomerAccount {
	private DatabaseConnection db;
	
	public Bank_AddCustomerAccount() {
		
		this.db = new DatabaseConnection();
		
		JFrame frame = new JFrame("Add a new account and new customer ID");
		frame.setSize(600,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(); // 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel cust = new JLabel("Enter NEW Customer ID: ");
		JTextField cust_input = new JTextField(10);
		
		JLabel name = new JLabel("Enter NEW Customer Name: ");
		JTextField name_input = new JTextField(10);
		
		JLabel add = new JLabel("Enter NEW Customer Address: ");
		JTextField add_input = new JTextField(10);
		
		JLabel pin = new JLabel("Enter NEW Customer Pin: ");
		JTextField pin_input = new JTextField(10);
		
		JLabel acc = new JLabel("Enter NEW Account ID: ");
		JTextField acc_input = new JTextField(10);
		
		JLabel branch = new JLabel("Enter NEW Account Branch Name: ");
		JTextField branch_input = new JTextField(10);
		
		JLabel type = new JLabel("Enter NEW Account Type (interest_checking, student_checking, savings, or pocket: ");
		JTextField type_input = new JTextField(10);
		
		JLabel bal = new JLabel("Enter NEW Account Balance ");
		JTextField bal_input = new JTextField(10);
		
		JLabel rate = new JLabel("Enter NEW Account Interest Rate: ");
		JTextField rate_input = new JTextField(10);
		
		JLabel fee = new JLabel("Enter NEW Account Monthly Fee (POCKET ONLY or NULL): ");
		JTextField fee_input = new JTextField(10);
		
		JLabel link = new JLabel("Enter NEW Account Linked Account (POCKET ONLY or NULL): ");
		JTextField link_input = new JTextField(10);
	
	
		panel.add(BorderLayout.NORTH,cust); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,cust_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, name);
		panel.add(BorderLayout.AFTER_LINE_ENDS, name_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, add);
		panel.add(BorderLayout.AFTER_LINE_ENDS, add_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, pin);
		panel.add(BorderLayout.AFTER_LINE_ENDS, pin_input);
		
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
		
		JPanel enterpanel = new JPanel();
        JButton enter = new JButton("Enter");
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
				System.out.print("Creating new account for the customer id: " + customer_id + " account id: " + account_id);
				frame.dispose();
				new Bank_Options();
				add_customer_account(customer_id, account_id, branch_name, account_type, balance, interest_rate, pocket_monthly_fee, pocket_linked_account_id, db);
			}
        });
        

        enterpanel.add(BorderLayout.SOUTH,enter);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, enterpanel);
		frame.setVisible(true);
		
	}
	
	public void add_customer_account(String customer_id, String account_id, String branch_name, String account_type, String balance, 
			String interest_rate, String pocket_monthly_fee, String pocket_linked_account_id, DatabaseConnection db) {
		try {
			String query = "";
			if(account_type.equals("pocket")) {
				query = "INSERT INTO Account (branch_name, account_id, account_type, balance,"
	    		  		+ "interest_rate, account_status, closed_date, current_month_interest_added, pocket_monthly_fee,"
	    		  		+ "pocket_linked_account_id) VALUES ('" + branch_name + "','" + account_id + "','" + account_type + "'," + balance
	    		  		+ "," + interest_rate + ", 'open', NULL, 'no'," + pocket_monthly_fee + ",'" + pocket_linked_account_id + "')";
			}
			else {
				query = "INSERT INTO Account (branch_name, account_id, account_type, balance,"
	    		  		+ "interest_rate, account_status, closed_date, current_month_interest_added, pocket_monthly_fee,"
	    		  		+ "pocket_linked_account_id) VALUES (" + branch_name + "," + account_id + "," + account_type + "," 
	    		  		+ balance + "," + interest_rate + ", 'open', NULL, 'no', NULL, NULL); ";
			}
			
			System.out.println(query);
			db.query(query);
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
}