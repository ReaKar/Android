package DataSources.DataSourcesImpl;

import DataSources.AdminImpl.AdminDaoImpl;
import DataSources.JobsImpl.NmapJobs;
import DataSources.JobsImpl.NmapJobsDaoImpl;
import DataSources.ResultsImpl.AndroidResults;
import DataSources.ResultsImpl.NmapJobsResultsDaoImpl;
import DataSources.SAImpl.SaInfoDaoImpl;
import DataSources.SAImpl.SaState;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by giannis on 12/31/15.
 */
public class DaoController {

    public DefaultTableModel returnResultTable(String saname){


       DefaultTableModel tableModel = new DefaultTableModel();

        ResultSet rs=null;
        try {
            //create an array of column names
            rs=new NmapJobsResultsDaoImpl().select(saname);
            ResultSetMetaData mdata = rs.getMetaData();
            int colCount = mdata.getColumnCount();
            String[] colNames = new String[colCount];
            for (int i = 1; i <= colCount; i++) {
                colNames[i - 1] = mdata.getColumnName(i);
            }
            tableModel.setColumnIdentifiers(colNames);

            //now populate the data
            while (rs.next()) {
                String[] rowData = new String[colCount];
                for (int i = 1; i <= colCount; i++) {
                    rowData[i - 1] = rs.getString(i);
                }
                tableModel.addRow(rowData);
            }
            rs.close();
        } catch (SQLException ignore) {
        }
        return tableModel;
    }
    public DefaultTableModel returnResultTabledates(Object[] dates){
        DefaultTableModel tableModel = new DefaultTableModel();

        ResultSet rs=null;
        try {
            //create an array of column names
            rs=new NmapJobsResultsDaoImpl().selectWithDate(dates);
            ResultSetMetaData mdata = rs.getMetaData();
            int colCount = mdata.getColumnCount();

            String[] colNames = new String[colCount];
            for (int i = 1; i <= colCount; i++) {
                colNames[i - 1] = mdata.getColumnName(i);
            }
            tableModel.setColumnIdentifiers(colNames);

            //now populate the data
            while (rs.next()) {
                String[] rowData = new String[colCount];
                for (int i = 1; i <= colCount; i++) {
                    rowData[i - 1] = rs.getString(i);
                    System.out.println(rowData[i-1]);
                }
                tableModel.addRow(rowData);
            }
            rs.close();
        } catch (SQLException ignore) {
        }
        return tableModel;
    }

    public DefaultTableModel returnAllResults(){
        DefaultTableModel tableModel = new DefaultTableModel();

        ResultSet rs=null;
        try {
            //create an array of column names
            rs=new NmapJobsResultsDaoImpl().selectAll();
            ResultSetMetaData mdata = rs.getMetaData();
            int colCount = mdata.getColumnCount();
            String[] colNames = new String[colCount];
            for (int i = 1; i <= colCount; i++) {
                colNames[i - 1] = mdata.getColumnName(i);
            }
            tableModel.setColumnIdentifiers(colNames);

            //now populate the data
            while (rs.next()) {
                String[] rowData = new String[colCount];
                for (int i = 1; i <= colCount; i++) {
                    rowData[i - 1] = rs.getString(i);
                }
                tableModel.addRow(rowData);
            }
            rs.close();
        } catch (SQLException ignore) {
        }
        return tableModel;
    }
    public DefaultTableModel returnAllResutsDates(Object[] dates){
        DefaultTableModel tableModel = new DefaultTableModel();

        ResultSet rs=null;
        try {
            //create an array of column names
            rs=new NmapJobsResultsDaoImpl().selectAlldates(dates);
            ResultSetMetaData mdata = rs.getMetaData();
            int colCount = mdata.getColumnCount();
            String[] colNames = new String[colCount];
            for (int i = 1; i <= colCount; i++) {
                colNames[i - 1] = mdata.getColumnName(i);
            }
            tableModel.setColumnIdentifiers(colNames);

            //now populate the data
            while (rs.next()) {
                String[] rowData = new String[colCount];
                for (int i = 1; i <= colCount; i++) {
                    rowData[i - 1] = rs.getString(i);
                }
                tableModel.addRow(rowData);
            }
            rs.close();
        } catch (SQLException ignore) {
        }

        return tableModel;
    }

    public boolean checkAdmin(String username,String password){
        ResultSet rs;
        rs=new AdminDaoImpl().select(username);
        try{
            if(rs.next()){
                if(rs.getString(2).equals(username)&&rs.getString(3).equals(password)){
                    rs.close();
                    return true;
                }
                else{
                    rs.close();
                    return false;
                }
            }else{
                rs.close();
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkalreadyreg(String name){
        ResultSet rs=new SaInfoDaoImpl().select(name);
        try{
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    public List<SaState> getSaState(){
        List<SaState> sas=new LinkedList<>();
        ResultSet set=new SaInfoDaoImpl().selectAll();
        SaState sast;
        try {
            while (set.next()) {
               String saname=set.getString(1);
                if(Mystore.getStore().getRegSaved().containsKey(saname)){
                    long timeinterval=System.currentTimeMillis()-Mystore.getStore().getSastatus().get(saname);
                    if(timeinterval<Mystore.getStore().getRegSaved().get(saname).getMainperiod()*1000*3){
                        sast=new SaState(saname,"online");
                    }
                    else{
                         sast=new SaState(saname,"offline");
                    }
                }else{
                     sast=new SaState(saname,"offline");
                }

                sas.add(sast);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }



        return sas;
    }

    public List<AndroidResults> androidTotalres(int num){
        List<AndroidResults> andres=new LinkedList<>();
        ResultSet set=new NmapJobsResultsDaoImpl().selectAllAndroid(num);

        try {
            if (!set.next()){
                System.out.println("ton hpiame");
            }
            while (set.next()) {
                AndroidResults res=new AndroidResults(set.getString(3),set.getString(4),set.getString(6),set.getString(6),
                        set.getString(7),set.getString(8),set.getInt(16),set.getString(17));
                andres.add(res);

            }
            return andres;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return andres;
    }
    public List<AndroidResults> androidSaResults(String sa,int num){
        List<AndroidResults> andres=new LinkedList<>();
        ResultSet set=new NmapJobsResultsDaoImpl().selectAndroid(sa,num);

        try {

            while (set.next()) {
                AndroidResults res=new AndroidResults(set.getString(3),set.getString(4),set.getString(6),set.getString(6),
                        set.getString(7),set.getString(8),set.getInt(16),set.getString(17));
                andres.add(res);
            }
            return andres;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return andres;
    }
    public List<NmapJobs> getalljobs(){
        List<NmapJobs> listjobs=new LinkedList<>();
        ResultSet set=new NmapJobsDaoImpl().selectAll();
        try{
            while(set.next()){
                NmapJobs nmj=new NmapJobs(set.getInt(1),set.getString(2),set.getString(3),set.getInt(4),set.getString(5));
                listjobs.add(nmj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listjobs;
    }
}
