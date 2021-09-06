package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    public String name;
    public Connection con = null;
    public Statement st = null;

    public DB(String name){
        //Getting stuff
        this.name = name;
    }

    public void openDatabase(){
        //Creating or Opening Database
        System.out.println("Opening Database");
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:"+name+".db");
            st = con.createStatement();
        }catch(Exception e){
            System.out.println("Failed to Open Database: "+name);
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void executeUpdate(String sql){
        try {
            System.out.println("Execute update -----------");
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error in executing update: "+name+" "+e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql){
        try {
            System.out.println("Executing SQl");
            return st.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error in executing Query: "+name);
        }
        return null;
    }

    public void closeAll(){
       try {
           System.out.println("Closing statement and connections");
           st.close();
           con.close();
           con = null;
           st = null;
       } catch (SQLException e) {
           System.out.println(e.getMessage()+"Closing error");
       }

    }

    public String getName(){
        return name;
    }

}
