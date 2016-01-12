/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
/**
 *
 * @author khacc
 */
public class ConnectController {
    public ConnectController(){
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.getConnection();
    }
}
