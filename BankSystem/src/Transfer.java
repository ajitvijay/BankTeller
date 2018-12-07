import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;

public class Transfer {
	public void addTransaction(String diff,String account_id,String customer_id, DatabaseConnection db) {
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
        //";
        String query = "INSERT INTO Transaction(Transaction_Date, Difference, Account_Id, Customer_Id, Transaction_Info)" + " Values (TO_DATE('" + todayStr + "', 'yyyy-mm-dd'), "
        + diff + ",'" + account_id + "', " + customer_id + ", " + "'Transfer')";
        db.queryUpdate(query);
        System.out.println("Transaction successfully added");
	}
	public String typeofAccount(String account_id, DatabaseConnection db) {
		String query = "SELECT account_type FROM Account WHERE account_id = '" + account_id + "'";
		ResultSet rs = db.querySelect(query);
		String temp = "";
		try {
			while(rs.next()) {
				String type = rs.getString("account_type");
				return type;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return temp;
		}
		return temp;
	}
	public void transfer(String value, String account_id1, String account_id2, DatabaseConnection db) {
		try {
			String query2 = "UPDATE Account SET balance = balance - "+value +" WHERE account_id = '"+account_id1.trim()+"'";
			String query = "UPDATE Account SET balance = balance + "+value +" WHERE account_id = '"+account_id2.trim()+"'";
			System.out.println(query2);
			System.out.println(query);
			db.queryUpdate(query2);
			db.queryUpdate(query);
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	public Transfer(String id) {
		JFrame frame = new JFrame("Transfer");
		DatabaseConnection db = new DatabaseConnection();
		
		frame.setSize(700,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(); // 
		JPanel panel2 = new JPanel();
		
		JLabel sender = new JLabel("Enter Account Number(Sender)");
		JLabel recv = new JLabel("Enter Account Number(Reciever)");
		JLabel amount = new JLabel("Enter Amount to transfer");
		
		JTextField tf = new JTextField(10); // accepts up to 10 characters
		JTextField pin_tf = new JTextField(10);
		JTextField money = new JTextField(10);
		
		JButton button = new JButton("Transfer");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(money.getText()) > 2000) {
					System.out.println("Too much money being moved");
				}
				else {
					transfer(money.getText(),tf.getText(), pin_tf.getText(), db);
					frame.dispose();
					new ATMFunctions(id);
					System.out.println(tf.getText() + " transfers " + money.getText() + " dollars to " + pin_tf.getText());
					addTransaction(money.getText(),tf.getText(),id,db);
					System.out.println("Transaction added to database");
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
