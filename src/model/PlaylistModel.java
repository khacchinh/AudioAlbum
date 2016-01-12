/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dto.*;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.nio.cs.ext.GB18030;

/**
 *
 * @author khacc
 */
public class PlaylistModel extends DatabaseHelper {
    public static String TABLE_NAME = "tbl_playlist";
    private PreparedStatement preStatemet;
    private String strQuery;
    public PlaylistModel(){
        super();
    }
    
    //get data from table user
    public ResultSet getAllDataTable(){
        return getAllDataTable(TABLE_NAME);
    }
    
    public ResultSet getAllDataForPage(int start, int offset, User user){
        return getAllDataTableForPage(TABLE_NAME, Playlist.col_id, start, offset, user);
    }
    
    public ResultSet getAllDataForPageSearchOwn(int start, int offset, User user, String name){
        ResultSet resultSet = null;
        try{
            strQuery = "select * from " + TABLE_NAME + " where playlist_name like ? and "
                    + " user_id = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, name);
            preStatemet.setInt(2, user.getUserID());
            System.out.println(preStatemet);
            resultSet = preStatemet.executeQuery();
        }
        catch (Exception e){
        }
        return resultSet;
    }
    
    public ResultSet getAllDataForPageSearch(int start, int offset, String name){
        ResultSet resultSet = null;
        try{
            strQuery = "select * from " + TABLE_NAME + " where playlist_name like ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, name);
            System.out.println(preStatemet);
            resultSet = preStatemet.executeQuery();
        }
        catch (Exception e){
        }
        return resultSet;
    }
    
    public ResultSet getDataForPlaylistHit(){
        String strQuery = "CALL get_playlist_hight()";
        CallableStatement callableStatement = null;
        try {
            callableStatement = getConnection().prepareCall(strQuery);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getDataofCall(callableStatement);
    }
    
    public ResultSet getDataForPlaylist_Genres(String genres, int start, int offset){
        String strQuery = "CALL get_playlist_genres(?,?,?)";
        CallableStatement callableStatement = null;
        try {
            callableStatement = getConnection().prepareCall(strQuery);
            callableStatement.setString(1, genres);
            callableStatement.setInt(2, start);
            callableStatement.setInt(3, offset);
            System.out.println(callableStatement);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getDataofCall(callableStatement);
    }
    
    public ResultSet getAllPlaylistNameByUser(User user){
        ResultSet resultSet = null;
        try{
            strQuery = "select playlist_name from " + TABLE_NAME + " where user_id = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, user.getUserID());
            resultSet = preStatemet.executeQuery();
        }
        catch (Exception e){
        }
        return resultSet;
    }
    
    public int getRowCount() throws SQLException{
        ResultSet resultSet = getAllDataTable(TABLE_NAME);
        resultSet.last();
        int size = resultSet.getRow();
        resultSet.beforeFirst();
        return size;
    }
    public boolean insertDataForTable(Playlist playlist){
        try{
            strQuery = "INSERT INTO " + TABLE_NAME + " ( "+playlist.col_name+", "+playlist.col_user_id + ", " 
                    + playlist.col_date_create + ", " + playlist.col_note + ")"
                    + " VALUES (?,?,?,?)";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, playlist.getPlaylistName());
            preStatemet.setString(2, String.valueOf(playlist.getUserID()));
            preStatemet.setString(3, playlist.getDateCreate());
            preStatemet.setString(4, playlist.getPlaylistNote());
            System.out.println(preStatemet);
            preStatemet.executeUpdate();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    
    public boolean updateDateFromTable(Playlist playlist){
        try{
            strQuery = "UPDATE " + TABLE_NAME + " SET " 
                    + Playlist.col_name + " = ?, "
                    + Playlist.col_user_id + " = ?, "
                    + Playlist.col_date_create + " = ?, "
                    + Playlist.col_note + " = ?, "
                    + Playlist.col_countdisplay + " = ?"
                    + " WHERE " + Playlist.col_id + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(6, playlist.getPlaylistID());
            preStatemet.setString(1, playlist.getPlaylistName());
            preStatemet.setInt(2, playlist.getUserID());
            //preStatemet.setDate(3, new java.sql.Date(playlist.getDateCreate().getTime()));
            preStatemet.setString(4, playlist.getPlaylistNote());
            preStatemet.setInt(5, playlist.getCountListen());
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
    
    public boolean deleteDateFromTable(Playlist playlist){
        try{
            strQuery = "DELETE FROM " + TABLE_NAME + " WHERE " 
                    + Playlist.col_id + " = ?" ;
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, playlist.getPlaylistID());
            preStatemet.executeUpdate();
            closeConn();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    public int getIDByName(Playlist playlist){
        int str = 0;
        try {
            strQuery = "SELECT " + Playlist.col_id +" FROM "+TABLE_NAME
                        + " WHERE " + Playlist.col_name + " = ?";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, playlist.getPlaylistName());
            ResultSet resultSet = preStatemet.executeQuery();
            while (resultSet.next())
                str =  resultSet.getInt(Playlist.col_id);
        } catch (Exception e) {
             System.out.println("adsad");
             e.printStackTrace();
        }
        return str;
    }
    
    public ResultSet getSongByPlaylistID(Playlist_Song playlist_Song){
        String strQuery = "CALL getSongByPlaylistID(?)";
        CallableStatement callableStatement = null;
        try {
            callableStatement = getConnection().prepareCall(strQuery);
            callableStatement.setInt(1, playlist_Song.getPlaylistID());
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getDataofCall(callableStatement);
    }
    
    public void updateLikePlaylist(int playlistid){
        String strQuery = "CALL upLikePlaylist(?)";
        CallableStatement callableStatement = null;
        try {
            callableStatement = getConnection().prepareCall(strQuery);
            callableStatement.setInt(1, playlistid);
            callableStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void upCountDisplay(int playlistid){
        String strQuery = "CALL upCountDisplay(?)";
        CallableStatement callableStatement = null;
        try {
            callableStatement = getConnection().prepareCall(strQuery);
            callableStatement.setInt(1, playlistid);
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
            strQuery = "select * from tbl_playlist where playlist_name like ? ";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setString(1, "%"+key+"%");
            System.out.println(preStatemet);
            resultSet = preStatemet.executeQuery();
            while (resultSet.next()){
                str = resultSet.getString(Playlist.col_id) + ":" +
                        resultSet.getString(Playlist.col_name);
                arrayList.add(str);
            }
        }
        catch (Exception e){
        }
        return arrayList;
    }
}
