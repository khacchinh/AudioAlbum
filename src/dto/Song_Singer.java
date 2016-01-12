/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author khacc
 */
public class Song_Singer {
    public final static String col_song_id = "song_id";
    public final static String col_singer_id = "singer_id";
    
    private int song_id;
    private int singer_id;
    
    public Song_Singer(){}
    public Song_Singer(int song_id, int singer_id){
        this.song_id = song_id;
        this.singer_id = singer_id;
    }
    
    public int getSongID(){
        return this.song_id;
    }
    
    public int getSingerID(){
        return this.singer_id;
    }
    
    public void setSongID(int id){
        this.song_id = id;
    }
    
    public void setSingerID(int id){
        this.singer_id = id;
    }
}
