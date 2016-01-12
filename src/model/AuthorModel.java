/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dto.Author;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author khacc
 */
public class AuthorModel extends DatabaseHelper {
    public static String TABLE_NAME = "tbl_author";
    private PreparedStatement preStatemet;
    private String strQuery;
    
    public AuthorModel(){
        super();
    }
    
    //get data from table user
    public ResultSet getAllDataTable(){
        return getAllDataTable(TABLE_NAME);
    }
    
    public boolean insertDataForTable(Author author){
        try{
            strQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?)";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, author.getAuthorID());
            preStatemet.setString(2, author.getAuthorName());
            preStatemet.setString(3, author.getAuthorBirthday());
            preStatemet.setString(4, author.getAuthorEmail());
            preStatemet.setString(5, author.getAuthorAddress());
            
            preStatemet.executeUpdate();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    
    public boolean updateDateFromTable(Author author){
        try{
            strQuery = "UPDATE " + TABLE_NAME + " SET " 
                    + Author.col_name + " = ?, "
                    + Author.col_birthday + " = ?, "
                    + Author.col_email + " = ?, "
                    + Author.col_address + " = ?"
                    + " WHERE " + Author.col_id + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(5, author.getAuthorID());
            preStatemet.setString(1, author.getAuthorName());
            preStatemet.setString(2, author.getAuthorBirthday());
            preStatemet.setString(3, author.getAuthorEmail());
            preStatemet.setString(4, author.getAuthorAddress());
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
    
    public boolean deleteDataFromTable(Author author){
        try{
            strQuery = "DELETE FROM " + TABLE_NAME + " WHERE " 
                    + Author.col_id + " = ?" ;
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, author.getAuthorID());
            preStatemet.executeUpdate();
            closeConn();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
}
