/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dto.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author khacc
 */
public class UserModel extends DatabaseHelper {
    public static String TABLE_NAME = "tbl_user";
    private PreparedStatement preStatemet;
    private String strQuery;
    public UserModel(){
        super();
    }
    
    //get data from table user
    public ResultSet getAllDataTable(){
        return getAllDataTable(TABLE_NAME);
    }
    
    public boolean checkSignIn(User user)
    {
        try {
            strQuery = "SELECT * FROM "+TABLE_NAME
                        + " WHERE " + User.col_username + " = ? AND " 
                        + User.col_pass + " = ? ";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, user.getUserUsername());
            preStatemet.setString(2, user.getUserPassword());
            System.out.println(preStatemet);
            if (!preStatemet.execute())  {
                return false;
            }
            return preStatemet.getResultSet().first();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean checkSignUpUserName(User user)
    {
        try {
            strQuery = "SELECT * FROM "+TABLE_NAME
                        +" WHERE "+User.col_username + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, user.getUserUsername());
            System.out.println(preStatemet);
            if (!preStatemet.execute())  {
                closeConn();
                return false;
            }
            return preStatemet.getResultSet().first();
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public boolean insertDataForTable(User user){
        try{
            strQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?,?,?)";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, user.getUserID());
            preStatemet.setString(2, user.getUserUsername());
            preStatemet.setString(3, user.getUserPassword());
            preStatemet.setString(4, user.getUserEmail());
            preStatemet.setDate(5, user.getUserBirthday());
            preStatemet.setString(6, user.getUserGioitinh());
            preStatemet.setString(7, user.getUserAddress());
            preStatemet.setString(8, user.getUserNote());
            
            System.out.println(preStatemet);
            preStatemet.executeUpdate();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    
    public boolean updateDateFromTable(User user){
        try{
            strQuery = "UPDATE " + TABLE_NAME + " SET " 
                    + User.col_username + " = ?, "
                    + User.col_pass + " = ?, "
                    + User.col_email + " = ?, "
                    + User.col_birthday + " = ?, "
                    + User.col_gioitinh + " = ?, "
                    + User.col_address + " = ?, "
                    + User.col_note + " = ?, "
                    + " WHERE " + User.col_id + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(8, user.getUserID());
            preStatemet.setString(1, user.getUserUsername());
            preStatemet.setString(2, user.getUserPassword());
            preStatemet.setString(3, user.getUserEmail());
            preStatemet.setDate(4, user.getUserBirthday());
            preStatemet.setString(5, user.getUserGioitinh());
            preStatemet.setString(6, user.getUserAddress());
            preStatemet.setString(7, user.getUserNote());
            if (!preStatemet.execute()){
                closeConn();
                return false;
            }
            closeConn();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    
    public String getNameByID(User user){
        String str = null;
        try {
            strQuery = "SELECT " + User.col_username +" FROM "+TABLE_NAME
                        + " WHERE " + User.col_id + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, user.getUserID());
            ResultSet resultSet = preStatemet.executeQuery();
            while (resultSet.next())
                str =  resultSet.getString(User.col_username);
        } catch (Exception e) {
             System.out.println("adsad");
             e.printStackTrace();
            return "";
        }
        return str;
    }
    
    public String getNameByIDint(int userid){
        String str = null;
        try {
            strQuery = "SELECT " + User.col_username +" FROM "+TABLE_NAME
                        + " WHERE " + User.col_id + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, userid);
            ResultSet resultSet = preStatemet.executeQuery();
            while (resultSet.next())
                str =  resultSet.getString(User.col_username);
        } catch (Exception e) {
             System.out.println("adsad");
             e.printStackTrace();
            return "";
        }
        return str;
    }
    
    public int getIDByName(User user){
        int str = 0;
        try {
            strQuery = "SELECT " + User.col_id +" FROM "+TABLE_NAME
                        + " WHERE " + User.col_username + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, user.getUserUsername());
            ResultSet resultSet = preStatemet.executeQuery();
            while (resultSet.next())
                str =  resultSet.getInt(User.col_id);
        } catch (Exception e) {
             System.out.println("adsad");
             e.printStackTrace();
        }
        return str;
    }
}
