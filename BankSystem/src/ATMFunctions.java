import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ATMFunctions {
	public ATMFunctions() {
		JFrame atm = new JFrame("ATM Functions");
		atm.setSize(600, 100);
		
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		
		JButton deposit = new JButton("Deposit");
		deposit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Deposit();
			}
			
		});
		
		JButton top_up = new JButton("Top Up");
		top_up.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TopUp();
			}
			
		});
		
		JButton withdraw = new JButton("Withdraw");
		withdraw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Withdraw();
			}
			
		});
		
		JButton purchase = new JButton("Purchase");
		purchase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Purchase();
			}
			
		});
		
		JButton transfer = new JButton("Transfer");
		transfer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Transfer();
			}
			
		});
		
		JButton collect = new JButton("Collect");
		collect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Collect();
			}
			
		});
		
		JButton pay_friend = new JButton("Pay Friend");
		pay_friend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new PayFriend();
			}
			
		});
		
		JButton wire = new JButton("Wire");
		wire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Wire();
			}
			
		});
		
		JButton write_check = new JButton("Write Check");
		write_check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new WriteCheck();
			}
			
		});
		
		JButton accrue_interest = new JButton("Accrue Interest");
		accrue_interest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AccrueInterest();
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
		panel2.add(BorderLayout.AFTER_LINE_ENDS,accrue_interest);
		
		
		atm.getContentPane().add(BorderLayout.NORTH, panel);
		atm.getContentPane().add(BorderLayout.SOUTH, panel2);
		atm.setVisible(true);
	}	
}
