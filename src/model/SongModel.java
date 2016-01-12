/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import controller.UserController;
import dto.*;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khacc
 */
public class SongModel extends DatabaseHelper {
    public static String TABLE_NAME = "tbl_song";
    private PreparedStatement preStatemet;
    private String strQuery;
    public SongModel(){
        super();
    }
    
    //get data from table user
    public ResultSet getAllDataTable(){
        return getAllDataTable(TABLE_NAME);
    }
    public ResultSet getAllDataForPage(int start, int offset, User user){
        return getAllDataTableForPage(TABLE_NAME, Song.col_id, start, offset, user);
    }
    
    public ResultSet getSongByGenres(int genresid){
        try{
            String strQuery = "SELECT * FROM " +TABLE_NAME+" WHERE genres_id = ?";
            PreparedStatement preStatemet = (PreparedStatement) getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, genresid);
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
    
    public int getRowCount() throws SQLException{
        UserController userController = new UserController();
        ResultSet resultSet = getAllDataByUser(TABLE_NAME,UserController.userIns);
        resultSet.last();
        int size = resultSet.getRow();
        resultSet.beforeFirst();
        return size;
    }
    
    public boolean insertDataForTable(Song song){
        try{
            strQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?,?,?)";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, song.getSongID());
            preStatemet.setString(2, song.getSongName());
            preStatemet.setInt(3, song.getGenresID());
            preStatemet.setInt(4, song.getAuthorID());
            preStatemet.setInt(5, song.getUserID());
            preStatemet.setDate(6, song.getDateUpLoad());
            preStatemet.setInt(7, song.getCountListen());
            preStatemet.setString(8, song.getSongNote());
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
    
    public boolean updateDataFromTable(Song song){
        try{
            strQuery = "UPDATE " + TABLE_NAME + " SET " 
                    + Song.col_name + " = ?, "
                    + Song.col_genres_id + " = ?, "
                    + Song.col_author_id + " = ?, "
                    + Song.col_user_id + " = ?, "
                    + Song.col_date_upload + " = ?, "
                    + Song.col_countlisten + " = ?, "
                    + Song.col_note + " = ?, "
                    + " WHERE " + Song.col_id + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(8, song.getSongID());
            preStatemet.setString(1, song.getSongName());
            preStatemet.setInt(2, song.getGenresID());
            preStatemet.setInt(3, song.getAuthorID());
            preStatemet.setInt(4, song.getUserID());
            preStatemet.setDate(5, song.getDateUpLoad());
            preStatemet.setInt(6, song.getCountListen());
            preStatemet.setString(7, song.getSongNote());
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
    
    public boolean deleteDateFromTable(Song song){
        try{
            strQuery = "DELETE FROM " + TABLE_NAME + " WHERE " 
                    + Song.col_id + " = ?" ;
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, song.getSongID());
            preStatemet.executeUpdate();
            closeConn();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    
    public ResultSet getSongByNameAndPlaylistID(int playlistid, String songname){
        String strQuery = "CALL getSongByNameAndPlaylistID(?, ?)";
        CallableStatement callableStatement = null;
        try {
            callableStatement = getConnection().prepareCall(strQuery);
            callableStatement.setInt(1, playlistid);
            callableStatement.setString(2, songname);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getDataofCall(callableStatement);
    }
    
    public void updateLikeSong(int songid){
        String strQuery = "CALL upLikeSong(?)";
        CallableStatement callableStatement = null;
        try {
            callableStatement = getConnection().prepareCall(strQuery);
            callableStatement.setInt(1, songid);
            System.out.println(callableStatement);
            callableStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean insertDataForTable(int playlistid, int songid){
        try{
            strQuery = "INSERT INTO tbl_playlist_song values (?, ?)";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, playlistid);
            preStatemet.setInt(2, songid);
            System.out.println(preStatemet);
            preStatemet.executeUpdate();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    
    public ResultSet getAllDataBXH(){
        try{
            String strQuery = "SELECT * FROM " +TABLE_NAME+" ORDER BY song_like desc";
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
    
    public String getSongByNameandUserID(String name, User user){
        String str = null;
        try {
            strQuery = "SELECT *  FROM "+TABLE_NAME
                        + " WHERE " + Song.col_name + " = ? and " + Song.col_user_id + " = ?" ;
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, name);
            preStatemet.setInt(2, user.getUserID());
            ResultSet resultSet = preStatemet.executeQuery();
            while (resultSet.next())
                str =  resultSet.getString(Song.col_src) +":"+ resultSet.getInt(Song.col_id);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return str;
    }
    
    public int getSongIDBy(String name){
        int str = 0;
        try {
            strQuery = "SELECT song_id  FROM "+TABLE_NAME
                        + " WHERE " + Song.col_name + " = ?" ;
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, name);
            ResultSet resultSet = preStatemet.executeQuery();
            while (resultSet.next())
                str =  resultSet.getInt(Song.col_id);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return str;
    }
    public void upCountDisplaySong(int songid){
        String strQuery = "CALL upCountDisplaySong(?)";
        CallableStatement callableStatement = null;
        try {
            callableStatement = getConnection().prepareCall(strQuery);
            callableStatement.setInt(1, songid);
            callableStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getSearchDataDefenceOnKeyValue(String key){
        ArrayList<String> arrayList = new ArrayList<>();
        try{
            ResultSet resultSet = null;
            String str = null;
            strQuery = "select * from tbl_song where song_name like ? ";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, "%"+key+"%");
            System.out.println(preStatemet);
            resultSet = preStatemet.executeQuery();
            while (resultSet.next()){
                str = resultSet.getString(Song.col_id) + ":" +
                        resultSet.getString(Song.col_name) +":"+
                        resultSet.getString(Song.col_src);
                arrayList.add(str);
            }
        }
        catch (Exception e){
        }
        return arrayList;
    }
    
    public String getSRCSongByName(String name){
        String str = null;
        try {
            strQuery = "SELECT *  FROM "+TABLE_NAME
                        + " WHERE " + Song.col_name + " = ?" ;
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, name);
            ResultSet resultSet = preStatemet.executeQuery();
            while (resultSet.next())
                str =  resultSet.getString(Song.col_src);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return str;
    }
}
