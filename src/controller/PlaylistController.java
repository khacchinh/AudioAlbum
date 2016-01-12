/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import view.*;
import model.*;
import dto.*;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author khacc
 */
public class PlaylistController implements ActionListener, ItemListener{
       private ArrayList<String> arr_playlist_name = new ArrayList<String>();
       private PlaylistModel playlistModel = new PlaylistModel();
       private static int start = 0;
       public static int lastOwn, lastOnline, offset;
       public static int current = start, page = 0, cur_page =1;
       public PlaylistController(){
            
       }
       
       public ArrayList<String> getAllDataPlaylist(){
           ResultSet resultSet = playlistModel.getAllDataTable();
           arr_playlist_name.clear();
           try{
                while(resultSet.next()){
                    arr_playlist_name.add(resultSet.getString(Playlist.col_name));
                }
                return arr_playlist_name;
           }
           catch (SQLException e){
               return null;
           }
       }
       
       public ArrayList<String> getAllDataPlaylistForPage(int start, int offset, User user){
           ResultSet resultSet = playlistModel.getAllDataForPage(start, offset, user);
           arr_playlist_name.clear();
           try{
                while(resultSet.next()){
                    arr_playlist_name.add(resultSet.getString(Playlist.col_name)+":"+ resultSet.getInt(Playlist.col_countdisplay));
                }
                return arr_playlist_name;
           }
           catch (SQLException e){
               return null;
           }
       }
       public ArrayList<String> getAllDataPlaylistForPageSearchOwn(int start, int offset, User user, String name){
           name = '%' + name + '%';
           ResultSet resultSet = playlistModel.getAllDataForPageSearchOwn(start, offset, user, name);
           arr_playlist_name.clear();
           try{
                while(resultSet.next()){
                    arr_playlist_name.add(resultSet.getString(Playlist.col_name)+":"+ resultSet.getInt(Playlist.col_countdisplay));
                }
                return arr_playlist_name;
           }
           catch (SQLException e){
               return null;
           }
       }
       
       public ArrayList<String> getAllDataPlaylistForPageSearch(int start, int offset, String name){
           name = '%' + name + '%';
           ResultSet resultSet = playlistModel.getAllDataForPageSearch(start, offset, name);
           arr_playlist_name.clear();
           try{
                while(resultSet.next()){
                    arr_playlist_name.add(resultSet.getString(Playlist.col_name)+":"+ resultSet.getInt(Playlist.col_countdisplay));
                }
                return arr_playlist_name;
           }
           catch (SQLException e){
               return null;
           }
       }
       
       public ArrayList<String> getAllPlaylistNameByUser(User user){
           ResultSet resultSet = playlistModel.getAllPlaylistNameByUser(user);
           ArrayList<String> list = new ArrayList<>();
           try{
                while(resultSet.next()){
                    list.add(resultSet.getString(Playlist.col_name));
                }
                return list;
           }
           catch (SQLException e){
               return null;
           }
       }
       
       public ArrayList<String> getDataForPlaylistHit(){
           ResultSet resultSet = playlistModel.getDataForPlaylistHit();
           ArrayList<String> arr_playlist = new ArrayList<String>();
           try{
                while(resultSet.next()){
                    arr_playlist.add(resultSet.getString(2));
                }
                return arr_playlist;
           }
           catch (SQLException e){
               return null;
           }
       }
       
       public ArrayList<String> getDataForPlaylist_Genres(String genres, int start, int offset){
           ResultSet resultSet = playlistModel.getDataForPlaylist_Genres(genres, start, offset);
           ArrayList<String> arr_playlist = new ArrayList<String>();
           try{
                while(resultSet.next()){
                    arr_playlist.add(resultSet.getString(Playlist.col_name));
                }
                return arr_playlist;
           }
           catch (SQLException e){
               return null;
           }
       }
       
       public ArrayList<String> getSongByPlaylistID(Playlist_Song playlist_Song){
           ResultSet resultSet = playlistModel.getSongByPlaylistID(playlist_Song);
           ArrayList<String> arr_song = new ArrayList<String>();
           try{
                while(resultSet.next()){
                    String str = resultSet.getString(Song.col_name) +':'+
                             resultSet.getString(Song.col_src);
                    arr_song.add(str);
                }
                return arr_song;
           }
           catch (SQLException e){
               return null;
           }
       } 
       
       public void updateLikePlaylist(int playlistid){
           playlistModel.updateLikePlaylist(playlistid);
       }
       public void upCountDisplay(int playlistid){
           playlistModel.upCountDisplay(playlistid);
       }
       public boolean deleteDateFromTable(Playlist playlist){
           playlist.setPlaylistID(getIDByPlaylistName(playlist));
           return playlistModel.deleteDateFromTable(playlist);
       }
       
       public int getIDByPlaylistName(Playlist playlist){
           return playlistModel.getIDByName(playlist);
       }
       
       public boolean insertDataToPlaylist(Playlist playlist){
           return playlistModel.insertDataForTable(playlist);
       }
    
       public int getRowCount(){
           try {
               return playlistModel.getRowCount();
           } catch (SQLException ex) {
               Logger.getLogger(PlaylistController.class.getName()).log(Level.SEVERE, null, ex);
           }
           return 0;
       }

    @Override
    public void actionPerformed(ActionEvent e) {
        int last = 0;
           try {
               if (FrameMain.getInstane().getJPanelPlaylistOwnVisible()){
                    offset = 5;
                    lastOwn = playlistModel.getRowCount() - offset;
                    FrameMain.getInstane().updatePlaylistCount(last + offset);  
                    last = lastOwn;
                    if (lastOnline <=0 )
                        page = lastOwn / offset + 1;
                   else page = (lastOwn +offset) / offset + 1;
               }
               else if (FrameMain.getInstane().getJPanelPlaylistOnlineVisible()){
                   offset = 10;
                   last = lastOnline;
                   if (lastOnline <=0 )
                        page = lastOnline / offset + 1;
                   else page = (lastOnline +offset) / offset + 1;
               }
           } catch (SQLException ex) {
               last = start;
               //FrameMain.getInstane().updatePlaylistCount(0);
               Logger.getLogger(PlaylistController.class.getName()).log(Level.SEVERE, null, ex);
           }
        JButton button = (JButton) e.getSource();
        if (button.getName().equals("ButtonFirst")){
            cur_page = 1;
            current = start;
        }
        else if (button.getName().equals("ButtonPre")){
            current = current - offset;
            cur_page--;
            if (current <= start) { current = start; cur_page = 1;}
        }
        else if (button.getName().equals("ButtonNext") && last > 0){
            current = current + offset;
            cur_page++;
            if (current >= last ) { current = last; cur_page = page; }
        }
        else if (button.getName().equals("ButtonLast") && last > 0){
            current = last;
            cur_page = page;
        } 
        if (FrameMain.getInstane().getJPanelPlaylistOwnVisible() && last >0){
            TableControlPlaylistClass.getInstance().resetPlaylistForPage(current, offset);
            FrameMain.getInstane().updatePagePlaylist(cur_page, page);
            return;
        }
        if (FrameMain.getInstane().getJPanelPlaylistOnlineVisible() && last > 0){
            PanelPlaylistOnline.getInstant().updatePagePlaylist(cur_page, page);
            ArrayList<String> arr_playlist_genres = getDataForPlaylist_Genres(PanelPlaylistOnline.getInstant().getGenres(), current, offset);
            PanelPlaylistOnline.getInstant().ressetDatatoLabelImage(arr_playlist_genres);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
            if (e.getItem().toString().equals("Mới & Hot")){
                PanelPlaylistOnline.getInstant().setDatatoLabelImage();
                return;
            }
            ArrayList<String> arr_playlist_genres = getDataForPlaylist_Genres(e.getItem().toString(), 0, offset);
            lastOnline = arr_playlist_genres.size() - offset;
            PanelPlaylistOnline.getInstant().ressetDatatoLabelImage(arr_playlist_genres);
        }
            
    }
    public ArrayList<String> getSearchDataDefenceOnKeyValue(String key){
        return playlistModel.getSearchDataDefenceOnKeyValue(key);
    }
}
