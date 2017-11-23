package Gui;

import DataSources.DataSourcesImpl.DaoController;
import DataSources.DataSourcesImpl.Mystore;
import DataSources.JobsImpl.NmapJobs;
import DataSources.SAImpl.SaInfoDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giannis on 12/19/15.
 */
public class UserStatus extends JFrame implements ActionListener {
    /**
     *Gui class to display status of sa's
     */
    private static final long serialVersionUID = 1L;
    private JFrame frame ;
    private JTable table;
    private DefaultTableModel tableModel;
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

        ResultSet set=new SaInfoDaoImpl().selectAll();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("sa");
        tableModel.addColumn("status");
        Object[]arr=new Object[2];
        try {
            while (set.next()) {
                arr[0] = set.getString(1);
                if(Mystore.getStore().getRegSaved().containsKey(arr[0])){
                    long timeinterval=System.currentTimeMillis()-Mystore.getStore().getSastatus().get(arr[0]);
                    if(timeinterval<Mystore.getStore().getRegSaved().get(arr[0]).getMainperiod()*1000*3){
                        arr[1] = "online";
                    }
                    else{
                        arr[1] = "offline";
                    }
                }else{
                    arr[1] = "offline";
                }

                tableModel.addRow(arr);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        table = new JTable(tableModel)
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSize(250,200);
        JScrollPane jpan=new JScrollPane(table);
        jpan.setSize(250, 200);
        this.frame.add(jpan);

        JButton registerButton = new JButton("Add Jobs");
        registerButton.setBounds(250, 0, 100, 30);
        registerButton.setFont(new Font("Arial", Font.BOLD, 12));
        frame.add(registerButton);
        registerButton.addActionListener(this);

        JButton restoptions = new JButton("Stop Jobs");
        restoptions.setBounds(250, 30, 100, 30);
        restoptions.setFont(new Font("Arial", Font.BOLD, 11));
        restoptions.addActionListener(this);
        frame.add(restoptions);

        JButton stopjobs = new JButton("Stop Sa");
        stopjobs.setBounds(250, 150, 100, 30);
        stopjobs.setFont(new Font("Arial", Font.BOLD, 11));
        stopjobs.addActionListener(this);
        frame.add(stopjobs);

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(250, 90, 100, 30);
        refresh.addActionListener(this);
        frame.add(refresh);

        JButton sares = new JButton("Sa Results");
        sares.setBounds(250, 120, 100, 30);
        sares.addActionListener(this);
        frame.add(sares);

        JButton backButton = new JButton("back");
        backButton.setBounds(250, 60, 100, 30);
        backButton.addActionListener(this);
        frame.add(backButton);

    }
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        int i=0;
        if(source.getText().equals("Add Jobs")) {
            int index=table.getSelectedRow();
            String value=(String) tableModel.getValueAt(index,0);
            if(Mystore.getStore().getRegSaved().containsKey(value)){
                long timeinterval=System.currentTimeMillis()-Mystore.getStore().getSastatus().get(value);
                if(timeinterval<Mystore.getStore().getRegSaved().get(value).getMainperiod()*1000){
                    AddJobs addj=new AddJobs(value);
                    this.frame.dispose();
                    addj.runUserOptions();
                }
                else{
                    JOptionPane.showMessageDialog(source,"Cannot add jobs to offline Sa");
                }
            }else{
                JOptionPane.showMessageDialog(source,"Cannot add jobs to offline Sa");
            }

        }
        else if(source.getText().equals("Stop Jobs")){
            int index=table.getSelectedRow();
            String value=(String) tableModel.getValueAt(index,0);
            if(Mystore.getStore().getRegSaved().containsKey(value)){
                long timeinterval=System.currentTimeMillis()-Mystore.getStore().getSastatus().get(value);
                if(timeinterval<Mystore.getStore().getRegSaved().get(value).getMainperiod()*1000*3){
                    StopJobs page=new StopJobs(value);
                    this.frame.dispose();
                    page.runStopJobs();
                }
                else{
                    JOptionPane.showMessageDialog(source,"Cannot Stop jobs from offline Sa");
                }
            }else{
                JOptionPane.showMessageDialog(source,"Cannot Stop jobs from offline Sa");
            }

        }
        else if(source.getText().equals("Refresh")){
            UserStatus stat=new UserStatus();
            this.frame.dispose();
            stat.runUserOptions();
        }
        else if(source.getText().equals("back")){
            UserOptions page=new UserOptions();
            this.frame.dispose();
            page.runUserOptions();
        }
        else if(source.getText().equals("Stop Sa")){
            int index=table.getSelectedRow();
            String value=(String) tableModel.getValueAt(index,0);
            NmapJobs nmjob=new NmapJobs(-1,"exit(0)","true",-1,value);
            Mystore.getStore().getMaplist().get(value).add(nmjob);


        }
        else if(source.getText().equals("Sa Results")){
            int index=table.getSelectedRow();
            String value=(String) tableModel.getValueAt(index,0);
            SaResults page=new SaResults(value,new JTable(new DaoController().returnResultTable(value)));
            this.frame.dispose();
            page.runSaResults();
        }

    }
}