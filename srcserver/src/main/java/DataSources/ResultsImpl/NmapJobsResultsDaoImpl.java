package DataSources.ResultsImpl;

import DataSources.DataSourcesImpl.DaoInterface;
import DataSources.DataSourcesImpl.JDBCSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NmapJobsResultsDaoImpl implements DaoInterface {
    Connection conn= JDBCSingleton.getInstance();

    public NmapJobsResultsDaoImpl(){

    }
    public ResultSet selectAll() {

        PreparedStatement stmt = null;
        ResultSet set=null;
        try {
            stmt = conn.prepareStatement("select * FROM nmapjobsresults ");


            set=stmt.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return set;

    }
    public ResultSet selectAllAndroid(int num) {

        PreparedStatement stmt = null;
        ResultSet set=null;
        try {
            stmt = conn.prepareStatement("select * FROM nmapjobsresults " +
                    "ORDER BY nmapjobs_idnmapjobs DESC " +
                    "LIMIT "+num);


            set=stmt.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return set;

    }


    public void insert(Object nmapjobsresults){

        int idnmapresult=((NmapJobsResults) nmapjobsresults).getNmapjobs_idnmapjobs();
        String scan=((NmapJobsResults) nmapjobsresults).getScanning();
        String flag=((NmapJobsResults) nmapjobsresults).getVerboseLevel();
        String debug=((NmapJobsResults) nmapjobsresults).getDebuggingLevel();
        String trace=((NmapJobsResults) nmapjobsresults).getTrace();
        String status=((NmapJobsResults) nmapjobsresults).getStatus();
        String address=((NmapJobsResults) nmapjobsresults).getAddress();
        String host=((NmapJobsResults) nmapjobsresults).getHostnames();
        String port=((NmapJobsResults) nmapjobsresults).getPorts();
        String os=((NmapJobsResults) nmapjobsresults).getOs();
        String uptime=((NmapJobsResults) nmapjobsresults).getUptime();
        String tcp=((NmapJobsResults) nmapjobsresults).getTcpsequence();
        String ip=((NmapJobsResults) nmapjobsresults).getIpidsequence();
        String tcpt=((NmapJobsResults) nmapjobsresults).getTcptssequence();

        int foreignkey=((NmapJobsResults) nmapjobsresults).getNmapjobs_idnmapjobs();
        String hashkey=((NmapJobsResults) nmapjobsresults).getSa_hashkey();



        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into nmapjobsresults "+" (scanning ,verboseLevel,debuggingLevel,trace,status,address,hostnames,ports,os,uptime,tcpsequence,ipidsequence,tcptssequence,timestamp,nmapjobs_idnmapjobs,SA_hashkey) values "+"(?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?)");


            stmt.setString(1,scan);
            stmt.setString(2, flag);
            stmt.setString(3, debug);
            stmt.setString(4,trace);
            stmt.setString(5, status);
            stmt.setString(6,address);
            stmt.setString(7, host);
            stmt.setString(8, port);
            stmt.setString(9,os);
            stmt.setString(10, uptime);
            stmt.setString(11,tcp);
            stmt.setString(12, ip);
            stmt.setString(13, tcpt);
            stmt.setInt(14,foreignkey);
            stmt.setString(15,hashkey);



            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void delete(Object nmapjobsresults){
        String idr=(String) nmapjobsresults;


        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from SA where idnmapjobsresults=?");

            stmt.setString(1, idr);

            stmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    @Override
    public ResultSet select(Object id) {
        PreparedStatement stmt = null;
        System.out.println(id);
        ResultSet set=null;
        try {
            stmt = conn.prepareStatement("select * FROM nmapjobsresults WHERE SA_hashkey=?");

            stmt.setString(1,(String)id);//edw vazw gia thn sql g t poso ? exw
            set=stmt.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return set;
    }

    public ResultSet selectAndroid(Object id,int num) {
        PreparedStatement stmt = null;
        System.out.println(id);
        ResultSet set=null;
        try {
            stmt = conn.prepareStatement("select * FROM nmapjobsresults WHERE SA_hashkey=?"+
                    "ORDER BY nmapjobs_idnmapjobs DESC " +
                    "LIMIT "+num);
            stmt.setString(1,(String)id);//edw vazw gia thn sql g t poso ? exw
            set=stmt.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return set;
    }

    public ResultSet selectWithDate(Object[] args){
        PreparedStatement stmt = null;
        String saname=(String) args[0];
        String fromdate=(String)args[1];
        String todate=(String)args[2];
        System.out.println(saname+" "+fromdate+" "+todate);
        ResultSet set=null;
        try {
            stmt = conn.prepareStatement("select * from nmapjobsresults where SA_hashkey=? and nmapjobsresults.timestamp<=? and nmapjobsresults.timestamp >=?");

            stmt.setString(1,saname);
            stmt.setString(2,fromdate);
            stmt.setString(3,todate);
            set=stmt.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return set;

    }

    public ResultSet selectAlldates(Object[] args){
        PreparedStatement stmt = null;
        String fromdate=(String)args[0];
        String todate=(String)args[1];

        ResultSet set=null;
        try {
            stmt = conn.prepareStatement("select * from nmapjobsresults where  nmapjobsresults.timestamp<=? and nmapjobsresults.timestamp >=?");


            stmt.setString(1,fromdate);
            stmt.setString(2,todate);
            set=stmt.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return set;

    }

}