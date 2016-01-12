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
public class Song {
    public final static String col_id = "song_id";
    public final static String col_name = "song_name";
    public final static String col_genres_id = "genres_id";
    public final static String col_author_id = "author_id";
    public final static String col_user_id = "user_id";
    public final static String col_date_upload = "song_date_upload";
    public final static String col_countlisten = "song_countlisten";
    public final static String col_note = "song_note";
    public final static String col_src = "src";
    public final static String col_lyrics = "song_lyrics";
    public final static String col_singer = "song_singer";
    public final static String col_songlike = "song_like";
    
    private int song_id;
    private String song_name;
    private int genres_id;
    private int author_id;
    private int user_id;
    private Date song_date_upload;
    private int song_countlisten;
    private String song_note;
    private String src;
    private String song_lyrics;
    private int song_singer;
    
    
    public Song(){}
    public Song(int id, String name, int genres_id, int author_id, int user_id, Date date_up, int count, String note){
        song_id = id;
        song_name = name;
        this.genres_id = genres_id;
        this.author_id = author_id;
        this.user_id = user_id;
        song_date_upload = date_up;
        song_countlisten = count;
        song_note = note;
    }
    
    public int getSongID(){
        return this.song_id;
    }
    
    public String getSongName(){
        return this.song_name;
    }
    
    public int getGenresID(){
        return this.genres_id;
    }
    
    public int getAuthorID(){
        return this.author_id;
    }
    
    public int getUserID(){
        return this.user_id;
    }
    
    public Date getDateUpLoad(){
        return this.song_date_upload;
    }
    
    public int getCountListen(){
        return this.song_countlisten;
    }
    
    public String getSongNote(){
        return this.song_note;
    }
    
    public String getSongsrc() {
        return this.src;
    }
    
    public String getSong_lyrics() {
        return song_lyrics;
    }

    public int getSong_singer() {
        return song_singer;
    }
    
    public void setSongID(int id){
        this.song_id = id;
    }
    
    public void setSongName(String name){
        this.song_name = name;
    }
    
    public void setGenresID(int id){
        this.genres_id = id;
    }
    
    public void setAuthorID(int id){
        this.author_id = id;
    }
    
    public void setUserID(int id){
        this.user_id = id;
    }
    
    public void setSongDateUpLoad(Date date){
        this.song_date_upload = date;
    }
    
    public void setSongCountListen(int count){
        this.song_countlisten = count;
    }
    
    public void setSongNote(String note){
        this.song_note = note;
    }
    
     public void setSongsrc(String src){
        this.src = src;
    }
    
    public void setSong_lyrics(String song_lyrics) {
        this.song_lyrics = song_lyrics;
    }

    public void setSong_singer(int song_singer) {
        this.song_singer = song_singer;
    }
    
}
