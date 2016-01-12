/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.UserController;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.*;

/**
 *
 * @author khacc
 */
public class DatabaseHelper {
    final static String DATABASE_NAME = "audio_dtbs";
    final static String USER_NAME = "root";
    final static String PASSWORD = "123456";
    private Connection conn;
    private static DatabaseHelper ints;
    
    public static DatabaseHelper getInstance(){
        return ints;
    }

    public DatabaseHelper() {
        ints = this;
    }
    
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME, USER_NAME, PASSWORD);
            //System.out.println("Ket noi thanh cong");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public ResultSet getAllDataTable(String table_name){
        try{
            String strQuery = "SELECT * FROM " +table_name + " where user_id = ?";
            PreparedStatement preStatemet = (PreparedStatement) getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, UserController.userIns.getUserID());
            System.out.println(preStatemet);
            if (!preStatemet.execute())  {
                closeConn();
                return null;
            }
            ResultSet rs = preStatemet.getResultSet();
            return rs;
        }
        
        catch(Exception e){
            return null;
        }
    }
    
    public ResultSet getAllDataByUser(String table_name,User user){
        try{
            String strQuery = "SELECT * FROM " +table_name+" WHERE user_id = ?";
            PreparedStatement preStatemet = (PreparedStatement) getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, user.getUserID());
            if (!preStatemet.execute())  {
                closeConn();
                return null;
            }
            ResultSet rs = preStatemet.getResultSet();
            return rs;
        }
        
        catch(Exception e){
            return null;
        }
    }
    
    public ResultSet getDataofQuery(String query){
        try{
            PreparedStatement preStatemet = (PreparedStatement) getConnection().prepareStatement(query);
            if (!preStatemet.execute())  {
                closeConn();
                return null;
            }
            ResultSet rs = preStatemet.getResultSet();
            return rs;
        }
        
        catch(Exception e){
            return null;
        }
    }
    
    public ResultSet getRowCount(String table_name){
        try{
            String strQuery = "SELECT count(*) FROM " + table_name;
            PreparedStatement preStatemet = (PreparedStatement) getConnection().prepareStatement(strQuery);
            if (!preStatemet.execute())  {
                closeConn();
                return null;
            }
            ResultSet rs = preStatemet.getResultSet();
            return rs;
        }
        
        catch(Exception e){
            return null;
        }
    }
    
    public ResultSet getAllDataTableForPage(String table_name, String col_name, int start, int offset, User user){
        try{
            String strQuery = "SELECT * FROM "+ table_name +" WHERE user_id = ? and "+ col_name +" LIMIT ?, ?";
            PreparedStatement preStatemet = (PreparedStatement) getConnection().prepareStatement(strQuery);
            preStatemet.setInt(2, start);
            preStatemet.setInt(3, offset);
            preStatemet.setInt(1, user.getUserID());
            System.out.println(preStatemet);
            if (!preStatemet.execute())  {
                closeConn();
                return null;
            }
            ResultSet rs = preStatemet.getResultSet();
            return rs;
        }
        
        catch(Exception e){
            return null;
        }
    }
    
    public ResultSet getDataofCall(CallableStatement callableStatement){
        try {      
            callableStatement.execute();
            ResultSet rs = callableStatement.getResultSet();
            return rs;
        }
        
        catch(Exception e){
            return null;
        }
    }
    
    public void closeConn() throws SQLException{
        if (!conn.isClosed())
            conn.close();
    }
    
    public static void main(String[] argv){
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.getConnection();
    }
}
