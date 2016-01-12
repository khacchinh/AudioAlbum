/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Comment_Playlist;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import model.*;
import view.PlaySongInList;

/**
 *
 * @author khacc
 */
public class CommentPlaylistController  implements ActionListener{
    private CommentPlaylistModel  commentPlaylistModel = new CommentPlaylistModel();
    private Comment_Playlist comment_Playlist = new Comment_Playlist();
    private ResultSet resultSet = null;
    public CommentPlaylistController(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       JButton button = (JButton) e.getSource();
       if (button.getName().equals("ButtonSubmitCMT")){
           if (PlaySongInList.getInstane().getTextComment().equals("")) return;
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
           Date date = new Date();
           comment_Playlist.setPlaylistID(PlaySongInList.getInstane().cmt_playlist.getPlaylistID());
           comment_Playlist.setUserID(1); // sau nay them vo
           comment_Playlist.setCommentText(PlaySongInList.getInstane().getTextComment());
           comment_Playlist.setCommentDate(dateFormat.format(date));
           if (commentPlaylistModel.insertDataForTable(comment_Playlist)) { 
               System.out.println("Thanh cong");
               PlaySongInList.getInstane().setDataCommentList(PlaySongInList.getInstane().jp_list_cmt, PlaySongInList.getInstane().gb_cmt);
               PlaySongInList.getInstane().clearTextComment();
               return;
           }
           System.out.println("error");
       }
    }
    
    public ArrayList<Comment_Playlist> getCommentByPlaylistID(Comment_Playlist cmt_playlist) {
        ArrayList<Comment_Playlist> arr_comment_Playlists = new ArrayList<Comment_Playlist>();
        arr_comment_Playlists.clear();
        resultSet = commentPlaylistModel.getAllCommentByPlaylistID(cmt_playlist);
        try {
            while (resultSet.next()){
                Comment_Playlist c_Playlist = new Comment_Playlist();
                c_Playlist.setUserID(resultSet.getInt(Comment_Playlist.col_user_id));
                c_Playlist.setCommentText(resultSet.getString(Comment_Playlist.col_comment_text));
                c_Playlist.setCommentDate(resultSet.getString(Comment_Playlist.col_comment_date));
                arr_comment_Playlists.add(c_Playlist);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentPlaylistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr_comment_Playlists;
    }
    
}
