/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.User;
import model.UserModel;

/**
 *
 * @author khacc
 */
public class UserController {
    public static User userIns = new User();
    private static UserController ins;
    
    private UserModel userModel = new UserModel();
    public static UserController getInstance(){
        return ins;
    }
    public UserController(){
        ins = this;
    }
    
    public String getNameByID(User user){
        return userModel.getNameByID(user);
    }
    
    public int getIDByName(User user){
        return userModel.getIDByName(user);
    }
}
