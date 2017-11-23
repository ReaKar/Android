package Gui;

import DataSources.DataSourcesImpl.DaoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giannis on 12/30/15.
 */
public class SaResults extends JFrame implements ActionListener {
    /**
     *Gui class for Results on a specific sa
     */
    private String saname;
    private JFrame frame;
    private JTable table;
    private JTextField jtxto;
    private JTextField jtxfrom;

    public SaResults(String saname,JTable table){
        this.saname=saname;
        this.table=table;
    }
    public void runSaResults() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.frame= new JFrame("Show Results for "+saname);

        this.frame.setLayout(null);
        this.frame.setSize(1000, 700);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        placeComponents(frame);

        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    private void placeComponents(JFrame frame) {


        table.setSize(1000,400);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane jpan=new JScrollPane(table);
        jpan.setSize(1000, 400);
        this.frame.add(jpan);
        JLabel jtxa=new JLabel();
        jtxa.setText("Date Format(YYYY-MM-DD HH:MM:SS)");
        jtxa.setBounds(650,450,300,30);
        frame.add(jtxa);
        JLabel jlabfrom=new JLabel();
        jlabfrom.setText("From:");
        jlabfrom.setBounds(600,480,50,30);
        frame.add(jlabfrom);
        JLabel jlabto=new JLabel();
        jlabto.setText("To:");
        jlabto.setBounds(755,480,30,30);
        frame.add(jlabto);
        this.jtxfrom=new JTextField();
        this.jtxfrom.setText("");
        this.jtxfrom.setBounds(650,480,100,30);
        frame.add(this.jtxfrom);
        this.jtxto=new JTextField();
        this.jtxto.setText("");
        this.jtxto.setBounds(790,480,100,30);
        frame.add(this.jtxto);
        JButton registerButton = new JButton("back");
        registerButton.setBounds(0, 620, 100, 30);
        registerButton.setFont(new Font("Arial", Font.BOLD, 12));
        frame.add(registerButton);
        registerButton.addActionListener(this);
        JButton formatresult = new JButton("Format");
        formatresult.setBounds(650, 520, 100, 30);
        formatresult.setFont(new Font("Arial", Font.BOLD, 12));
        frame.add(formatresult);
        formatresult.addActionListener(this);


    }
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        int i=0;
        if(source.getText().equals("back")) {
            UserStatus page=new UserStatus();
            this.frame.dispose();
            page.runUserOptions();
    }else if(source.getText().equals("Format")){

            Object[] arr=new Object[3];
            arr[0]=saname;
            arr[1]=this.jtxfrom.getText();
            arr[2]=this.jtxto.getText();
            SaResults page=new SaResults(saname,new JTable(new DaoController().returnResultTabledates(arr)));
            this.frame.dispose();
            page.runSaResults();

        }

}
}
