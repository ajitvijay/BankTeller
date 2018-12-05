import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Withdraw {
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
	public Withdraw() {
		JFrame frame = new JFrame("Withdraw");
		DatabaseConnection db = new DatabaseConnection();
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(); // 
		JLabel label = new JLabel("Enter Account Number");
		JLabel send = new JLabel("Amount To Withdraw");
		JTextField tf = new JTextField(10); // accepts up to 10 characters
		JTextField pin_tf = new JTextField(10);
		
		JButton depo_btn = new JButton("Withdraw Money");
	    depo_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					String deposit_value = pin_tf.getText();
				    String account_id = tf.getText();
					frame.dispose();
					new ATMFunctions();
					withdraw(deposit_value,account_id,db);
					System.out.println(pin_tf.getText() + " dollars is the amount sent to account number " + tf.getText());

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
