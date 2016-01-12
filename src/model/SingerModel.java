/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dto.Singer;
import dto.Song;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static model.SongModel.TABLE_NAME;

/**
 *
 * @author khacc
 */
public class SingerModel extends DatabaseHelper {
    public static String TABLE_NAME = "tbl_singer";
    private PreparedStatement preStatemet;
    private String strQuery;
    public SingerModel(){
        super();
    }
    
    //get data from table user
    public ResultSet getAllDataTable(){
        return getAllDataTable(TABLE_NAME);
    }
    
    public boolean insertDataForTable(Singer singer){
        try{
            strQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?)";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, singer.getSingerID());
            preStatemet.setString(2, singer.getSingerName());
            preStatemet.setString(3, singer.getSingerBirthday());
            preStatemet.setString(4, singer.getSingerEmail());
            preStatemet.setString(5, singer.getSingerAddress());
            preStatemet.setString(6, singer.getSingerNote());
            
            System.out.println(preStatemet);
            
            preStatemet.executeUpdate();
            return true;       
        }
        catch (Exception e){
            return false;
        }
    }
    
    public boolean updateDateFromTable(Singer singer){
        try{
            strQuery = "UPDATE " + TABLE_NAME + " SET " 
                    + Singer.col_name + " = ?, "
                    + Singer.col_birthday + " = ?, "
                    + Singer.col_email + " = ?, "
                    + Singer.col_address + " = ?, "
                    + Singer.col_note + " = ?"
                    + " WHERE " + Singer.col_id + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(6, singer.getSingerID());
            preStatemet.setString(1, singer.getSingerName());
            preStatemet.setString(2, singer.getSingerBirthday());
            preStatemet.setString(3, singer.getSingerEmail());
            preStatemet.setString(4, singer.getSingerAddress());
            preStatemet.setString(5, singer.getSingerNote());
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
    
    public boolean deleteDataFromTable(Singer singer){
        try{
            strQuery = "DELETE FROM " + TABLE_NAME + " WHERE " 
                    + Song.col_id + " = ?" ;
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, singer.getSingerID());
            preStatemet.executeUpdate();
            closeConn();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    
    public String getNameByIDint(int singerid){
        String str = null;
        try {
            strQuery = "SELECT " + Singer.col_name +" FROM "+TABLE_NAME
                        + " WHERE " + Singer.col_id + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, singerid);
            System.out.println(preStatemet);
            ResultSet resultSet = preStatemet.executeQuery();
            while (resultSet.next())
                str =  resultSet.getString(Singer.col_name);
        } catch (Exception e) {
             System.out.println("adsad");
             e.printStackTrace();
            return "";
        }
        return str;
    }
}
