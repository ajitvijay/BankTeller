import java.sql.*;
import java.awt.*;
import javax.swing.*;

public class ATMFunctions {
	public ATMFunctions() {
		JFrame atm = new JFrame("ATM Functions");
		atm.setSize(600, 100);
		
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JButton deposit = new JButton("Deposit");
		JButton top_up = new JButton("Top Up");
		JButton withdraw = new JButton("Withdraw");
		JButton purchase = new JButton("Purchase");
		JButton transfer = new JButton("Transfer");
		JButton collect = new JButton("Collect");
		JButton pay_friend = new JButton("Pay Friend");
		JButton wire = new JButton("Wire");
		JButton write_check = new JButton("Write Check");
		JButton accrue_interest = new JButton("Accrue Interest");
		
		
		panel.add(BorderLayout.NORTH,deposit); 
		panel.add(BorderLayout.AFTER_LINE_ENDS,top_up);
		panel.add(BorderLayout.AFTER_LAST_LINE, withdraw);
		panel.add(BorderLayout.AFTER_LINE_ENDS,purchase);
		panel.add(BorderLayout.AFTER_LINE_ENDS,transfer);
		
		panel2.add(BorderLayout.NORTH,collect);
		panel2.add(BorderLayout.AFTER_LINE_ENDS,pay_friend);
		panel2.add(BorderLayout.AFTER_LAST_LINE, wire);
		panel2.add(BorderLayout.AFTER_LINE_ENDS,write_check);
		panel2.add(BorderLayout.AFTER_LINE_ENDS,accrue_interest);
		
		
		atm.getContentPane().add(BorderLayout.NORTH, panel);
		atm.getContentPane().add(BorderLayout.SOUTH, panel2);
		atm.setVisible(true);
	}	
}
