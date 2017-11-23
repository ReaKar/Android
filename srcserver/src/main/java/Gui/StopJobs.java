package Gui;

import DataSources.DataSourcesImpl.Mystore;
import DataSources.JobsImpl.NmapJobs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by giannis on 12/30/15.
 */
public class StopJobs extends JFrame implements ActionListener {
    /**
     *Gui class to stopjobs
     */
    private String saname;
    private JFrame frame;
    private DefaultTableModel tableModel;
    private JTable table;

    public StopJobs(String saname){
        this.saname=saname;
    }
    public void runStopJobs() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.frame= new JFrame("Show Jobs for "+saname);

        this.frame.setLayout(null);
        this.frame.setSize(1000, 500);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        placeComponents(frame);

        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    private void placeComponents(JFrame frame) {
        tableModel = new DefaultTableModel();


            List<NmapJobs> mylist=Mystore.getStore().getHistorymaplist().get(saname);
            tableModel.addColumn("Id");
            tableModel.addColumn("Args");
            tableModel.addColumn("PeriodicTime");
            //now populate the data
            Object[]arr=new Object[3];
          for(NmapJobs nmj:mylist){
              if(nmj.getFlagperiodic().equals("true")){
                  arr[0]=nmj.getIdnmapjobs();
                  arr[1]=nmj.getNmapjobscol();
                  arr[2]=nmj.getTimeperiodic();
                  tableModel.addRow(arr);
              }
          }




        table = new JTable(tableModel);
        table.setSize(1000,400);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane jpan=new JScrollPane(table);
        jpan.setSize(1000, 400);
        this.frame.add(jpan);

        JButton registerButton = new JButton("back");
        registerButton.setBounds(0, 420, 100, 30);
        registerButton.setFont(new Font("Arial", Font.BOLD, 12));
        frame.add(registerButton);
        registerButton.addActionListener(this);

        JButton stopbutton = new JButton("Stop Jobs");
        stopbutton.setBounds(100, 420, 100, 30);
        stopbutton.setFont(new Font("Arial", Font.BOLD, 12));
        frame.add(stopbutton);
        stopbutton.addActionListener(this);


    }
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        int i=0;
        if(source.getText().equals("back")) {
            UserStatus page=new UserStatus();
            this.frame.dispose();
            page.runUserOptions();
        }
        else if(source.getText().equals("Stop Jobs")){
            int[] indexes=table.getSelectedRows();

            for(i = (indexes.length-1); i >=0; i--)
            {
                int id=(int) tableModel.getValueAt(i,0);
                NmapJobs nmjob=new NmapJobs(id,"Stop","true",0,saname);
                for(NmapJobs tempel:Mystore.getStore().getHistorymaplist().get(saname)){
                    if(tempel.getIdnmapjobs()==id){
                        Mystore.getStore().getHistorymaplist().get(saname).remove(tempel);
                    }
                }
                Mystore.getStore().getMaplist().get(saname).add(nmjob);
                tableModel.removeRow(indexes[i]);
            }
        }


    }
}

