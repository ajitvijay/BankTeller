import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Deposit {
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
				
				try {
			       int deposit_value = Integer.parseInt(tf.getText());
			       int account_id = Integer.parseInt(pin_tf.getText());
			       String query = "UPDATE Account SET balance = balance + " + deposit_value + 
							" WHERE account_id = '" + account_id + "'";
			       db.query(query);
			       
			    } catch (NumberFormatException nfe) {
			        tf.setText("");
			    }
				
				System.out.println(tf.getText() + " dollars is the amount deposited");
				tf.setText("");
				pin_tf.setText("");
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
