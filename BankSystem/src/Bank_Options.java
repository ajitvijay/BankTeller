import javax.swing.*;
import oracle.net.aso.e;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Bank_Options {
	private DatabaseConnection db;
	
	public Bank_Options() {
		this.db = new DatabaseConnection();
		
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
	    month_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//GENERATE MONTHLY STATEMENT FOR A CUSTOMER
				options_frame.dispose();
				new Bank_MonthStatement();
			}
        });
	    closed_accounts_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//GENERATE ALL CLOSED ACCOUNTS
				options_frame.dispose();
				new Bank_Options();
				generate_closed_accounts(db);
			}
        });
	    govt_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//GENERATE GOVERNMENT REPORT
			}
        });
	    report_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				options_frame.dispose();
				new Bank_CustReport();
			}
        });
	    
	    interest_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ADD INTEREST TO ALL ACCOUNTS
			}
        });
	    acc_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CREATE A NEW ACCOUNT
				options_frame.dispose();
				new Bank_AddAccount();
			}
        });
	    cust_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				options_frame.dispose();
				new Bank_AddCustomer();
			}
        });
	    custacc_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				options_frame.dispose();
				new Bank_AddCustomerAccount();
			}
        }); 
	    delete_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// delete all closed accounts and customers
				options_frame.dispose();
				new Bank_Options();
				delete_closed_account_customers(db);
			}
        }); 
	    delete_trans_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//delete all transactions from each other the accounts in the past month
				options_frame.dispose();
				new Bank_Options();
				delete_transactions(db);
			}
        });
		
	}
	
	public void generate_closed_accounts(DatabaseConnection db) {
		try {
			Date today = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String todayStr = dateFormat.format(today);

	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.MONTH, -1);
	        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedmonth = format1.format(cal.getTime());
	        
	        System.out.println("Today's date: " + todayStr);
	        System.out.println("Month ago's date: " + formattedmonth);
			
			String query = "SELECT account_id, account_type, account_status, closed_date FROM Account WHERE account_status = 'closed' AND "
					+ "closed_date > TO_DATE('" + formattedmonth + "', 'yyyy-mm-dd') AND closed_date <= TO_DATE('" + todayStr + "', 'yyyy-mm-dd')";
			
			ResultSet rs = db.querySelect(query);
			
			System.out.println("GENERATING CLOSED ACCOUNTS WITHIN THE LAST MONTH: ");
			
			if(!rs.next()) {
				System.out.println("*No closed accounts exist from the past month.*");
			}
		    while(rs.next()){	
		        String account_id  = rs.getString("account_id");
		        String account_type = rs.getString("account_type");
		        String account_status  = rs.getString("account_status");
		        Date closed_date = rs.getDate("closed_date");
		        
		        DateFormat closed_date_format = new SimpleDateFormat("yyyy-MM-dd");  
                String str_closed_date = closed_date_format.format(closed_date);  

		        //Display values
		        System.out.print("Account: " + account_id.trim());
		        System.out.print(", Account Type: " + account_type.trim());
		        System.out.print(", Account Status: " + account_status.trim());
		        System.out.println(", Closed Date: " + str_closed_date.trim());
		    }
		    rs.close();
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
		
	}

	
	public void delete_closed_account_customers(DatabaseConnection db) {
		try {
			String query = "DELETE FROM AccountCustomer WHERE  account_id = (SELECT account_id FROM Account WHERE account_status = 'closed')";
			String query2 = "DELETE FROM Account WHERE account_status = 'closed'";
			String query3 = "DELETE FROM Customer WHERE NOT EXISTS ( SELECT * FROM AccountCustomer WHERE Customer.tax_id = AccountCustomer.customer_id)"; 
	
			db.queryUpdate(query);
			db.queryUpdate(query2);
			db.queryUpdate(query3);
			
			System.out.println("Closed accounts and customers have been successfully deleted.");
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	
	public void delete_transactions(DatabaseConnection db) {
		try {
			//delete list of transactions from each account, restart the month of processing
			String query = "DELETE FROM Transaction";
			String query2 = "UPDATE Account SET current_month_interest_added = 'no' ";

			db.queryUpdate(query);
			db.queryUpdate(query2);
			
			System.out.println("Transactions have been successfully deleted and ready for the new month of processing.");
		}
		catch (Exception e) {
	    	  e.printStackTrace();
	    }
	}
	
	
	
}
