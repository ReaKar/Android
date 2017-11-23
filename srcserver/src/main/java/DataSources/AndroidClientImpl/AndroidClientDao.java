package DataSources.AndroidClientImpl;


import DataSources.DataSourcesImpl.DaoInterface;
import DataSources.DataSourcesImpl.JDBCSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by giannis on 2/8/16.
 */
public class AndroidClientDao implements DaoInterface {

    //list is working as a database
    Connection conn = JDBCSingleton.getInstance();


    //retrive list of students from the database

public ResultSet selectAll(){
    ResultSet res;
    res=null;
    return res;
}
    public void insert(Object admin) {


        String pass = ((AndroidClient) admin).getPassword();
        String username = ((AndroidClient) admin).getUsername();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("insert into client " + " ( username ,password) values " + "( ?,?)");


            stmt.setString(1, username);
            stmt.setString(2, pass);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void delete(Object admin) {


        String pass = ((AndroidClient) admin).getPassword();
        String username = ((AndroidClient) admin).getUsername();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("delete from client where  username=? and password=?");


            stmt.setString(1, username);
            stmt.setString(2, pass);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet select(Object id) {
        PreparedStatement stmt;

        ResultSet set = null;
        try {
            stmt = conn.prepareStatement("select * FROM client WHERE username=?");

            stmt.setString(1, (String) id);//edw vazw gia thn sql g t poso ? exw
            set = stmt.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return set;
    }
}