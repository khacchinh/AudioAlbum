/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author khacc
 */
public class Point_Playlist {
    public final static String col_id = "id";
    public final static String col_playlist_id = "playlist_id";
    public final static String col_user_id = "user_id";
    public final static String col_point = "point";
    public final static String col_date = "date";
    public final static String col_note = "note";
    
    private int id;
    private int playlist_id;
    private int user_id;
    private int point;
    private Date date;
    private String note;
    
    public Point_Playlist(){}
    
    public Point_Playlist(int id, int playlist_id, int user_id, int point, Date date, String note){
        this.id = id;
        this.playlist_id = playlist_id;
        this.user_id = user_id;
        this.point = point;
        this.date = date;
        this.note = note;
    }
    
    public int getID(){
        return id;
    }
    
    public int getPlaylistID(){
        return playlist_id;
    }
    
    public int getUserID(){
        return user_id;
    }
    
    public int getPoint(){
        return point;
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getNote(){
        return note;
    }
    
    public void setID(int id){
        this.id = id;
    }
    
    public void setPlaylistID(int id){
        this.playlist_id = id;
    }
    
    public void setUserID(int id){
        this.user_id = id;
    }
    
    public void setPoint(int point){
        this.point = point;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public void setNote(String note){
        this.note = note;
    }
}
