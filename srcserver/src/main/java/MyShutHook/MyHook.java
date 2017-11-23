package MyShutHook;

import DataSources.DataSourcesImpl.JDBCSingleton;
import DataSources.DataSourcesImpl.Mystore;
import org.glassfish.grizzly.http.server.HttpServer;

import java.sql.SQLException;

/**
 * Created by giannis on 12/31/15.
 */
public class MyHook extends Thread {
    /**
     *Class to stop connections after shutdown is invoked
     */
    HttpServer server;
    public MyHook(HttpServer server){
        this.server=server;
    }
    public void run(){
        Mystore.getStore().stopMystore();
        try {
            JDBCSingleton.getInstance().close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        this.server.stop();
    }

}
