import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;

public class Wire {
	public void addTransaction(String diff,String account_id,String customer_id, DatabaseConnection db) {
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
        //";
        String query = "INSERT INTO Transaction(Transaction_Date, Difference, Account_Id, Customer_Id, Transaction_Info)" + " Values (TO_DATE('" + todayStr + "', 'yyyy-mm-dd'), "
        + diff + ",'" + account_id + "', " + customer_id + ", " + "'Wire')";
        db.queryUpdate(query);
        System.out.println("Transaction successfully added");
	}
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
	public Wire(String id) {
		final JFrame frame = new JFrame("Wire");
		DatabaseConnection db = new DatabaseConnection();
		frame.setSize(700,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JPanel panel = new JPanel(); // 
		final JPanel panel2 = new JPanel();
		
		final JLabel sender = new JLabel("Enter Account Number(Sender)");
		final JLabel recv = new JLabel("Enter Account Number(Reciever)");
		final JLabel amount = new JLabel("Enter Amount to wire");
		
		final JTextField tf = new JTextField(10); // accepts up to 10 characters
		final JTextField pin_tf = new JTextField(10);
		final JTextField money = new JTextField(10);
		
		final JButton button = new JButton("Wire");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String send = tf.getText();
				String recv = pin_tf.getText();
			    String moolah = money.getText();
				frame.dispose();
				new ATMFunctions(id);
				wire(moolah,send,recv,db);
				System.out.println(tf.getText() + " wires " + pin_tf.getText() + " " + money.getText() + " dollars");
				addTransaction(money.getText(),tf.getText(),id,db);
				System.out.println("Transaction added to database");
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
