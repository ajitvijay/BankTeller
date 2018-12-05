import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class Bank_Check {
	private DatabaseConnection db;
	
	public Bank_Check() {
		this.db = new DatabaseConnection();
		
		JFrame check_frame = new JFrame("Bank Teller Options");
		check_frame.setSize(500,100);
		check_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(); // 
		JLabel acc = new JLabel("Enter Account");
		JLabel amt = new JLabel("Enter Amount");
		JTextField account_id_input = new JTextField(10); // accepts up to 10 characters
		JTextField specified_amount_input = new JTextField(10);
		panel.add(BorderLayout.NORTH,acc); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,account_id_input);
		panel.add(BorderLayout.AFTER_LAST_LINE, amt);
		panel.add(BorderLayout.AFTER_LINE_ENDS, specified_amount_input);
		
		JPanel enterpanel = new JPanel();
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String account_id = account_id_input.getText();
				String specified_amount = specified_amount_input.getText();
				//System.out.print("Entered a check for account_id: " + account_id + " for the amount: " + specified_amount);
				check_frame.dispose();
				new Bank_Options();
				write_check(account_id, specified_amount, db);
			}
        });
        

        enterpanel.add(BorderLayout.SOUTH,enter);
        check_frame.getContentPane().add(BorderLayout.NORTH, panel);
        check_frame.getContentPane().add(BorderLayout.SOUTH, enterpanel);
		check_frame.setVisible(true);
		
	}
	
	public void write_check(String account_id, String specified_amount, DatabaseConnection db) {
		try {
			String query = "UPDATE Account SET balance = balance - " + specified_amount + " WHERE account_id = '" + account_id + "'";
			//String query = "UPDATE Account SET balance = balance - 3000 WHERE account_id = '265'";
			System.out.println(query);
			db.queryUpdate(query);
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	
}