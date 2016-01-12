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
 * @author VMQ
 */
public class SignUpController {
    User user = new User();
    UserModel usermodel=new UserModel();
    
    public boolean insertUser (User user)
    {
        return usermodel.insertDataForTable(user);
    }
    
    public boolean checkUserName(User user)
    {
        return usermodel.checkSignUpUserName(user);
    }
    
    
}
