/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @singer khacc
 */
public class Singer {
    public final static String col_id = "singer_id";
    public final static String col_name = "singer_name";
    public final static String col_birthday = "singer_birthday";
    public final static String col_email = "singer_email";
    public final static String col_address = "singer_address";
    public final static String col_note = "singer_note";
    
    private int singer_id;
    private String singer_name;
    private String singer_birthday;
    private String singer_email;
    private String singer_address;
    private String singer_note;
    
    public Singer(){}
    
    public Singer(int id, String name, String birth, String email, String address, String note){
        singer_id = id;
        singer_name = name;
        singer_birthday = birth;
        singer_email = email;
        singer_address = address;
        singer_note = note;
    }
    
    public int getSingerID(){
        return singer_id;
    }
    
    public String getSingerName(){
        return singer_name;
    }
    
    public String getSingerBirthday(){
        return singer_birthday;
    }
    
    public String getSingerEmail(){
        return singer_email;
    }
    
    public String getSingerAddress(){
        return singer_address;
    }
    
    public String getSingerNote(){
        return singer_note;
    }   
    
    public void setSingerID(int id){
        singer_id = id;
    }
    
    public void setSingerName(String name){
        singer_name = name;
    }
    
    public void setSingerBirthday(String birth){
        singer_birthday = birth;
    }
    
    public void setSingerEmail(String email){
        singer_email = email;
    }
    
    public void setSingerAddress(String address){
        singer_address = address;
    }
    
    public void setSingerNote(String note){
        singer_note = note;
    }
}
