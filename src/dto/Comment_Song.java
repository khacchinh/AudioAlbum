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
public class Comment_Song {
    public final static String col_comment_id = "comment_id";
    public final static String col_song_id = "song_id";
    public final static String col_user_id = "user_id";
    public final static String col_comment_text = "comment_text";
    public final static String col_comment_date = "comment_date";
    public final static String col_comment_like = "comment_like";
    public final static String col_comment_dislike = "comment_dislike";
    
    private int comment_id;
    private int song_id;
    private int user_id;
    private String comment_text;
    private String comment_date;
    private int comment_like;
    private int comment_dislike;
    
    public Comment_Song(){}
    
    public Comment_Song(int id, int song_id, int user_id, String comm_text, String comm_date, int comm_like, int comm_dislike){
        this.comment_id = id;
        this.song_id = song_id;
        this.user_id = user_id;
        this.comment_text = comm_text;
        this.comment_date = comm_date;
        this.comment_like = comm_like;
        this.comment_dislike = comm_dislike;
    }
    
    public int getCommentID(){
        return comment_id;
    }
    
    public int getSongID(){
        return song_id;
    }
    
    public int getUserID(){
        return user_id;
    }
    
    public String getCommentText(){
        return comment_text;
    }
    
    public String getCommentDate(){
        return comment_date;
    }
    
    public int getCommentLike(){
        return comment_like;
    }
    
    public int getCommentDislike(){
        return comment_dislike;
    }
    
    public void setCommentID(int id){
        this.comment_id = id;
    }
    
    public void setSongID(int id){
        this.song_id = id;
    }
    
    public void setUserID(int id){
        this.user_id = id;
    }
    
    public void setCommentText(String text){
        this.comment_text = text;
    }
    
    public void setCommentDate(String date){
        this.comment_date = date;
    }
    
    public void setCommentLike(int like){
        this.comment_like = like;
    }
    
    public void setCommentDislike(int dislike){
        this.comment_dislike = dislike;
    }
}
