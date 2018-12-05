import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Transfer {
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
	
	public Transfer() {
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
				// TODO Auto-generated method stub
				
				
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
