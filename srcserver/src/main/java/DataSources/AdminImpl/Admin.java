package DataSources.AdminImpl;

public class Admin {
	
	private int idadmin;
	   private String username;
	   private String password;

	  public Admin(int idadmin,String username,String password){
		  this.idadmin = idadmin;
		  this.username = username;
	      this.password = password;
	      
	      
	   }
	   public int getId(){
		   return idadmin;
	   }
    
	   public void setId(int idadmin){
		   this.idadmin=idadmin;
		   
	   }
	   public String getUsername() {
	      return username;
	   }

	   public void setUsername(String username) {
	      this.username = username;
	   }

	   public String getPassword() {
	      return password;
	   }

	   public void setPassword(String password) {
	      this.password = password;
	   }

}
