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
public class Genres {
    public final static String TABLE = "tbl_genres";
     public final static String col_id = "genres_id";
     public final static String col_name = "genres_name";
     
     private int genres_id;
     private String genres_name;
     
     public Genres(){}   
     public Genres(int id, String name){
         genres_id = id;
         genres_name = name;
     }
     
     public int getGenresID(){
         return genres_id;
     }
     
     public String getGenresName(){
         return genres_name;
     }
     
     public void setGenresID(int id){
         genres_id = id;
     }
     
     public void setGenresName(String name){
         genres_name = name;
     }
}
