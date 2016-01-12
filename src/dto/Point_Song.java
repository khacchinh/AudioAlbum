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
public class Point_Song {
    public final static String col_id = "id";
    public final static String col_playlist_id = "song_id";
    public final static String col_user_id = "user_id";
    public final static String col_point = "point";
    public final static String col_date = "date";
    public final static String col_note = "note";
    
    private int id;
    private int song_id;
    private int user_id;
    private int point;
    private Date date;
    private String note;
    
    public Point_Song(){}
    
    public Point_Song(int id, int song_id, int user_id, int point, Date date, String note){
        this.id = id;
        this.song_id = song_id;
        this.user_id = user_id;
        this.point = point;
        this.date = date;
        this.note = note;
    }
    
    public int getID(){
        return id;
    }
    
    public int getSongID(){
        return song_id;
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
    
    public void setSongID(int id){
        this.song_id = id;
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
