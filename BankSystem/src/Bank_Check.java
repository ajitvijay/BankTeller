import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bank_Check {
	public Bank_Check() {
		JFrame check_frame = new JFrame("Bank Teller Options");
		check_frame.setSize(500,100);
		check_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(); // 
		JLabel acc = new JLabel("Enter Account");
		JLabel amt = new JLabel("Enter Amount");
		JTextField account_id_input = new JTextField(10); // accepts up to 10 characters
		JTextField specified_amount_input = new JTextField(10);
		panel.add(BorderLayout.NORTH,acc); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,account_id_input);
		panel.add(BorderLayout.AFTER_LAST_LINE, amt);
		panel.add(BorderLayout.AFTER_LINE_ENDS, specified_amount_input);
		
		JPanel enterpanel = new JPanel();
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String account_id = account_id_input.getText();
				String specified_amount = specified_amount_input.getText();
				System.out.print("Entered a check for account_id: " + account_id + " for the amount: " + specified_amount);
				check_frame.dispose();
				new Bank_Options();
			}
        });
        

        enterpanel.add(BorderLayout.SOUTH,enter);
        check_frame.getContentPane().add(BorderLayout.NORTH, panel);
        check_frame.getContentPane().add(BorderLayout.SOUTH, enterpanel);
		check_frame.setVisible(true);
		
	}
}