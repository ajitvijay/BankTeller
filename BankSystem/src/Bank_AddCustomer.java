import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bank_AddCustomer {
	public Bank_AddCustomer() {
		JFrame frame = new JFrame("Add a new account, using existing customer ID");
		frame.setSize(550,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(); // 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel cust = new JLabel("Enter NEW Customer ID: ");
		JTextField cust_input = new JTextField(10);
		
		JLabel acc = new JLabel("Enter EXISTING Account ID: ");
		JTextField acc_input = new JTextField(10);
		
		JLabel name = new JLabel("Enter NEW Customer Name: ");
		JTextField name_input = new JTextField(10);
		
		JLabel add = new JLabel("Enter NEW Customer Address: ");
		JTextField add_input = new JTextField(10);
		
		JLabel pin = new JLabel("Enter NEW Customer Pin: ");
		JTextField pin_input = new JTextField(10);

	
		panel.add(BorderLayout.NORTH,cust); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,cust_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, acc);
		panel.add(BorderLayout.AFTER_LINE_ENDS, acc_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, name);
		panel.add(BorderLayout.AFTER_LINE_ENDS, name_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, add);
		panel.add(BorderLayout.AFTER_LINE_ENDS, add_input);
		
		panel.add(BorderLayout.AFTER_LAST_LINE, pin);
		panel.add(BorderLayout.AFTER_LINE_ENDS, pin_input);

		JPanel enterpanel = new JPanel();
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tax_id = cust_input.getText();
				String account_id = acc_input.getText();
				String owner_name = name_input.getText();
				String address = add_input.getText();
				String pin = pin_input.getText();
				System.out.print("Creating new account for the customer id: " + tax_id + " account id: " + account_id);
				frame.dispose();
				new Bank_Options();
			}
        });
        

        enterpanel.add(BorderLayout.SOUTH,enter);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, enterpanel);
		frame.setVisible(true);
		
	}
}