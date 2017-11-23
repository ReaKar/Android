package Gui;


import DataSources.DataSourcesImpl.DaoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInt extends JFrame implements ActionListener{

	 /**
	 * Gui class to sign in
	 */
	private static final long serialVersionUID = 1L;
	private JTextField  userText;
	 private JPasswordField passwordText;
	 private JFrame frame;

	public void runUserInt() {
		this.frame = new JFrame("Demo application");
		this.frame.setSize(300, 150);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents(frame);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	private void placeComponents(JFrame frame) {
		frame.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		frame.add(userLabel);

		this.userText = new JTextField(20);
		this.userText.setBounds(100, 10, 160, 25);
		frame.add(this.userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		frame.add(passwordLabel);

		this.passwordText = new JPasswordField(20);
		this.passwordText.setBounds(100, 40, 160, 25);
		frame.add(this.passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		frame.add(loginButton);
		loginButton.addActionListener(this);
		

	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if(new DaoController().checkAdmin(this.userText.getText(),this.passwordText.getText())){
			UserOptions page=new UserOptions();
			this.frame.dispose();
			  page.runUserOptions();
			  }
			  else{

			  JOptionPane.showMessageDialog(this,"Incorrect login or password",
			  "Error",JOptionPane.ERROR_MESSAGE);
			  }
		
			}
		
		
		
	
}
