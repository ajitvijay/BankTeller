import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class ATM {
	
	public String customerid = "";
	
	public ATM() {
		// TODO Auto-generated method stub
		//new ATMFunctions();
		JFrame atm = new JFrame("ATM Interface");
		atm.setSize(500,100);
		atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DatabaseConnection db = new DatabaseConnection();
		
		JPanel panel = new JPanel(); // 
//        JLabel label = new JLabel("Enter Account Number");
//        JLabel pin 	= new JLabel("Enter Pin");
//        JTextField tf = new JTextField(10); // accepts up to 10 characters
//        JTextField pin_tf = new JTextField(10);
//        panel.add(BorderLayout.NORTH,label); // Components Added using Flow Layout
//        panel.add(BorderLayout.AFTER_LINE_ENDS,tf);
//        panel.add(BorderLayout.AFTER_LAST_LINE, pin);
//        panel.add(BorderLayout.AFTER_LINE_ENDS,pin_tf);
        
        JPanel logincheck = new JPanel();
        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					JFrame login_frame = new JFrame("Login Interface");
					atm.dispose();
					String id = JOptionPane.showInputDialog(login_frame,"Enter Account Number");
					String customer_id = JOptionPane.showInputDialog(login_frame, "Enter Customer ID");
					customerid = customer_id;
					System.out.println(customerid);
					String pin = JOptionPane.showInputDialog(login_frame, "Enter Pin Number");
					// validate login and move forward accordingly
					boolean temp = validate(id,customer_id,pin,db);
					if((temp)) {
						atm.dispose();
						new ATMFunctions(customer_id);
						System.out.println("Login Successful");
					}
					else {
						atm.dispose();
						new ATM();
						System.out.println("Login Failed");
					}
					System.out.println(id);
					System.out.println(customer_id);
					System.out.println(pin);
					//System.out.print(tf.getText());
					
			}
        	
        });
        JButton back = new JButton("Go Back");
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new GUI();
			}
        	
        });
        logincheck.add(BorderLayout.NORTH,login);
        logincheck.add(BorderLayout.SOUTH, back);

		atm.getContentPane().add(BorderLayout.NORTH, panel);
		atm.getContentPane().add(BorderLayout.SOUTH, logincheck);
		atm.setVisible(true);
	}
	public boolean validate(String account_id, String customer_id, String pin, DatabaseConnection db) {
		String query = "SELECT account_id, customer_id FROM AccountCustomer WHERE account_id = '" + 
	account_id + "'" + "AND customer_id = '" + customer_id + "'";
		String query2 = "SELECT PIN from Customer WHERE pin = '" + pin + "'" +"AND tax_id = '" + customer_id + "'";
		ResultSet rs = db.querySelect(query);
		ResultSet rs1 = db.querySelect(query2);
		boolean check = false;
		boolean check2 = false;
		try {
			if (rs.isBeforeFirst()) check = true;
			else check = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			if(rs1.isBeforeFirst()) check2 = true;
			else check2 = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if((check) && (check2)) return true;
		else return false;
	}
}
