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
public class Playlist {
    public final static String col_id = "playlist_id";
    public final static String col_name = "playlist_name";
    public final static String col_user_id = "user_id";
    public final static String col_date_create = "playlist_date_create";
    public final static String col_countdisplay = "playlist_countdisplay";
    public final static String col_note = "playlist_note";
    
    private int playlist_id;
    private String playlist_name;
    private int user_id;
    private String playlist_date_create;
    private int playlist_countdisplay;
    private String playlist_note;
    
    public Playlist(){}
    public Playlist(int id, String name, int user_id, String date_create, int count, String note){
        playlist_id = id;
        playlist_name = name;
        this.user_id = user_id;
        playlist_date_create = date_create;
        playlist_countdisplay = count;
        playlist_note = note;
    }
    
    public int getPlaylistID(){
        return this.playlist_id;
    }
    
    public String getPlaylistName(){
        return this.playlist_name;
    }
    
    public int getUserID(){
        return this.user_id;
    }
    
    public String getDateCreate(){
        return this.playlist_date_create;
    }
    
    public int getCountListen(){
        return this.playlist_countdisplay;
    }
    
    public String getPlaylistNote(){
        return this.playlist_note;
    }
    
    public void setPlaylistID(int id){
        this.playlist_id = id;
    }
    
    public void setPlaylistName(String name){
        this.playlist_name = name;
    }
    
    public void setUserID(int id){
        this.user_id = id;
    }
    
    public void setPlaylistDateCreate(String date){
        this.playlist_date_create = date;
    }
    
    public void setPlaylistCountListen(int count){
        this.playlist_countdisplay = count;
    }
    
    public void setPlaylistNote(String note){
        this.playlist_note = note;
    }
}
