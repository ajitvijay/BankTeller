import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bank_Options {
	public Bank_Options() {
		JFrame options_frame = new JFrame("Bank Teller Options");
		options_frame.setSize(500,500);
		options_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	    JButton check_button = new JButton();
	    check_button.setVisible(true);
	    check_button.setText("Enter Check Transaction");
	    
	    JButton month_button = new JButton();
	    month_button.setVisible(true);
	    month_button.setText("Generate Monthly Statement");
	    
	    JButton closed_accounts_button = new JButton();
	    closed_accounts_button.setVisible(true);
	    closed_accounts_button.setText("List Closed Accounts");
	    
	    JButton govt_button = new JButton();
	    govt_button.setVisible(true);
	    govt_button.setText("Generate Government Drug and Tax Evasion Report");
	    
	    JButton report_button = new JButton();
	    report_button.setVisible(true);
	    report_button.setText("Generate Customer Report");
	    
	    JButton interest_button = new JButton();
	    interest_button.setVisible(true);
	    interest_button.setText("Add Interest to Open Accounts");
	    
	    JButton acc_button = new JButton();
	    acc_button.setVisible(true);
	    acc_button.setText("Create new Account");
	    
	    JButton cust_button = new JButton();
	    cust_button.setVisible(true);
	    cust_button.setText("Create new Customer");
	    
	    JButton custacc_button = new JButton();
	    custacc_button.setVisible(true);
	    custacc_button.setText("Create new Account and new Customer");
	    
	    JButton delete_button = new JButton();
	    delete_button.setVisible(true);
	    delete_button.setText("Delete Closed Accounts and Customers");
	    
	    JButton delete_trans_button = new JButton();
	    delete_trans_button.setVisible(true);
	    delete_trans_button.setText("Delete Transactions");
	    
	    
	    options_frame.setLayout(new FlowLayout());
	    options_frame.add(check_button);
	    options_frame.add(month_button);
	    options_frame.add(closed_accounts_button);
	    options_frame.add(govt_button);
	    options_frame.add(report_button);
	    options_frame.add(interest_button);
	    options_frame.add(acc_button);
	    options_frame.add(cust_button);
	    options_frame.add(custacc_button);
	    options_frame.add(delete_button);
	    options_frame.add(delete_trans_button);
	    options_frame.setVisible(true);
	    
	    check_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				options_frame.dispose();
				new Bank_Check();
			}
        });
		
		
	}
}
