/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dto.Singer;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.SingerModel;
import view.UploadSong;

/**
 *
 * @author VMQ
 */
public class SingerController {
    Singer singer = new Singer();
    SingerModel singerModel = new SingerModel();
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    
    private ArrayList<String> arr_singer_id = new ArrayList<String>();
    private ArrayList<String> arr_singer_name = new ArrayList<String>();
    private ArrayList<String> arr_singer_birth = new ArrayList<String>();
    private ArrayList<String> arr_singer_note = new ArrayList<String>();
    public int chosenid;
    private int check;
    private static int rowAt;
    
    public boolean InsertSinger(Singer singer){
        return singerModel.insertDataForTable(singer);
    }
    
    public ArrayList<String> getAllDataSinger(){
        ResultSet resultSet = singerModel.getAllDataTable();
        arr_singer_id.clear();
        arr_singer_name.clear();
        arr_singer_birth.clear();
        arr_singer_note.clear();
        try{
            
            while(resultSet.next()){
                
                arr_singer_id.add(resultSet.getString(Singer.col_id));
                arr_singer_name.add(resultSet.getString(Singer.col_name));
                arr_singer_birth.add(resultSet.getString(Singer.col_birthday));
                arr_singer_note.add(resultSet.getString(Singer.col_note));
            }
            return arr_singer_name;
        }
        catch (SQLException e){
            return null;
        }
    }
    
    public void resetSinger(){
        SwingUtilities.invokeLater(new Runnable(){public void run(){
            defaultTableModel.removeRow(rowAt);
        }});
    }
    
    public void resetSingerTable(){
        
        SwingUtilities.invokeLater(new Runnable(){@Override
        public void run(){
            while (defaultTableModel.getRowCount()  > 0)
                for(int i = 0 ; i < defaultTableModel.getRowCount();i++){
                    defaultTableModel.removeRow(i);
                }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList =  getAllDataSinger();
        for (int i=0;i<arrayList.size() ;i++){
            String singer_arr= arrayList.get(i);
            defaultTableModel.addRow(new Object[] {singer_arr,arr_singer_birth.get(i), arr_singer_note.get(i)});
            
        }
        }}); 

    }
       
    public boolean deleteDataFromTable(Singer singer){
        int index = arr_singer_name.indexOf(singer.getSingerName());
        singer.setSingerID(Integer.parseInt(arr_singer_id.get(index)));
        return singerModel.deleteDataFromTable(singer);
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
                    UploadSong.getInstane().toTxtSinger(row);
                    chosenid= Integer.parseInt(arr_singer_id.get(row));
                }
             }
            
        });
    }
    
    private DefaultTableModel tableModel(){
        defaultTableModel = new DefaultTableModel();
        //defaultTableModel.addColumn("Mã ca sĩ");
        defaultTableModel.addColumn("Tên ca sĩ");
        defaultTableModel.addColumn("Ngày sinh");
        defaultTableModel.addColumn("Ghi chú");
       
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = getAllDataSinger();
        
        for (int i=0;i<arrayList.size() ;i++){
            String singer_arr= arrayList.get(i);
            defaultTableModel.addRow(new Object[] {singer_arr,arr_singer_birth.get(i), arr_singer_note.get(i)});
            
        }
        
        return defaultTableModel;
    }
    
    
}
