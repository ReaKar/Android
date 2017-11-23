package DataSources.JobsImpl;

import DataSources.AdminImpl.Admin;
import DataSources.DataSourcesImpl.DaoInterface;
import DataSources.DataSourcesImpl.JDBCSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NmapJobsDaoImpl implements DaoInterface {
	Connection conn= JDBCSingleton.getInstance();
	
	public NmapJobsDaoImpl(){
  		
	   }
	 public ResultSet selectAll() {
		 
		   PreparedStatement stmt ;
		   ResultSet set=null;
		   try {
				stmt = conn.prepareStatement("select * FROM nmapjobs ");
		
		      
		      set=stmt.executeQuery();
		    
		  	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			return set;

	   }


	public void insert(Object nmapjobs){

		int idnmap=((NmapJobs) nmapjobs).getIdnmapjobs();
		String col=((NmapJobs) nmapjobs).getNmapjobscol();
		String flag=((NmapJobs) nmapjobs).getFlagperiodic();
		int time=((NmapJobs) nmapjobs).getTimeperiodic();
		String hashkey=((NmapJobs) nmapjobs).getSa_hashkey();

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("insert into nmapjobs "+" (idnmapjobs, nmapjobscol ,flagperiodic,timeperiodic,SA_hashkey) values "+"(?, ?,?,?,?)");

			stmt.setInt(1, idnmap);
			stmt.setString(2,col);
			stmt.setString(3, flag);
			stmt.setInt(4, time);
			stmt.setString(5,hashkey);


			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	   
	   public void delete(Object admin){
			  
			   int id=((Admin) admin).getId();
			   String pass=((Admin) admin).getPassword();
			   String username=((Admin) admin).getUsername();
			   PreparedStatement stmt;
			      try {
					stmt = conn.prepareStatement("delete from admin where idadmin=? and username=? and password=?");
			
			      stmt.setInt(1, id);
			      stmt.setString(2,username);
			      stmt.setString(3, pass);
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
			stmt = conn.prepareStatement("select * FROM nmapjobs WHERE SA_hashkey=?");
	
	      stmt.setString(1,(String)id);//edw vazw gia thn sql g t poso ? exw
	      set=stmt.executeQuery();
	    
	  	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return set;
	}

	public int returnLast() {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM nmapjobs " +
					"ORDER BY idnmapjobs DESC " +
					"LIMIT 1");
			ResultSet set=stmt.executeQuery();
			if(!set.next()){
				return 1;
			}
			else{
				return set.getInt("idnmapjobs")+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}

}

	

