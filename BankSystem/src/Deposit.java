import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit {
	public void addTransaction(String diff,String account_id, String transaction_info, DatabaseConnection db) {
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
        //";
        String query = "INSERT INTO Transaction(Transaction_Date, Difference, Account_Id, Customer_Id, Transaction_Info)" + " Values (TO_DATE('" + todayStr + "', 'yyyy-mm-dd'), "
        + diff + ", " + account_id + ", " + NULL + ", " + "Deposit");
	}
	public void deposit(String value, String account_id, DatabaseConnection db) {
		try {
			String query2 = "UPDATE Account SET balance = balance + "+value +" WHERE account_id = '"+account_id+"'";
			//String query = "UPDATE Account SET balance = balance - 3000 WHERE account_id = '265'";
			//System.out.println(query);
			System.out.println(query2);
			db.queryUpdate(query2);
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	public Deposit() {
		JFrame frame = new JFrame("Deposit");
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DatabaseConnection db = new DatabaseConnection();
		
		JPanel panel = new JPanel();
		JLabel send = new JLabel("Account Number money is going to");
		JLabel money 	= new JLabel("Enter Amount to Deposit");
        JTextField tf = new JTextField(10); // accepts up to 10 characters	
        JTextField pin_tf = new JTextField(10);
        JButton depo_btn = new JButton("Deposit");
        depo_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String deposit_value = tf.getText();
			    String account_id = pin_tf.getText();
				frame.dispose();
				new ATMFunctions();
				deposit(deposit_value,account_id,db);
				System.out.println(tf.getText() + " dollars is the amount deposited");
				
			}
        	
        	
        });
        
        panel.add(BorderLayout.NORTH,money); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,tf);
		panel.add(BorderLayout.AFTER_LAST_LINE, send);
		panel.add(BorderLayout.AFTER_LINE_ENDS,pin_tf);
		panel.add(BorderLayout.SOUTH,depo_btn);
		
		frame.add(panel);
		
		frame.setVisible(true);
	}
}
