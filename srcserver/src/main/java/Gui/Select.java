package Gui;

import DataSources.DataSourcesImpl.DaoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Select extends JFrame implements ActionListener{
	/**
	 *Gui class to select among several options
	 */
		private static final long serialVersionUID = 1L;
		private JFrame frame ;

		
			
			public void runSelect() {
				this.frame= new JFrame("Demo application");
				this.frame.setSize(300, 320);
				this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				placeComponents(frame);
				this.frame.setLocationRelativeTo(null);
				this.frame.setVisible(true);
			}
		
			private void placeComponents(JFrame frame) {
				frame.setLayout(null);

				JButton registerButton = new JButton("Select SA");
				registerButton.setBounds(0, 10, 300, 100);
				frame.add(registerButton);
				registerButton.addActionListener(this);

				JButton restoptions = new JButton("Total Results");
				restoptions.setBounds(0, 110, 300, 100);
				frame.add(restoptions);
				restoptions.addActionListener(this);

				JButton registerandroid = new JButton("Android");
				registerandroid.setBounds(0, 210, 300, 100);
				frame.add(registerandroid);
				registerandroid.addActionListener(this);

			}
		
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			if(source.getText().equals("Select SA")){
				UserStatus page=new UserStatus();
				this.frame.dispose();
				  page.runUserOptions();
			}
				else if(source.getText().equals("Total Results")){
					TotalResults page=new TotalResults(new JTable(new DaoController().returnAllResults()));
					this.frame.dispose();
					page.runSaResults();
		
			}
			else if(source.getText().equals("Android")){
				AndroidRegister page=new AndroidRegister();
				this.frame.dispose();
				page.runUserOptions();
			}
			
	}
}