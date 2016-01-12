/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.PlaylistController;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.FrameMain;
import view.PanelPlaylistOnline;

/**
 *
 * @author khacc
 */
public class TabbPanelAction implements ChangeListener{
    PlaylistController playlistController = new PlaylistController();
    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        if (tabbedPane.getSelectedIndex() == 2){

            PlaylistController.offset = 10;
            PlaylistController.lastOnline = playlistController.getDataForPlaylist_Genres(PanelPlaylistOnline.getInstant().getGenres(), 0, 1000).size() - PlaylistController.offset;
            if (PlaylistController.lastOnline <= 0 ) {
                    PlaylistController.page = PlaylistController.lastOnline / PlaylistController.offset + 1;
            }
            else 
                PlaylistController.page = (PlaylistController.lastOnline + PlaylistController.offset) / PlaylistController.offset + 1;
            PanelPlaylistOnline.getInstant().updatePagePlaylist(PlaylistController.cur_page, PlaylistController.page);
                
            System.out.println(PlaylistController.lastOnline);

            PlaylistController.offset = 5;
            PlaylistController.lastOwn =  playlistController.getRowCount() - PlaylistController.offset;
            FrameMain.getInstane().updatePlaylistCount(PlaylistController.lastOwn + PlaylistController.offset);
            PlaylistController.page = (PlaylistController.lastOwn + PlaylistController.offset) / PlaylistController.offset + 1;
            FrameMain.getInstane().updatePagePlaylist(PlaylistController.cur_page, PlaylistController.page);
        }
    }
    
}
