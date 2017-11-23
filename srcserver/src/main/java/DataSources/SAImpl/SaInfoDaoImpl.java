package DataSources.SAImpl;
import DataSources.DataSourcesImpl.DaoInterface;
import DataSources.DataSourcesImpl.JDBCSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SaInfoDaoImpl implements DaoInterface {
	
	  //list is working as a database
	   Connection conn= JDBCSingleton.getInstance();
	   
	   
	   public SaInfoDaoImpl(){
		   

     		
	   }

	   @Override
	   public ResultSet selectAll() {
		   /* Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		      String sql = "SELECT * FROM admin";
		      try {
				ResultSet rs = stmt.executeQuery(sql);
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;*/
		   PreparedStatement stmt ;
		   ResultSet set=null;
		   try {
				stmt = conn.prepareStatement("select * FROM SA ");
		
		      
		      set=stmt.executeQuery();
		    
		  	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			return set;

	   }

     
	   public void insert(Object sa){
		
		   String idh=((SaInfo) sa).getHashkey();
		   String devicename=((SaInfo) sa).getDeviceName();
		   String inter=((SaInfo) sa).getInterfaceIP();
		   String addrmac=((SaInfo) sa).getInterfaceMacAddr();
		   String os=((SaInfo) sa).getOsVersion();
		   String nmapversion=((SaInfo) sa).getNmapVersion();
		   PreparedStatement stmt;
		      try {
				stmt = conn.prepareStatement("insert into SA "+" (hashkey,deviceName,InterfaceIP,InterfaceMacAddr,OsVersion,NmapVersion) values "+"(?, ?,?,?,?,?)");
		
		      stmt.setString(1, idh);
		      stmt.setString(2,devicename);
		      stmt.setString(3, inter);
		      stmt.setString(4, addrmac);
		      stmt.setString(5,os);
		      stmt.setString(6, nmapversion);
		      
		      stmt.executeUpdate();
		  	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	   }
	   
	   public void delete(Object sa){
		   String idh=((SaInfo) sa).getHashkey();
		   String devicename=((SaInfo) sa).getDeviceName();
		   String inter=((SaInfo) sa).getInterfaceIP();
		   String addrmac=((SaInfo) sa).getInterfaceMacAddr();
		   String os=((SaInfo) sa).getOsVersion();
		   String nmapversion=((SaInfo) sa).getNmapVersion();
			  
			   
			   PreparedStatement stmt;
			      try {
					stmt = conn.prepareStatement("delete from SA where hashkey=? and deviceName=? and InterfaceIP=? and InterfaceMacAddr=? and OsVersion=? and NmapVersion=?");
			
					stmt.setString(1, idh);
				      stmt.setString(2,devicename);
				      stmt.setString(3, inter);
				      stmt.setString(4, addrmac);
				      stmt.setString(5,os);
				      stmt.setString(6, nmapversion);
				      stmt.executeUpdate();
				      
			  	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
		   }
	@Override
	public ResultSet select(Object id) {
		String idh=(String)id;
		PreparedStatement stmt = null;
		System.out.println(id);
		ResultSet set=null;
		try {
			stmt = conn.prepareStatement("select * FROM SA WHERE hashkey=?");
	
	      stmt.setString(1,idh);//edw vazw gia thn sql g t poso ? exw
	      set=stmt.executeQuery();
	    
	  	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return set;
	}

}

	   
	   
	   
	   

