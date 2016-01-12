/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dto.Song_Singer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author khacc
 */
public class Song_SingerModel extends DatabaseHelper{
    public static String TABLE_NAME = "tbl_song_singer";
    private PreparedStatement preStatemet;
    private String strQuery;
    public Song_SingerModel(){
        super();
    }
    
    //get data from table user
    public ResultSet getAllDataTable(){
        return getAllDataTable(TABLE_NAME);
    }
    
    public boolean insertDataForTable(Song_Singer song_singer){
        try{
            strQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, song_singer.getSongID());
            preStatemet.setInt(2, song_singer.getSingerID());
            if (!preStatemet.execute()){
                closeConn();
                return false;
            }
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    /*
    public boolean updateDateFromTable(Genres genres){
        try{
            strQuery = "UPDATE " + TABLE_NAME + " SET " 
                    + Genres.col_name + " = ?, "
                    + " WHERE " + Genres.col_id + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, genres.getGenresName());
            preStatemet.setInt(2, genres.getGenresID());
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
    */
}
