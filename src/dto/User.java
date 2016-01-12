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
public class User {
    public final static String col_id = "user_id";
    public final static String col_username = "user_username";
    public final static String col_pass = "user_password";
    public final static String col_email = "user_email";
    public final static String col_birthday = "user_birthday";
    public final static String col_gioitinh = "user_gioitinh";
    public final static String col_address = "user_address";
    public final static String col_note = "user_note";
    
    private int user_id;
    private String user_username;
    private String user_password;
    private String user_email;
    private Date user_birthday;
    private String user_gioitinh;
    private String user_address;
    private String user_note;
    
    
    public User(){}
    public User(int id, String username, String pass, String email, Date birth, String gioitinh, String address, String note){
        user_id = id;
        user_username = username;
        user_password = pass;
        user_email = email;
        user_birthday = birth;
        user_gioitinh = gioitinh;
        user_address = address;
        user_note = note;
    }
    
    public int getUserID(){
        return user_id;
    }
    
    public String getUserUsername(){
        return user_username;
    }
    
    public String getUserPassword() {
        return user_password;
    }
    
    public String getUserEmail(){
        return user_email;
    }
    
    public Date getUserBirthday(){
        return user_birthday;
    }
    
    public String getUserGioitinh(){
        return user_gioitinh;
    }
    
    public String getUserAddress(){
        return user_address;
    }
    
    public String getUserNote(){
        return user_note;
    }
    
    public void setUserID(int id){
        user_id = id;
    }
    
    public void setUserUsername(String username){
        user_username = username;
    }
    
    public void setUserPassword(String password){
        user_password = password;
    }
    
    public void setUserEmail(String email){
        user_email = email;
    }
    
    public void setUserBirthday(Date birthday){
        user_birthday = birthday;
    }
    
    public void setUserGioitinh(String gioitinh){
        user_gioitinh = gioitinh;
    }
    
    public void setUserAddress(String address){
        user_address = address;
    }
    
    public void setUserNote(String note){
        user_note = note;
    }
}
