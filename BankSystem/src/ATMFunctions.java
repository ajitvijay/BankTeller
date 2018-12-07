import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ATMFunctions {
	public ATMFunctions(String id) {
		final JFrame atm = new JFrame("ATM Functions");
		atm.setSize(600, 100);
		
		final JPanel panel = new JPanel();
		final JPanel panel2 = new JPanel();
		
		final JButton deposit = new JButton("Deposit");
		deposit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new Deposit(id);
				
			}
			
		});
		
		final JButton top_up = new JButton("Top Up");
		top_up.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new TopUp(id);
			}
			
		});
		
		final JButton withdraw = new JButton("Withdraw");
		withdraw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new Withdraw(id);
			}
			
		});
		
		final JButton purchase = new JButton("Purchase");
		purchase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new Purchase(id);
			}
			
		});
		
		final JButton transfer = new JButton("Transfer");
		transfer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new Transfer(id);
			}
			
		});
		
		final JButton collect = new JButton("Collect");
		collect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new Collect(id);
			}
			
		});
		
		final JButton pay_friend = new JButton("Pay Friend");
		pay_friend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new PayFriend(id);
			}
			
		});
		
		final JButton wire = new JButton("Wire");
		wire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new Wire(id);
			}
			
		});
		
		final JButton write_check = new JButton("Write Check");
		write_check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm.dispose();
				new WriteCheck(id);
			}
			
		});
		
		
		panel.add(BorderLayout.NORTH,deposit); 
		panel.add(BorderLayout.AFTER_LINE_ENDS,top_up);
		panel.add(BorderLayout.AFTER_LAST_LINE, withdraw);
		panel.add(BorderLayout.AFTER_LINE_ENDS,purchase);
		panel.add(BorderLayout.AFTER_LINE_ENDS,transfer);
		
		panel2.add(BorderLayout.NORTH,collect);
		panel2.add(BorderLayout.AFTER_LINE_ENDS,pay_friend);
		panel2.add(BorderLayout.AFTER_LAST_LINE, wire);
		panel2.add(BorderLayout.AFTER_LINE_ENDS,write_check);
		
		
		atm.getContentPane().add(BorderLayout.NORTH, panel);
		atm.getContentPane().add(BorderLayout.SOUTH, panel2);
		atm.setVisible(true);
	}	
}
