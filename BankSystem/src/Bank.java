import java.awt.*;

import javax.swing.*;

public class Bank {
	Bank() {
		JFrame bank = new JFrame("Bank Teller");
		bank.setSize(500, 500);
		bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel bankheader = new JLabel("Welcome to Bank Teller Interface");
		bank.getContentPane().add(BorderLayout.NORTH,bankheader);
		bank.setVisible(true);
	}
}
