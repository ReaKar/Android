package DataSources.DataSourcesImpl;
import DataSources.AdminImpl.Admin;
import DataSources.AdminImpl.AdminDaoImpl;
import DataSources.SAImpl.SaInfo;
import DataSources.SAImpl.SaInfoDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoPatternDemo {
	 public static void main(String[] args) {
	      DaoInterface daoInterface = new AdminDaoImpl();
	      Admin admin=new Admin(20,"rea","mousitsa"); //dhmiourgw antikeimeno g t insert
	      Admin admin2=new Admin(20,"rea","mousitsa"); //dhmiourgw antikeimeno gia to delete
	      DaoInterface daoInterfacec = new SaInfoDaoImpl();
	      SaInfo sa=new SaInfo("aaahah","rea","mousitsa","mpla","op","opa",5);
	      SaInfo sa2=new SaInfo("aaahah","rea","mousitsa","mpla","op","opa",5);
		 String idh;
		 String devicename ;
		 String inter;
		 String addrmac;
		 String os;
		 String nmapversion ;

		 try {
	     daoInterface.insert(admin);
	     daoInterfacec.insert(sa);
	     ResultSet rs=daoInterface.select(5); //epilegw
	     ResultSet rt=daoInterfacec.select("20"); //epilegw
	      daoInterface.delete(admin2);
	      daoInterfacec.delete(sa2);
			 int id ;
			 String username;
			 String pass;
	      if(rs.next()) {
			   id = rs.getInt("idadmin");
			   username = rs.getString("username");
			   pass = rs.getString("password");
			  System.out.println("Select specific");
			  System.out.println(id + " " + username + " " + pass);
		  }
		  if(rt.next()) {

			   idh = rt.getString("hashkey");
			   devicename = rt.getString("deviceName");
			   inter = rt.getString("InterfaceIP");
			   addrmac = rt.getString("InterfaceMacAddr");
			   os = rt.getString("OsVersion");
			   nmapversion = rt.getString("NmapVersion");

			  System.out.println("Select specific");
			  System.out.println(idh + " " + devicename + " " + inter + " " + addrmac + " " + os + " " + nmapversion);
		  }
	      //print all students
	      rs=daoInterface.selectAll();
	      rt=daoInterfacec.selectAll();
	      
	      System.out.println("Select all" );
			while(rs.next()){
			 id=rs.getInt("idadmin");
			 username=rs.getString("username");
			 pass=rs.getString("password");
			
			System.out.println(id+" "+username+" "+pass );
			}
			while(rt.next()){
			       idh=rt.getString("hashkey");
				   devicename=rt.getString("deviceName");
				   inter=rt.getString("InterfaceIP");
				   addrmac=rt.getString("InterfaceMacAddr");
				  os=rt.getString("OsVersion");
				   nmapversion=rt.getString("NmapVersion");
			
				  System.out.println(idh+" " +devicename+" " + inter+" " +addrmac+" "+os+" "+nmapversion );
			}
	      }
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
}

