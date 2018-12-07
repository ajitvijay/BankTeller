import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopUp {
	public void addTransaction(String diff,String account_id,String customer_id, DatabaseConnection db) {
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
        //";
        String query = "INSERT INTO Transaction(Transaction_Date, Difference, Account_Id, Customer_Id, Transaction_Info)" + " Values (TO_DATE('" + todayStr + "', 'yyyy-mm-dd'), "
        + diff + ",'" + account_id + "', " + customer_id + ", " + "'TopUP')";
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return temp;
		}
		return temp;
	}
	public void topUp(String value, String account_id1, String account_id2, DatabaseConnection db) {
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
	public TopUp(String id) {
		final JFrame frame = new JFrame("Top Up");
		DatabaseConnection db = new DatabaseConnection();
		
		frame.setSize(700,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JPanel panel = new JPanel(); // 
		final JPanel panel2 = new JPanel();
		
		final JLabel sender = new JLabel("Enter Account Number(Sender)");
		final JLabel recv = new JLabel("Enter Account Number(Reciever)");
		final JLabel amount = new JLabel("Enter Amount to send");
		
		final JTextField tf = new JTextField(10); // accepts up to 10 characters
		final JTextField pin_tf = new JTextField(10);
		final JTextField money = new JTextField(10);
		
		final JButton button = new JButton("Top Up");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String acc1 = typeofAccount(tf.getText(), db);
				String acc2 = typeofAccount(pin_tf.getText(), db);
				System.out.println(acc1);
				System.out.println(acc2);
				
				if((acc1.trim().equals("interest_checking") || (acc1.trim().equals("student_checking")) || acc1.trim().equals("savings")) && acc2.trim().equals("pocket")) {
					topUp(money.getText(),tf.getText(),pin_tf.getText(), db);
					frame.dispose();
					new ATMFunctions(id);
					System.out.println(tf.getText() + " sent " + money.getText() + " dollars to account num " + pin_tf.getText());
					addTransaction(money.getText(),tf.getText(),id,db);
					System.out.println("Transaction added to database");
				}
				else {
					System.out.println("Can't transfer money");
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
