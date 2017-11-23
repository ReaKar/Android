package DataSources.AdminImpl;
import DataSources.DataSourcesImpl.DaoInterface;
import DataSources.DataSourcesImpl.JDBCSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDaoImpl implements DaoInterface {
	
	  //list is working as a database
	   Connection conn= JDBCSingleton.getInstance();

	   public AdminDaoImpl(){
	      		
	   }
	   

	   //retrive list of students from the database
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
		   PreparedStatement stmt;
		   ResultSet set=null;
		   try {
				stmt = conn.prepareStatement("select * FROM admin ");
		
		      
		      set=stmt.executeQuery();
		    
		  	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			return set;

	   }

     
	   public void insert(Object admin){
		
		   int id=((Admin) admin).getId();
		   String pass=((Admin) admin).getPassword();
		   String username=((Admin) admin).getUsername();
		   PreparedStatement stmt ;
		      try {
				stmt = conn.prepareStatement("insert into admin "+" (idadmin, username ,password) values "+"(?, ?,?)");
		
		      stmt.setInt(1, id);
		      stmt.setString(2,username);
		      stmt.setString(3, pass);
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
			   PreparedStatement stmt ;
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
		PreparedStatement stmt;

		ResultSet set=null;
		try {
			stmt = conn.prepareStatement("select * FROM admin WHERE username=?");
	
	      stmt.setString(1,(String)id);//edw vazw gia thn sql g t poso ? exw
	      set=stmt.executeQuery();
	    
	  	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return set;
	}

}
