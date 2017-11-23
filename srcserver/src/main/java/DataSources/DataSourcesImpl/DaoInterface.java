package DataSources.DataSourcesImpl;
import java.sql.ResultSet;


public interface DaoInterface {
	  ResultSet selectAll();
	    ResultSet select(Object id);
	   
	    void insert(Object a);
	    void delete(Object a);

}
