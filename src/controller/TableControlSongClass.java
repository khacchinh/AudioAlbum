/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Song;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import view.PanelSongOffline;
import view.FrameMain;
import view.PlaySong;

/**
 *
 * @author VMQ
 */
public class TableControlSongClass {
    private static TableControlSongClass ins;
    private SongController songController = new SongController();
    private static DefaultTableModel defaultTableModel;
    private Song song;
    private String song_name;
    private static int rowAt, response;
    private int check;
    public static TableControlSongClass getInstance(){
        return ins;
    }
    public TableControlSongClass(){
        ins = this;
    }
    
    public void setModelTable(JTable table){
        table.setModel(tableModel());
        table.getColumn("NameSong").setCellEditor(new Editor_name(new JCheckBox()));
        
        table.getColumn("Button_Play").setCellRenderer(new ButtonRenderer());
        table.getColumn("Button_Play").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        table.getColumn("Button_Delete").setCellRenderer(new ButtonRenderer());
        table.getColumn("Button_Delete").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        table.getColumnModel().getColumn(0).setPreferredWidth(800);
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
                if (me.getClickCount() == 2) {
            // your valueChanged overridden method 
                    if (check == 0){
                       //FrameMain.getInstane().addFrameDetailSong(table.getModel().getValueAt(row, 0).toString());
                    }
                }
             }
            
        });
    }
    
    private DefaultTableModel tableModel(){
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("NameSong");
        defaultTableModel.addColumn("Button_Play");
        defaultTableModel.addColumn("Button_Delete");
        defaultTableModel.addColumn("Like");
       
        ArrayList<String> arrayList = null;
        arrayList = songController.getAllDataSongForPage(0, 5, UserController.userIns);
        for (int i=0;i<arrayList.size() ;i++){
            String song_arr[] = arrayList.get(i).split(":");
            defaultTableModel.addRow(new Object[] {song_arr[0],"Play","Delete",song_arr[1]});
            
        }
        
        return defaultTableModel;
    }
    
    public void resetSong(){
        SwingUtilities.invokeLater(new Runnable(){public void run(){
            defaultTableModel.removeRow(rowAt);
        }});
    }
    
    public void resetSongForPage(int start, int offset){
        
        SwingUtilities.invokeLater(new Runnable(){public void run(){
            while (defaultTableModel.getRowCount()  > 0)
                for(int i = 0 ; i < defaultTableModel.getRowCount();i++){
                    defaultTableModel.removeRow(i);
            }
            ArrayList<String> arrayList = null;
            arrayList = songController.getAllDataSongForPage(start, offset, UserController.userIns);
            for (int i=0;i<arrayList.size() ;i++){
                String song_arr[] = arrayList.get(i).split(":");
                defaultTableModel.addRow(new Object[] {song_arr[0],"Play","Delete",song_arr[1]}); 
            }
        }});    
    }
    
    private void deleteSong(){
        song = new  Song();
        song.setSongName(song_name);
        if (songController.deleteDataFromTable(song))
            JOptionPane.showMessageDialog(null, "Deleted Success");
        else  JOptionPane.showMessageDialog(null, "Faild");
        resetSong();
    }
    
    private void playSongOpen(){
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlaySong.ulrsong = songController.getSongByNameandUserID(song_name.split(":")[0], UserController.userIns).split(":")[0];
                    PlaySong.cmt_song.setSongID(Integer.parseInt(songController.getSongByNameandUserID(song_name.split(":")[0], UserController.userIns).split(":")[1]));
                    new PlaySong().jl_song_name_playing.setText(song_name.split(":")[0]);
                    songController.updateLikeSong(PlaySong.cmt_song.getSongID());
                } catch (IOException ex) {
                    Logger.getLogger(TableControlSongClass.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
       
    }
    
    public static class Editor_name extends DefaultCellEditor {
        public Editor_name(JCheckBox checkBox) {
         super(checkBox);
        }
        public boolean isCellEditable(EventObject anEvent) {
          return false;
        }
  }
    
    class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
      setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
      if (isSelected) {
        setForeground(table.getSelectionForeground());
        setBackground(table.getSelectionBackground());
      } else {
        setForeground(table.getForeground());
        setBackground(UIManager.getColor("Button.background"));
      }
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }
/**
 * @version 1.0 11/09/98
 */
    class ButtonEditor extends DefaultCellEditor {
      protected JButton button;

      private String label;

      private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
      super(checkBox);
      button = new JButton();
      button.setOpaque(true);
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fireEditingStopped();
        }
      });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
      if (isSelected) {
        button.setForeground(table.getSelectionForeground());
        button.setBackground(table.getSelectionBackground());
      } else {
        button.setForeground(table.getForeground());
        button.setBackground(table.getBackground());
      }
      rowAt = row;
        System.out.println(rowAt);
      song_name = table.getValueAt(row, 0).toString() +":"+ table.getValueAt(row, 3);
      label = (value == null ) ? "" : value.toString();
      button.setText(label);
      isPushed = true;
      return button;
    }

    public Object getCellEditorValue() {
      if (isPushed) {
        // do something here @khacchinh
        check = 1;
        System.out.println(label);
        if (label.equals("Delete")){
            response = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION)
                deleteSong();
            }
        else if (label.equals("Play"))
            playSongOpen();
        check = 0;
      }
      isPushed = false;
      return new String(label);
    }

    public boolean stopCellEditing() {
      isPushed = false;
      return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
      super.fireEditingStopped();
    }
  } 
}
