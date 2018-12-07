import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteCheck {
	public void addTransaction(String diff,String account_id,String customer_id, DatabaseConnection db) {
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
        //";
        String query = "INSERT INTO Transaction(Transaction_Date, Difference, Account_Id, Customer_Id, Transaction_Info)" + " Values (TO_DATE('" + todayStr + "', 'yyyy-mm-dd'), "
        + diff + ",'" + account_id + "', " + customer_id + ", " + "'Write Check')";
        db.queryUpdate(query);
        System.out.println("Transaction successfully added");
	}
	public void write_check(String value, String account_id, DatabaseConnection db) {
		try {
			String query2 = "UPDATE Account SET balance = balance - "+value +" WHERE account_id = '"+account_id+"'";
			System.out.println(query2);
			db.queryUpdate(query2);
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	public WriteCheck(String id) {
		final JFrame frame = new JFrame("Write Check");
		DatabaseConnection db = new DatabaseConnection();
		frame.setSize(700,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JPanel panel = new JPanel(); // 
		final JPanel panel2 = new JPanel();
		
		final JLabel sender = new JLabel("Enter Account Number");
		final JLabel recv = new JLabel("Enter Check number");
		final JLabel amount = new JLabel("Enter Amount for check");
		
		final JTextField tf = new JTextField(10); // accepts up to 10 characters
		final JTextField pin_tf = new JTextField(10);
		final JTextField money = new JTextField(10);
		
		final JButton button = new JButton("Write Check");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String checknum = pin_tf.getText();
				
				String check_value = money.getText();
			    String account_id = tf.getText();
				frame.dispose();
				new ATMFunctions(id);
				write_check(check_value,account_id,db);
				System.out.println(tf.getText() + " writes check " + pin_tf.getText() + " for " + money.getText() + " dollars");
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
