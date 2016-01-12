    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Comment_Playlist;
import dto.Comment_Song;
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
import view.PlaySong;
import view.PlaySongInList;

/**
 *
 * @author khacc
 */
public class CommentSongController  implements ActionListener{
    private CommentSongModel  commentSongModel = new CommentSongModel();
    private Comment_Song comment_Song = new Comment_Song();
    private ResultSet resultSet = null;
    public CommentSongController(){
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
       JButton button = (JButton) e.getSource();
       if (button.getName().equals("ButtonSubmitCMT")){
           if (PlaySong.getInstane().getTextComment().equals("")) return;
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
           Date date = new Date();
           comment_Song.setSongID(PlaySong.getInstane().cmt_song.getSongID());
           comment_Song.setUserID(1); // sau nay them vo
           comment_Song.setCommentText(PlaySong.getInstane().getTextComment());
           comment_Song.setCommentDate(dateFormat.format(date));
           
           if (commentSongModel.insertDataForTable(comment_Song)) { 
               System.out.println("Thanh cong");
               PlaySong.getInstane().setDataCommentList(PlaySong.getInstane().jp_list_cmt, PlaySong.getInstane().gb_cmt);
               PlaySong.getInstane().clearTextComment();
               return;
           }
                   
           System.out.println("error");
       }
                
    }
    
    public ArrayList<Comment_Song> getAllCommentBySongID(Comment_Song cmt_song) {
        ArrayList<Comment_Song> arr_comment_Song = new ArrayList<Comment_Song>();
        arr_comment_Song.clear();
        resultSet = commentSongModel.getAllCommentBySongID(cmt_song);
        try {
            while (resultSet.next()){
                Comment_Song c_Song = new Comment_Song();
                c_Song.setUserID(resultSet.getInt(Comment_Playlist.col_user_id));
                c_Song.setCommentText(resultSet.getString(Comment_Song.col_comment_text));
                c_Song.setCommentDate(resultSet.getString(Comment_Song.col_comment_date));
                arr_comment_Song.add(c_Song);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentSongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr_comment_Song;
    }
    
}
