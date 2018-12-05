import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Collect {
	public String typeofAccount(String account_id, DatabaseConnection db) {
		String query = "SELECT account_type FROM Account WHERE account_id = '" + account_id + "'";
		ResultSet rs = db.querySelect(query);
		String temp = "";
		try {
			while(rs.next()) {
				String type = rs.getString("account_type");
				return type;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return temp;
		}
		return temp;
	}
	public void collect(String value, String account_id1, String account_id2, DatabaseConnection db) {
		try {
			String query2 = "UPDATE Account SET balance = balance - "+value +" WHERE account_id = '"+account_id1.trim()+"'";
			String query = "UPDATE Account SET balance = balance + "+value +" * .97 WHERE account_id = '"+account_id2.trim()+"'";
			System.out.println(query2);
			System.out.println(query);
			db.queryUpdate(query2);
			db.queryUpdate(query);
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	public Collect() {
		JFrame frame = new JFrame("Collect");
		DatabaseConnection db = new DatabaseConnection();
		frame.setSize(800,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(); // 
		JPanel panel2 = new JPanel();
		
		JLabel sender = new JLabel("Enter Pocket Account Number");
		JLabel recv = new JLabel("Enter Checking/Savings Account Number");
		JLabel amount = new JLabel("Enter Amount to send");
		
		JTextField tf = new JTextField(10); // accepts up to 10 characters
		JTextField pin_tf = new JTextField(10);
		JTextField money = new JTextField(10);
		
		JButton button = new JButton("Collect");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String acc1 = typeofAccount(tf.getText(), db);
				String acc2 = typeofAccount(pin_tf.getText(), db);
				System.out.println(acc1);
				System.out.println(acc2);
				if(acc1.trim().equals("pocket") && (acc2.trim().equals("interest_checking") || (acc2.trim().equals("student_checking")) || acc2.trim().equals("savings"))) {
					collect(money.getText(),tf.getText(),pin_tf.getText(),db);
					frame.dispose();
					new ATMFunctions();
					System.out.println(tf.getText() + " pays " + pin_tf.getText() + " " + money.getText() + " dollars");
				}
				else {
					System.out.println("Both accounts must be pocket accounts");
					frame.dispose();
				}
			}
			
		});
		
		
		
		panel.add(BorderLayout.NORTH,sender); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,tf);
		panel.add(BorderLayout.AFTER_LAST_LINE, recv);
		panel.add(BorderLayout.AFTER_LINE_ENDS,pin_tf);
		
		panel2.add(BorderLayout.NORTH,amount);
		panel2.add(BorderLayout.AFTER_LINE_ENDS, money);
		
		frame.getContentPane().add(BorderLayout.NORTH, panel);
		frame.getContentPane().add(BorderLayout.CENTER, panel2);
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.setVisible(true);
	}
}
