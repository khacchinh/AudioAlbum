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
public class Playlist_Song {
    public final static String col_song_id = "song_id";
    public final static String colplaylist_id = "playlist_id";
    
    private int song_id;
    private int playlist_id;
    
    public Playlist_Song(){}
    public Playlist_Song(int song_id, int playlist_id){
        this.song_id = song_id;
        this.playlist_id = playlist_id;
    }
    
    public int getSongID(){
        return this.song_id;
    }
    
    public int getPlaylistID(){
        return this.playlist_id;
    }
    
    public void setSongID(int id){
        this.song_id = id;
    }
    
    public void setPlaylistID(int id){
        this.playlist_id = id;
    }
}
