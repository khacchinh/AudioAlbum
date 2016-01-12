/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dto.Singer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dto.*;
import java.sql.SQLException;
import static model.PlaylistModel.TABLE_NAME;

/**
 *
 * @author khacc
 */
public class CommentSongModel extends DatabaseHelper{
    public static String TABLE_NAME = "tbl_comment_song";
    private Comment_Song comment_Song = new Comment_Song();
    private PreparedStatement preStatemet;
    private String strQuery;
    public CommentSongModel(){
        super();
    }
    
    //get data from table user
    public ResultSet getAllDataTable(){
        return getAllDataTable(TABLE_NAME);
    }
    
    public ResultSet getAllCommentBySongID(Comment_Song cmt_song){
        ResultSet resultSet = null;
        try{
            strQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + Comment_Song.col_song_id  + " = ? ";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, cmt_song.getSongID());
            resultSet = preStatemet.executeQuery();
        }
        catch (SQLException e){
            System.err.println("error");
        }
        return resultSet;
    }
    
    public boolean insertDataForTable(Comment_Song comment_Song){
        try{
            strQuery = "INSERT INTO " + TABLE_NAME + " ( "+Comment_Song.col_song_id+", "+Comment_Song.col_user_id + ", " 
                    + Comment_Song.col_comment_text + ", " + Comment_Song.col_comment_date + ", " 
                    + Comment_Song.col_comment_like + ", " + Comment_Song.col_comment_dislike + ")"
                    + " VALUES (?,?,?,?,?,?)";
            preStatemet = getConnection().prepareStatement(strQuery);
            preStatemet.setInt(1, comment_Song.getSongID());
            preStatemet.setInt(2, comment_Song.getUserID());
            preStatemet.setString(3, comment_Song.getCommentText());
            preStatemet.setString(4, comment_Song.getCommentDate());
            preStatemet.setInt(5, comment_Song.getCommentLike());
            preStatemet.setInt(6, comment_Song.getCommentDislike());
            System.out.println(preStatemet);
            preStatemet.executeUpdate();
            return true;    
        }
        catch (Exception e){
            return false;
        }
    }
    
    /*
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
            preStatemet.setDate(2, singer.getSingerBirthday());
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
    */
}
