import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class GUI {
	public GUI() {
		final JFrame frame = new JFrame("CS174A Project");
        frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        final JButton button1 = new JButton("ATM");
        final JButton button2 = new JButton("Bank Teller");
        button1.setBounds(20, 20, 130, 130);
        button2.setBounds(20, 150, 130, 130);
        button1.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		// TODO Auto-generated method stub
        		frame.dispose();
        		new DatabaseConnection();

        		new ATM();
        	}
        	
        });
        
        button2.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		new DatabaseConnection();
        		new Bank();
        	}
        });
        
        
        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);
        frame.setVisible(true);
	}
	public static void main(String[] args) {
		new GUI();
	}

	
}