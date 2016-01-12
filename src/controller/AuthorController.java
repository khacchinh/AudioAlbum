/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Author;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.AuthorModel;
import view.UploadSong;

/**
 *
 * @author VMQ
 */
public class AuthorController {
    Author author = new Author();
    AuthorModel authorModel = new AuthorModel();
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    
    
    private ArrayList<String> arr_author_id = new ArrayList<String>();
    private ArrayList<String> arr_author_name = new ArrayList<String>();
    private ArrayList<String> arr_author_birth = new ArrayList<String>();
    private int check;
    public int chosenid;
    private static int rowAt;
    
    public boolean InsertAuthor(Author author){
        return authorModel.insertDataForTable(author);
    }
    
    public ArrayList<String> getAllDataAuthor(){
        ResultSet resultSet = authorModel.getAllDataTable();
        try{
            arr_author_id.clear();
            arr_author_name.clear();
            arr_author_birth.clear();
            while(resultSet.next()){
                arr_author_id.add(resultSet.getString(Author.col_id));
                arr_author_name.add(resultSet.getString(Author.col_name));
                arr_author_birth.add(resultSet.getString(Author.col_birthday));
            }
            return arr_author_name;
        }
        catch (SQLException e){
            return null;
        }
    }
    
    public void resetAuthor(){
        SwingUtilities.invokeLater(new Runnable(){public void run(){
            defaultTableModel.removeRow(rowAt);
        }});
    }
    
    public void resetAuthorTable(){
        
        SwingUtilities.invokeLater(new Runnable(){@Override
        public void run(){
            while (defaultTableModel.getRowCount()  > 0)
                for(int i = 0 ; i < defaultTableModel.getRowCount();i++){       
                    defaultTableModel.removeRow(i);
            }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList =  getAllDataAuthor();
        
        for (int i=0;i<arrayList.size() ;i++){
            String author_arr= arrayList.get(i);
            defaultTableModel.addRow(new Object[] {author_arr,arr_author_birth.get(i)});
            
        }
        }});    
    }
       
    public boolean deleteDataFromTable(Author author){
        int index = arr_author_name.indexOf(author.getAuthorName());
        author.setAuthorID(Integer.parseInt(arr_author_id.get(index)));
        return authorModel.deleteDataFromTable(author);
    }

    
    public void setModelTable(JTable table){
        table.setModel(tableModel());
        
        table.setRowHeight(30);
        /*
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.addListSelectionListener(this);
        */
        //double click
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) {
            // your valueChanged overridden method 
                    UploadSong.getInstane().toTxtAuthor(row);
                    chosenid= Integer.parseInt(arr_author_id.get(row));
                }
             }
            
        });
    }
    
    private DefaultTableModel tableModel(){
        defaultTableModel = new DefaultTableModel();
        //defaultTableModel.addColumn("Mã ca sĩ");
        defaultTableModel.addColumn("Tên nhạc sĩ");
        defaultTableModel.addColumn("Ngày sinh");
       
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = getAllDataAuthor();
        
        for (int i=0;i<arrayList.size() ;i++){
            String author_arr= arrayList.get(i);
            defaultTableModel.addRow(new Object[] {author_arr,arr_author_birth.get(i)});
        }
        
        return defaultTableModel;
    }
}
