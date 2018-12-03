import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ATM {

	public ATM() {
		// TODO Auto-generated method stub
		JFrame atm = new JFrame("ATM Interface");
		atm.setSize(500,100);
		atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(); // 
        JLabel label = new JLabel("Enter Account Number");
        JLabel pin 	= new JLabel("Enter Pin");
        JTextField tf = new JTextField(10); // accepts up to 10 characters
        JTextField pin_tf = new JTextField(10);
        panel.add(BorderLayout.NORTH,label); // Components Added using Flow Layout
        panel.add(BorderLayout.AFTER_LINE_ENDS,tf);
        panel.add(BorderLayout.AFTER_LAST_LINE, pin);
        panel.add(BorderLayout.AFTER_LINE_ENDS,pin_tf);
        
        JPanel logincheck = new JPanel();
        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
}
