import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Wire {
	public void wire(String value, String account_id1, String account_id2, DatabaseConnection db) {
		try {
			String temp = value;
			value = "" + (Integer.parseInt(temp) * 1.02);
			String query2 = "UPDATE Account SET balance = balance - "+value +" WHERE account_id = '"+account_id1+"'";
			String query = "UPDATE Account SET balance = balance + "+temp +" WHERE account_id = '"+account_id2+"'";
			System.out.println(query2);
			System.out.println(query);

			db.queryUpdate(query2);
			db.queryUpdate(query);
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	public Wire() {
		JFrame frame = new JFrame("Wire");
		DatabaseConnection db = new DatabaseConnection();
		frame.setSize(700,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(); // 
		JPanel panel2 = new JPanel();
		
		JLabel sender = new JLabel("Enter Account Number(Sender)");
		JLabel recv = new JLabel("Enter Account Number(Reciever)");
		JLabel amount = new JLabel("Enter Amount to wire");
		
		JTextField tf = new JTextField(10); // accepts up to 10 characters
		JTextField pin_tf = new JTextField(10);
		JTextField money = new JTextField(10);
		
		JButton button = new JButton("Wire");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String send = tf.getText();
				String recv = pin_tf.getText();
			    String moolah = money.getText();
				frame.dispose();
				new ATMFunctions();
				wire(moolah,send,recv,db);
				System.out.println(tf.getText() + " wires " + pin_tf.getText() + " " + money.getText() + " dollars");
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
