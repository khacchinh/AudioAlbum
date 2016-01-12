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
public class Author {
    public final  static String col_id = "author_id";
    public final static String col_name = "author_name";
    public final static String col_birthday = "author_birthday";
    public final static String col_email = "author_email";
    public final static String col_address = "author_address";
    
    private int author_id;
    private String author_name;
    private String author_birthday;
    private String author_email;
    private String author_address;
    
    public Author(){}
    
    public Author(int id, String name, String birth, String email, String address){
        author_id = id;
        author_name = name;
        author_birthday = birth;
        author_email = email;
        author_address = address;
    }
    
    public int getAuthorID(){
        return author_id;
    }
    
    public String getAuthorName(){
        return author_name;
    }
    
    public String getAuthorBirthday(){
        return author_birthday;
    }
    
    public String getAuthorEmail(){
        return author_email;
    }
    
    public String getAuthorAddress(){
        return author_address;
    }
    
    
    public void setAuthorID(int id){
        author_id = id;
    }
    
    public void setAuthorName(String name){
        author_name = name;
    }
    
    public void setAuthorBirthday(String birth){
        author_birthday = birth;
    }
    
    public void setAuthorEmail(String email){
        author_email = email;
    }
    
    public void setAuthorAddress(String address){
        author_address = address;
    }
    
}
