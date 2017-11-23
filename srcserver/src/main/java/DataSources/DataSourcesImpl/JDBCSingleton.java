package DataSources.DataSourcesImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



public class JDBCSingleton {

	 // JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/ergasia2";

	 //  Database credentials
	 static final String USER = "root";
	 static final String PASS = "zxcvbnm";
	 static Connection conn = null;
	 static JDBCSingleton instance=null;
	 private JDBCSingleton() {
		 try{
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 }catch(Exception e){
			    //Handle errors for Class.forName
			    e.printStackTrace();
			 }
		  }
	 public static Connection getInstance(){
	 Statement stmt = null;

	    if(instance==null){  
	    	instance=new JDBCSingleton();
	    }
	    return conn;
	 }
}
