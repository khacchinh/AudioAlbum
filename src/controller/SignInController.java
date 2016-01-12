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
public class SignInController {
    UserModel usermodel = new UserModel();
    User user;
    
    public boolean checkSignIn(User user)
    {
        return usermodel.checkSignIn(user);
    }
    
}
