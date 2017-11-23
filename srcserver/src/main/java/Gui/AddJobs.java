package Gui;

import DataSources.DataSourcesImpl.Mystore;
import DataSources.JobsImpl.NmapJobs;
import DataSources.JobsImpl.NmapJobsDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giannis on 12/19/15.
 */
public class AddJobs extends JFrame implements ActionListener {
    /**
     *Gui class to addjobs for sa
     */
    private static final long serialVersionUID = 1L;
    private JFrame frame ;
    private JTable table;
    private JSpinner jspn;
    private JCheckBox chinButton;
    private DefaultTableModel tableModel;
    private JTextField textField;
    private List<String> reglist=new LinkedList<>();
    private String name;
    public AddJobs(String name){
        this.name=name;
    }
    public void runUserOptions() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.frame= new JFrame("Add jobs for user "+name);

        this.frame.setLayout(null);
        this.frame.setSize(400, 350);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        placeComponents(frame);
        //this.frame.add(new JScrollPane(this.list));

        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    private void placeComponents(JFrame frame) {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Args");
        tableModel.addColumn("Periodic");
        tableModel.addColumn("PeriodicTime");
        table = new JTable(tableModel);
        table.setSize(400,150);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane jpan=new JScrollPane(table);
        jpan.setSize(400, 150);
        this.frame.add(jpan);

        JButton registerButton = new JButton("Add");
        registerButton.setBounds(0, 280, 100, 30);
        registerButton.setFont(new Font("Arial", Font.BOLD, 12));
        frame.add(registerButton);
        registerButton.addActionListener(this);

        JButton restoptions = new JButton("Remove");
        restoptions.setBounds(100, 280, 100, 30);
        restoptions.setFont(new Font("Arial", Font.BOLD, 11));
        restoptions.addActionListener(this);
        frame.add(restoptions);

        JButton refresh = new JButton("Send");
        refresh.setBounds(200, 280, 100, 30);
        refresh.addActionListener(this);
        frame.add(refresh);

        JButton backButton = new JButton("back");
        backButton.setBounds(300, 280, 100, 30);
        backButton.addActionListener(this);
        frame.add(backButton);

        textField = new JTextField(20);
        textField.setText("");
        textField.setBounds(0, 220, 250, 40);
        frame.add(textField);

        JLabel jl1=new JLabel("Periodic");
        jl1.setBounds(270,205,70,50);
        jl1.setFont(new Font("Arial", Font.BOLD, 11));
        frame.add(jl1);
        chinButton = new JCheckBox();
        chinButton.setBounds(250,220,20,20);
        chinButton.setSelected(false);
        frame.add(chinButton);

        JLabel jl2=new JLabel("Time");
        jl2.setBounds(310,227,50,50);
        frame.add(jl2);
        jspn=new JSpinner();
        jspn.setBounds(255,240,50,20);
        frame.add(jspn);


    }
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        int i=0;
        if(source.getText().equals("Add")) {
            Object[] obj=new Object[3];
            obj[0]=textField.getText();
            obj[1]=String.valueOf(chinButton.isSelected());
            if(obj[1].equals("true")){
                obj[2]=jspn.getValue();
            }
            else{
                obj[2]=0;
            }
            tableModel.addRow(obj);
        }
        else if(source.getText().equals("Remove")){
            int[] indexes=table.getSelectedRows();
            for(i = (indexes.length-1); i >=0; i--)
            {

                tableModel.removeRow(indexes[i]);
            }
        }
        else if(source.getText().equals("Send")){
            int rows =tableModel.getRowCount();

            for( i=0;i<rows;i++){

                int id=Mystore.getStore().getLastidNmap();
                String nmapargs=(String) tableModel.getValueAt(i,0);
                String flag=(String) tableModel.getValueAt(i,1);
                int time=(int) tableModel.getValueAt(i,2);
                NmapJobs nmj=new NmapJobs(id, nmapargs, flag, time, name);
                if(Mystore.getStore().getMaplist().containsKey(name)) {
                    Mystore.getStore().getMaplist().get(name).add(nmj);

                    Mystore.getStore().getHistorymaplist().get(name).add(nmj);
                    new NmapJobsDaoImpl().insert(nmj);
                }
                else{
                    List<NmapJobs> mylist=new LinkedList<>();
                    mylist.add(nmj);
                    Mystore.getStore().getMaplist().put(name,mylist);
                    List<NmapJobs> mylist1=new LinkedList<>();
                    mylist1.add(nmj);
                    Mystore.getStore().getHistorymaplist().put(name,mylist1);
                    new NmapJobsDaoImpl().insert(nmj);

                }
            }
            for(i=rows-1;i>=0;i--){
                tableModel.removeRow(i);
            }

        }
        else if(source.getText().equals("back")){
            UserStatus page=new UserStatus();
            this.frame.dispose();
            page.runUserOptions();
        }

    }
}