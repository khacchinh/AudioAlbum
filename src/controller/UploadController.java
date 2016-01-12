/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Administrator PC
 */
import com.sun.javafx.runtime.SystemProperties;
import java.sql.ResultSet;
import java .sql.SQLException;
import java.sql.*;
import dto.*;
import java.util.ArrayList;
import view.*;
import model.*;

public class UploadController {
    private  Song song;
    private SongModel sm = new SongModel();
    private ResultSet rs = new GenresModel().getAllDataTable();
    private ResultSet rs_sig = new SingerModel().getAllDataTable();
    private ResultSet rs_autr = new AuthorModel().getAllDataTable();
    private PreparedStatement preparedStatement;
    private String Sqlquery;
    private DatabaseHelper data = new DatabaseHelper();
    private Connection conn = data.getConnection();
    public ArrayList<Integer> id_gener = new ArrayList<Integer>();
    public ArrayList<String> name_gener = new ArrayList<String>();
    public ArrayList<Integer> id_sig = new ArrayList<Integer>();
    public ArrayList<String> name_sig = new ArrayList<String>();
    public ArrayList<Integer> id_autr = new ArrayList<Integer>();
    public ArrayList<String> name_autr = new ArrayList<String>();

    public UploadController() {
    }
    public boolean UpSucces(Song song)
    {
        if(sm.insertDataForTable(song))
        {
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList getGenresName()
    {
        try{
        while(rs.next())
        {
            name_gener.add(rs.getString(2));
        }
            return name_gener;
        }
        catch(SQLException e){
            return null;
        }
    }
    public ArrayList getGenresId(){
        
        try{
            while(rs.next()){
                id_gener.add(rs.getInt(1));
            }
            return id_gener;
        }
        catch(SQLException e){
            return null;
        }
    }
    public ArrayList getSingerId(){
        try{
            while(rs_sig.next()){
                id_sig.add(rs_sig.getInt(1));
            }
            return id_sig;
        }
        catch(SQLException e){
            return null;
        }
    }
    public ArrayList getSingerName(){
        try{
            while(rs_sig.next()){
                name_sig.add(rs_sig.getString(2));
            }
            return name_sig;
        }
        catch(SQLException e){
            return null;
        }
    }
    public ArrayList getAuthorId(){
        try{
            while(rs_autr.next()){
                id_autr.add(rs_autr.getInt(1));
            }
            return id_autr;
        }
        catch(SQLException e){
            return null;
        }
    }
    public ArrayList getAuthorName(){
        try{
            while(rs_autr.next()){
                name_autr.add(rs_autr.getString(2));
            }
            return name_autr;
        }
        catch(SQLException e){
            return null;
        }
    }
    public int getIdByNameForSinger(String name){
        try{
            Sqlquery ="SELECT singer_id  from audio_dtbs.tbl_singer where singer_name = ? ";
            preparedStatement = conn.prepareStatement(Sqlquery);
            preparedStatement.setString(1,name);
            System.out.println(preparedStatement);
            if(!preparedStatement.execute())
            {
                System.out.println("that bai 1");
                data.closeConn();
                return -1;
            }
            ResultSet rs_s = preparedStatement.getResultSet();
            int i = 0 ;
            while(rs_s.next())
             i = rs_s.getInt(1);
            return i;
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return -1;
        }
    }
    public int getIdByNameForAuthor(String name){
        try{
            Sqlquery ="SELECT author_id  from audio_dtbs.tbl_author where author_name = ? ";
            preparedStatement = conn.prepareStatement(Sqlquery);
            preparedStatement.setString(1,name);
            System.out.println(preparedStatement);
            if(!preparedStatement.execute())
            {
                System.out.println("that bai 1");
                data.closeConn();
                return -1;
            }
            ResultSet rs_s = preparedStatement.getResultSet();
            int i = 0 ;
            while(rs_s.next())
             i = rs_s.getInt(1);
            return i;
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return -1;
        }
    }
    public int getIdByNameForGenres(String name){
        try{
            Sqlquery ="SELECT genres_id  from audio_dtbs.tbl_genres where genres_name = ? ";
            preparedStatement = conn.prepareStatement(Sqlquery);
            preparedStatement.setString(1,name);
            System.out.println(preparedStatement);
            if(!preparedStatement.execute())
            {
                System.out.println("that bai 1");
                data.closeConn();
                return -1;
            }
            ResultSet rs_s = preparedStatement.getResultSet();
            int i = 0 ;
            while(rs_s.next())
             i = rs_s.getInt(1);
            return i;
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return -1;
        }
    }
    public boolean UpdateSong(int song_id,String song_name,int genres_id,int author_id,int user_id,Date date_upload,int song_count,String src,String song_img){
        try{
            Sqlquery ="INSERT INTO audio_dtbs.tbl_song (song_id, song_name, genres_id, author_id, user_id, song_date_upload, song_countlisten, src, song_img) VALUES (?,?,?,?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(Sqlquery);
            preparedStatement.setInt(1,song_id);
            preparedStatement.setString(2, song_name);
            preparedStatement.setInt(3,genres_id);
            preparedStatement.setInt(4,author_id);
            preparedStatement.setInt(5,user_id);
            preparedStatement.setDate(6, date_upload);
            preparedStatement.setInt(7,song_count);
            preparedStatement.setString(8,src);
            preparedStatement.setString(9,song_img);
            System.out.println(preparedStatement);
            if(!preparedStatement.execute()){
                closeConn();
                return false;
            }
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    public int getIdSong(){
        try{
        Sqlquery ="SELECT song_id FROM tbl_song order by song_id desc limit 1";
        preparedStatement = conn.prepareStatement(Sqlquery);
        
            System.out.println(preparedStatement);
            if(!preparedStatement.execute()){
                closeConn();
                return -1;
            }
            ResultSet r = preparedStatement.getResultSet();
            int i=0;
            while(r.next()){
                i=r.getInt(1);
            }
            return i;
        }
        catch(SQLException e){
            System.out.println(e);
            return -1;
        }
    }
    public void closeConn() throws SQLException{
        if (!conn.isClosed())
            conn.close();
    }
}
