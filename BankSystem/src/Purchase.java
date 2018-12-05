import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Purchase {
	public void purchase(String price, String customer_id, DatabaseConnection db) {
		try {
			String query = "SELECT account_id FROM AccountCustomer WHERE customer_id = '"+customer_id+"' AND account_type = 'pocket'";
			ResultSet rs = db.querySelect(query);
			if(rs.next()) {
				String account_id = rs.getString("account_id");
				String query2 = "UPDATE Account SET balance = balance - "+price +" WHERE account_id = '"+account_id.trim()+"'";
				System.out.println(query2);
				db.queryUpdate(query2);
				System.out.println(price + " dollars is the amount spent by customer " + customer_id);
			}
			else {
				System.out.println("Can't make transaction");
			}
			rs.close();
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	public Purchase() {
		JFrame frame = new JFrame("Purchase");
		DatabaseConnection db = new DatabaseConnection();
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(); // 
		JLabel label = new JLabel("Enter Price of Purchase");
		JLabel send = new JLabel("Enter Customer ID");
		JTextField tf = new JTextField(10); // accepts up to 10 characters
		JTextField pin_tf = new JTextField(10);
		
		JButton depo_btn = new JButton("Purchase Item");
	    depo_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					String deposit_value = tf.getText();
				    String customer_id = pin_tf.getText();
					frame.dispose();
					new ATMFunctions();
					purchase(deposit_value,customer_id,db);
					

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
