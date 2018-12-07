import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Bank {

	public Bank() {
		// TODO Auto-generated method stub
		final JFrame bank = new JFrame("Bank Interface");
		bank.setSize(500,100);
		bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel panel = new JPanel();
        
		final JPanel logincheck = new JPanel();
		final JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					JFrame login_frame = new JFrame("Bank Teller Login Interface");
					bank.dispose();
					String teller_id = JOptionPane.showInputDialog(login_frame,"Enter Bank Teller ID");
					String teller_password = JOptionPane.showInputDialog(login_frame, "Enter Bank Teller Password");
					// validate login and move forward accordingly
					
					System.out.println(teller_id);
					System.out.println(teller_password);
					//System.out.print(tf.getText());
					
					//compare the teller_id and teller_password to the BankTellerAccounts table
					
					bank.dispose();
					new Bank_Options();
					
			}
        	
        });
        final JButton back = new JButton("Go Back");
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bank.dispose();
				new GUI();
			}
        	
        });
        logincheck.add(BorderLayout.NORTH,login);
        logincheck.add(BorderLayout.SOUTH, back);

        bank.getContentPane().add(BorderLayout.NORTH, panel);
		bank.getContentPane().add(BorderLayout.SOUTH, logincheck);
		bank.setVisible(true);
	}
}
