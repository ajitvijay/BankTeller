import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Purchase {
	public Purchase() {
		JFrame frame = new JFrame("Purchase");
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(); // 
		JLabel label = new JLabel("Enter Price of Purchase");
		JLabel send 	= new JLabel("Enter Account Number");
		JTextField tf = new JTextField(10); // accepts up to 10 characters
		JTextField pin_tf = new JTextField(10);
		
		JButton depo_btn = new JButton("Purchase Item");
	    depo_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println(tf.getText() + " dollars is spent on a purchase by " + pin_tf.getText());
					tf.setText("");
					pin_tf.setText("");
				}
	        	
	        	
	        });
		
		panel.add(BorderLayout.NORTH,label); // Components Added using Flow Layout
		panel.add(BorderLayout.AFTER_LINE_ENDS,tf);
		panel.add(BorderLayout.AFTER_LAST_LINE, send);
		panel.add(BorderLayout.AFTER_LINE_ENDS,pin_tf);
		
		frame.add(panel);
		frame.add(BorderLayout.SOUTH, depo_btn);
		
		frame.setVisible(true);
	}
}
