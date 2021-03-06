import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase {
	public void addTransaction(String diff,String account_id,String customer_id, DatabaseConnection db) {
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
        //";
        String query = "INSERT INTO Transaction(Transaction_Date, Difference, Account_Id, Customer_Id, Transaction_Info)" + " Values (TO_DATE('" + todayStr + "', 'yyyy-mm-dd'), "
        + diff + ",'" + account_id + "', " + customer_id + ", " + "'Purchase')";
        db.queryUpdate(query);
        System.out.println("Transaction successfully added");
	}
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
				addTransaction(price,account_id,customer_id,db);
				System.out.println("Transaction added to database");
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
	public Purchase(String id) {
		final JFrame frame = new JFrame("Purchase");
		DatabaseConnection db = new DatabaseConnection();
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JPanel panel = new JPanel(); // 
		final JLabel label = new JLabel("Enter Price of Purchase");
		final JLabel send = new JLabel("Enter Customer ID");
		final JTextField tf = new JTextField(10); // accepts up to 10 characters
		final JTextField pin_tf = new JTextField(10);
		
		final JButton depo_btn = new JButton("Purchase Item");
	    depo_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					String purchase_value = tf.getText();
				    String customer_id = pin_tf.getText();
					frame.dispose();
					new ATMFunctions(id);
					purchase(purchase_value,customer_id,db);
					System.out.println(customer_id + " purchased an item for " + purchase_value + " dollars");
					
					

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
