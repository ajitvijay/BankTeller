import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdraw {
	public void addTransaction(String diff,String account_id,String customer_id, DatabaseConnection db) {
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
        //";
        String query = "INSERT INTO Transaction(Transaction_Date, Difference, Account_Id, Customer_Id, Transaction_Info)" + " Values (TO_DATE('" + todayStr + "', 'yyyy-mm-dd'), "
        + diff + ",'" + account_id + "', " + customer_id + ", " + "'Withdraw')";
        db.queryUpdate(query);
        System.out.println("Transaction successfully added");
	}
	public void withdraw(String value, String account_id, DatabaseConnection db) {
		try {
			String query2 = "UPDATE Account SET balance = balance - "+value +" WHERE account_id = '"+account_id+"'";
			System.out.println(query2);
			db.queryUpdate(query2);
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	public Withdraw(String id) {
		final JFrame frame = new JFrame("Withdraw");
		DatabaseConnection db = new DatabaseConnection();
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JPanel panel = new JPanel(); // 
		final JLabel label = new JLabel("Enter Account Number");
		final JLabel send = new JLabel("Amount To Withdraw");
		final JTextField tf = new JTextField(10); // accepts up to 10 characters
		final JTextField pin_tf = new JTextField(10);
		
		final JButton depo_btn = new JButton("Withdraw Money");
	    depo_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					String withdraw_value = pin_tf.getText();
				    String account_id = tf.getText();
					frame.dispose();
					new ATMFunctions(id);
					withdraw(withdraw_value,account_id,db);
					System.out.println(pin_tf.getText() + " dollars is the amount sent to account number " + tf.getText());
					addTransaction(withdraw_value,tf.getText(),id,db);
					System.out.println("Transaction added to database");

				}
	        	
	        	
	        });
		
		panel.add(BorderLayout.NORTH,label); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,tf);
		panel.add(BorderLayout.AFTER_LAST_LINE, send);
		panel.add(BorderLayout.AFTER_LINE_ENDS,pin_tf);
		
		frame.add(panel);
		frame.add(BorderLayout.SOUTH, depo_btn);
		
		frame.setVisible(true);
	}
}
