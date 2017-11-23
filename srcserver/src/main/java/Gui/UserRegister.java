package Gui;

import DataSources.DataSourcesImpl.DaoController;
import DataSources.DataSourcesImpl.Mystore;
import DataSources.SAImpl.SaInfo;
import DataSources.SAImpl.SaInfoDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;



public class UserRegister extends JFrame implements ActionListener{
	/**
	 * Gui Class For Registering Sa's
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame ;
	private JList list;
	private DefaultListModel listModel;
	private List<String> reglist=new LinkedList<>();
	public void runUserOptions() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.frame= new JFrame("Demo application");
		
		this.frame.setLayout(null);
		this.frame.setSize(400, 350);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents(frame);
		//this.frame.add(new JScrollPane(this.list));
		
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	private void placeComponents(JFrame frame) {

		List<SaInfo> mylist=new LinkedList<>();
				mylist.addAll(Mystore.getStore().getRegister().values());
		listModel = new DefaultListModel();
		for(SaInfo sa:mylist){
			listModel.addElement(sa.getHashkey());
		}
		
		
		list = new JList(listModel);
		list.setSize(250,200);
		JScrollPane jpan=new JScrollPane(list);
		jpan.setSize(250, 200);
		this.frame.add(jpan);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(250, 0, 100, 30);
		registerButton.setFont(new Font("Arial", Font.BOLD, 12));
		frame.add(registerButton);
		registerButton.addActionListener(this);
		JButton restoptions = new JButton("More");
		restoptions.setBounds(250, 30, 100, 30);
		frame.add(restoptions);
		
		JButton backButton = new JButton("back");
		backButton.setBounds(250, 60, 100, 30);
		backButton.addActionListener(this);
		frame.add(backButton);

		JTextField textField = new JTextField(20);
		textField.setText("");
		textField.setBounds(0, 200, 350, 100);
		frame.add(textField);
		
	}
	public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			int i=0;
			if(source.getText().equals("Register")){
				int[] fromindex = list.getSelectedIndices();
				Object[] from = list.getSelectedValues();

				// Then, for each item in the array, we add them to
				// the other list.
				for(i = 0; i < from.length; i++)
				{
					reglist.add(from[i].toString());
				}

				// Finally, we remove the items from the first list.
				// We must remove from the bottom, otherwise we try to
				// remove the wrong objects.
				for(i = (fromindex.length-1); i >=0; i--)
				{
					listModel.remove(fromindex[i]);
				}
				for(String cur:reglist){
					Mystore.getStore().getRegSaved().put(cur,Mystore.getStore().getRegister().get(cur));
					if(!new DaoController().checkalreadyreg(cur)) {
						new SaInfoDaoImpl().insert(Mystore.getStore().getRegister().get(cur));
					}
					Mystore.getStore().getRegister().remove(cur);
				}
			}
			else if(source.getText().equals("More")){

				JOptionPane.showMessageDialog(source,source.getText()+" has been pressed...we dont care");
			}
			else if(source.getText().equals("back")){
				UserOptions page=new UserOptions();
				this.frame.dispose();
				  page.runUserOptions();
			}
			
	}
}