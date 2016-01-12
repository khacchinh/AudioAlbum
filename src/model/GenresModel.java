/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dto.Genres;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author khacc
 */
public class GenresModel extends DatabaseHelper{
    public static String TABLE_NAME = "tbl_genres";
    private PreparedStatement preStatemet;
    private String strQuery;
    public GenresModel(){
        super();
    }
    
    //get data from table user
    public ResultSet getAllDataTable(){
        return getAllDataTable(TABLE_NAME);
    }
    
     public ResultSet getGenres(){
        String query = "SELECT genres_name FROM " + Genres.TABLE;
        return getDataofQuery(query);
    }
    
    public boolean insertDataForTable(Genres genres){
        try{
            strQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, genres.getGenresID());
            preStatemet.setString(2, genres.getGenresName());
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
}
