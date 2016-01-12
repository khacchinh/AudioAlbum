/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Genres;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import model.*;
import view.UploadSong;

/**
 *
 * @author khacc
 */
public class GenresController {
    private GenresModel genresModel = new GenresModel();
    
    DefaultComboBoxModel defaultComboBoxModel= new DefaultComboBoxModel();
    private ArrayList<String> arr_genres_id = new ArrayList<String>();
    private ArrayList<String> arr_genres_name = new ArrayList<String>();
    public GenresController(){
        
    }
    public ArrayList<String> getGenres(){
           ResultSet resultSet = genresModel.getGenres();
           ArrayList<String> arr_gesres = new ArrayList<>();
           try{
                while(resultSet.next()){
                    arr_gesres.add(resultSet.getString(Genres.col_name));
                }
                return arr_gesres;
           }
           catch (SQLException e){
               return null;
           }
       }
    
    public ArrayList<String> getAllDataGenres(){
           ResultSet resultSet = genresModel.getAllDataTable();
           
           try{
                while(resultSet.next()){
                    arr_genres_id.add(resultSet.getString(Genres.col_id));
                    arr_genres_name.add(resultSet.getString(Genres.col_name));
                }
                return arr_genres_name;
           }
           catch (SQLException e){
               return null;
           }
       }
    
     public void setModelComboBox(JComboBox jcbb){
        jcbb.setModel(ComboBoxModel());
        /*
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.addListSelectionListener(this);
        */
        //double click
        jcbb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if(ItemEvent.SELECTED==state) {
                    int row = arr_genres_name.indexOf(itemEvent.getItem());
                    UploadSong.getInstane().setGenresID(Integer.parseInt(arr_genres_id.get(row)));
                }
            }
          });
    }
     
      private DefaultComboBoxModel ComboBoxModel(){
        
        ArrayList<String> arraylist = new ArrayList<>();
        arraylist = getAllDataGenres();
        System.out.println(arraylist.size());
        for(int i =0; i< arraylist.size();i++){
            defaultComboBoxModel.addElement(arraylist.get(i));
        }
            return defaultComboBoxModel;
    }
}
