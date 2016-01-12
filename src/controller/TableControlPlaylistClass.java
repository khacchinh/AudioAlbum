/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import view.*;
import dto.*;
import java.awt.Color;
import java.util.Vector;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import static view.PlaySongInList.cmt_playlist;
/**
 *
 * @author khacc
 */
public class TableControlPlaylistClass {
    private static TableControlPlaylistClass ins;
    private PlaylistController playlistController = new PlaylistController();
    private static DefaultTableModel defaultTableModel;
    private Playlist playlist;
    private String playlist_name;
    private static int rowAt, response;
    private int check;
    public static TableControlPlaylistClass getInstance(){
        return ins;
    }
    public TableControlPlaylistClass(){
        ins = this;
    }
    
    public void setModelTable(JTable table){
        table.setModel(tableModel());
        table.getColumn("NamePlaylist").setCellEditor(new Editor_name(new JCheckBox()));
        
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
                        Playlist pl = new Playlist();
                        pl.setPlaylistName(table.getModel().getValueAt(row, 0).toString());
                        FrameMain.getInstane().addFramePlaySongInPlaylist(playlistController.getIDByPlaylistName(pl));
                    }
                }
             }
            
        });
    }
    
    private DefaultTableModel tableModel(){
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("NamePlaylist");
        defaultTableModel.addColumn("Button_Play");
        defaultTableModel.addColumn("Button_Delete");
        defaultTableModel.addColumn("Like");
       // defaultTableModel.addColumn("Point");
       
        ArrayList<String> arrayList = null;
        arrayList = playlistController.getAllDataPlaylistForPage(0, 10, UserController.userIns);
        for (int i=0;i<arrayList.size() ;i++){
            String playlist_arr[] = arrayList.get(i).split(":");
            defaultTableModel.addRow(new Object[] {playlist_arr[0],"Play","Delete",playlist_arr[1]});
            
        }
        
        return defaultTableModel;
    }
    
    public void resetPlaylist(){
        SwingUtilities.invokeLater(new Runnable(){public void run(){
            defaultTableModel.removeRow(rowAt);
        }});
        
        
        
    }
    
    public void resetPlaylistForPage(int start, int offset){
        
        SwingUtilities.invokeLater(new Runnable(){public void run(){
            while (defaultTableModel.getRowCount()  > 0)
                for(int i = 0 ; i < defaultTableModel.getRowCount();i++){
                    defaultTableModel.removeRow(i);
            }
            ArrayList<String> arrayList = null;
            arrayList = playlistController.getAllDataPlaylistForPage(start, offset, UserController.userIns);
            for (int i=0;i<arrayList.size() ;i++){
                String playlist_arr[] = arrayList.get(i).split(":");
                defaultTableModel.addRow(new Object[] {playlist_arr[0],"Play","Delete",playlist_arr[1]}); 
            }
        }});    
    }
    
    private void deletePlaylist(){
        
        playlist = new  Playlist();
        playlist.setPlaylistName(playlist_name);
        if (playlistController.deleteDateFromTable(playlist))
            JOptionPane.showMessageDialog(null, "Deleted Success");
        else  JOptionPane.showMessageDialog(null, "Faild");
        resetPlaylist();
        
    }
    
    private void playPlaylistOpen(){
        Playlist pl = new Playlist();
        pl.setPlaylistName(playlist_name.split(":")[0]);
        playlistController.upCountDisplay(pl.getPlaylistID());
        FrameMain.getInstane().addFramePlaySongInPlaylist(playlistController.getIDByPlaylistName(pl));
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
      playlist_name = table.getValueAt(row, 0).toString() +":"+ table.getValueAt(row, 3);
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
                deletePlaylist();
            }
        else if (label.equals("Play"))
            playPlaylistOpen();
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
