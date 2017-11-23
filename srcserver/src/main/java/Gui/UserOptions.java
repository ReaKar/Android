package Gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserOptions extends JFrame implements ActionListener{
	/**
	 * Gui class to select among options
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame ;
	public void runUserOptions() {
		this.frame= new JFrame("Demo application");
		this.frame.setSize(300, 320);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents(frame);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	private void placeComponents(JFrame frame) {
		frame.setLayout(null);

		JButton registerButton = new JButton("Register Sa's");
		registerButton.setBounds(0, 10, 300, 100);
		frame.add(registerButton);

		JButton restoptions = new JButton("Rest Options");
		restoptions.setBounds(0, 110, 300, 100);
		frame.add(restoptions);
		
		JButton backButton = new JButton("back");
		backButton.setBounds(0, 210, 300, 100);
		frame.add(backButton);
		registerButton.addActionListener(this);
		restoptions.addActionListener(this);
		backButton.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			if(source.getText().equals("Register Sa's")){
				UserRegister page=new UserRegister();
				this.frame.dispose();
				  page.runUserOptions();
			}
			else if(source.getText().equals("Rest Options")){
				Select page=new Select();
				this.frame.dispose();
				page.runSelect();
			}
			else if(source.getText().equals("back")){
				UserInt page=new UserInt();
				this.frame.dispose();
				  page.runUserInt();
			}
			
	}
}
