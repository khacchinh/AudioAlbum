/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import model.SongModel;
import view.PanelSongOffline;

/**
 *
 * @author VMQ
 */
public class SongController implements ActionListener {
       private ArrayList<String> arr_song_id = new ArrayList<String>();
       private ArrayList<String> arr_song_name = new ArrayList<String>();
       private SongModel songModel = new SongModel();
       private static int start = 0, offset = 5, last = 0;
       private static int current = start, page = 0, cur_page =1;
       public SongController(){
           
       }
       
       public ArrayList<String> getAllDataSong(){
           ResultSet resultSet = songModel.getAllDataTable();
           arr_song_id.clear();
           arr_song_name.clear();
           try{
                while(resultSet.next()){
                    arr_song_id.add(resultSet.getString(Song.col_id));
                    arr_song_name.add(resultSet.getString(Song.col_name));
                }
                return arr_song_name;
           }
           catch (SQLException e){
               return null;
           }
       }
       
       public ArrayList<String> getAllDataSongForPage(int start, int offset, User user){
           ResultSet resultSet = songModel.getAllDataForPage(start, offset, user);
           arr_song_id.clear();
           arr_song_name.clear();
           try{
                while(resultSet.next()){
                    arr_song_id.add(resultSet.getString(Song.col_id));
                    arr_song_name.add(resultSet.getString(Song.col_name)+":"+ resultSet.getInt(Song.col_countlisten));
                }
                
                return arr_song_name;
           }
           catch (SQLException e){
               //System.out.println("error code"+e.getErrorCode());
               return null;
           }
       }
       
       public boolean deleteDataFromTable(Song song){
           int index = arr_song_name.indexOf(song.getSongName());
           song.setSongID(Integer.parseInt(arr_song_id.get(index)));
           return songModel.deleteDateFromTable(song);
       }
       
       public boolean insertDataToSong(Song song){
           return songModel.insertDataForTable(song);
       }
       
       public int getRowCount(){
        try {
            return songModel.getRowCount();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
           try {
               last = songModel.getRowCount() - offset;
               page = (last +offset) / offset + 1;   
               PanelSongOffline.getInstane().updateSongCount(last + offset);
           } catch (SQLException ex) {
               last = start;
               PanelSongOffline.getInstane().updateSongCount(0);
               Logger.getLogger(SongController.class.getName()).log(Level.SEVERE, null, ex);
           }
        System.out.println("e "+e.getSource());
        JButton button = (JButton) e.getSource();
        System.out.println("b "+button.getName());
        if (button.getName() == "First"){
            System.out.println("first clicked");
            cur_page = 1;
            current = start;
            TableControlSongClass.getInstance().resetSongForPage(current, offset);
        }
        else if (button.getName() == "Pre"){
            System.out.println("pre clicked");
            current = current - offset;
            cur_page--;
            if (current < start) { current = start; cur_page = 1;}
            TableControlSongClass.getInstance().resetSongForPage(current, offset);
        }
        else if (button.getName() == "Next"){
            System.out.println("next clicked");
            current = current + offset;
            cur_page++;
            if (current > last ) { current = last; cur_page = page; }
            TableControlSongClass.getInstance().resetSongForPage(current, offset);
        }
        else if (button.getName() == "Last"){
            System.out.println("last clicked");
            current = last;
            cur_page = page;
            TableControlSongClass.getInstance().resetSongForPage(current, offset);
        } 
        PanelSongOffline.getInstane().updatePageSong(cur_page, page);
    }
    
    public String getSongByNameAndPlaylistID(int playlistid, String songname){
           ResultSet resultSet = songModel.getSongByNameAndPlaylistID(playlistid, songname);
           String song = null;
           try{
                while(resultSet.next()){
                    String str = 
                             resultSet.getString(Song.col_src) + ':' +
                             resultSet.getInt(Song.col_id);
                    song = str;
                }
                return song;
           }
           catch (SQLException e){
               return "";
           }
       } 
    
    public void updateLikeSong(int songid){
        songModel.updateLikeSong(songid);
    }
    public boolean insertDataForTable(int playlistid, int songid){
        return songModel.insertDataForTable(playlistid, songid);
    }
    public String getSongByNameandUserID(String name, User user){
        return songModel.getSongByNameandUserID(name, user);
    }
    public void upCountDisplaySong(int songid){
        songModel.upCountDisplaySong(songid);
    }
    
    public int getSongIDByName(String name){
        return songModel.getSongIDBy(name);
    }
    public ArrayList<String> getSearchDataDefenceOnKeyValue(String key){
        return songModel.getSearchDataDefenceOnKeyValue(key);
    }
     public String getSRCSongByName(String name){
         return songModel.getSRCSongByName(name);
     }
}
