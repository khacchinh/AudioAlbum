/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PlaylistController;
import controller.SongController;
import controller.TableControlSongClass;
import controller.UserController;
import dto.Playlist;
import dto.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import static model.PlaylistModel.TABLE_NAME;

/**
 *
 * @author khacc
 */
public class SearchGeneral extends javax.swing.JFrame {
    public static String key_search = null;
    private ArrayList<JLabel> arrayListSong = new ArrayList<>();
    private ArrayList<JLabel> arrayListPlaylist = new ArrayList<>();
    private PlaylistController playlistController = new PlaylistController();
    private SongController songController = new SongController();
    private ArrayList<String> list_song = new ArrayList<>();
    private ArrayList<String> list_playlist = new ArrayList<>();

    /**
     * Creates new form SearchGeneral
     */
    public SearchGeneral() {
        initComponents();
        addComponents();
        addToComponent();
       
    }
    
    private void addComponents(){
        lbKetQua.setText("Kết quả tìm kiếm: " + key_search);
        arrayListSong.add(lbSong_1);
        arrayListSong.add(lbSong_2);
        arrayListSong.add(lbSong_3);
        arrayListSong.add(lbSong_4);
        arrayListSong.add(lbSong_5);
        arrayListSong.add(lbSong_6);
        arrayListSong.add(lbSong_7);
        arrayListSong.add(lbSong_8);
        arrayListSong.add(lbSong_9);
        arrayListSong.add(lbSong_10);
        arrayListSong.add(lbSong_11);
        arrayListSong.add(lbSong_12);
        arrayListSong.add(lbSong_13);
        arrayListSong.add(lbSong_14);
        arrayListPlaylist.add(lnPlaylist_1);
        arrayListPlaylist.add(lnPlaylist_2);
        arrayListPlaylist.add(lnPlaylist_3);
        arrayListPlaylist.add(lnPlaylist_4);
        arrayListPlaylist.add(lnPlaylist_5);
        arrayListPlaylist.add(lnPlaylist_6);
        arrayListPlaylist.add(lnPlaylist_7);
        arrayListPlaylist.add(lnPlaylist_8);
        arrayListPlaylist.add(lnPlaylist_9);
        arrayListPlaylist.add(lnPlaylist_10);
        arrayListPlaylist.add(lnPlaylist_11);
        arrayListPlaylist.add(lnPlaylist_12);
        arrayListPlaylist.add(lnPlaylist_13);
        arrayListPlaylist.add(lnPlaylist_14);
        for (int i = 0; i< arrayListSong.size();i++){
            arrayListSong.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
            arrayListSong.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
            arrayListSong.get(i).setText("");
            arrayListSong.get(i).setName(Integer.toString(i));
            arrayListSong.get(i).setIcon(new ImageIcon(getClass().getResource("/Resoures/search_1.png")));
            arrayListSong.get(i).addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e){
                    System.out.println("play song");
                    JLabel lb = (JLabel) e.getSource();
                    if (lb.getText().equals("")) return;
                    int k = Integer.parseInt(lb.getName());
                    SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                            try {
                                PlaySong.ulrsong = list_song.get(k).split(":")[2];
                                PlaySong.cmt_song.setSongID(Integer.parseInt(list_song.get(k).split(":")[0]));
                                new PlaySong();
                                songController.updateLikeSong(PlaySong.cmt_song.getSongID());
                            } catch (IOException ex) {
                                Logger.getLogger(TableControlSongClass.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        }
                    });
                }
            });
            
            arrayListPlaylist.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
            arrayListPlaylist.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
            arrayListPlaylist.get(i).setText("");
            arrayListPlaylist.get(i).setName(Integer.toString(i));
            arrayListPlaylist.get(i).setIcon(new ImageIcon(getClass().getResource("/Resoures/search_1.png")));
            arrayListPlaylist.get(i).addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e){
                    JLabel lb = (JLabel) e.getSource();
                    if (lb.getText().equals("")) return;
                    int k = Integer.parseInt(lb.getName());
                    
                    Playlist pl = new Playlist();
                    pl.setPlaylistName(list_playlist.get(k).split(":")[1]);
                    playlistController.upCountDisplay(pl.getPlaylistID());
                    FrameMain.getInstane().addFramePlaySongInPlaylist(playlistController.getIDByPlaylistName(pl));
                }
            });
            
             
        }
    }
    
    private void addToComponent(){
        int i=0;
        list_song = songController.getSearchDataDefenceOnKeyValue(key_search);
        for (i =0;i<list_song.size();i++){
            arrayListSong.get(i).setText(list_song.get(i).split(":")[1]);
            arrayListSong.get(i).setIcon(new ImageIcon(getClass().getResource("/Resoures/search_2.png")));
        }
        for (int j =i; j<arrayListSong.size();j++){
            arrayListSong.get(j).setText("");
            arrayListSong.get(j).setIcon(new ImageIcon(getClass().getResource("/Resoures/search_1.png")));
        }
        
        list_playlist = playlistController.getSearchDataDefenceOnKeyValue(key_search);
        for (i =0;i<list_playlist.size();i++){
            arrayListPlaylist.get(i).setText(list_playlist.get(i).split(":")[1]);
            arrayListPlaylist.get(i).setIcon(new ImageIcon(getClass().getResource("/Resoures/search_2.png")));
        }
        for (int j =i; j<arrayListPlaylist.size();j++){
            arrayListPlaylist.get(j).setText("");
            arrayListPlaylist.get(j).setIcon(new ImageIcon(getClass().getResource("/Resoures/search_1.png")));
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbKetQua = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbSong_8 = new javax.swing.JLabel();
        lbSong_1 = new javax.swing.JLabel();
        lbSong_2 = new javax.swing.JLabel();
        lbSong_9 = new javax.swing.JLabel();
        lbSong_3 = new javax.swing.JLabel();
        lbSong_10 = new javax.swing.JLabel();
        lbSong_4 = new javax.swing.JLabel();
        lbSong_11 = new javax.swing.JLabel();
        lbSong_12 = new javax.swing.JLabel();
        lbSong_5 = new javax.swing.JLabel();
        lbSong_6 = new javax.swing.JLabel();
        lbSong_13 = new javax.swing.JLabel();
        lbSong_7 = new javax.swing.JLabel();
        lbSong_14 = new javax.swing.JLabel();
        lnPlaylist_14 = new javax.swing.JLabel();
        lnPlaylist_13 = new javax.swing.JLabel();
        lnPlaylist_6 = new javax.swing.JLabel();
        lnPlaylist_7 = new javax.swing.JLabel();
        lnPlaylist_5 = new javax.swing.JLabel();
        lnPlaylist_12 = new javax.swing.JLabel();
        lnPlaylist_11 = new javax.swing.JLabel();
        lnPlaylist_4 = new javax.swing.JLabel();
        lnPlaylist_3 = new javax.swing.JLabel();
        lnPlaylist_10 = new javax.swing.JLabel();
        lnPlaylist_9 = new javax.swing.JLabel();
        lnPlaylist_8 = new javax.swing.JLabel();
        lnPlaylist_1 = new javax.swing.JLabel();
        lnPlaylist_2 = new javax.swing.JLabel();

        lbKetQua.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbKetQua.setText("Kết quả tìm kiếm: ");

        jLabel2.setText("- Bài hát");

        jLabel3.setText("-Playlist");

        lbSong_8.setText("jLabel4");

        lbSong_1.setText("jLabel4");
        lbSong_1.setName("1"); // NOI18N

        lbSong_2.setText("jLabel4");
        lbSong_2.setName("2"); // NOI18N

        lbSong_9.setText("jLabel4");

        lbSong_3.setText("jLabel4");
        lbSong_3.setName(""); // NOI18N

        lbSong_10.setText("jLabel4");

        lbSong_4.setText("jLabel4");

        lbSong_11.setText("jLabel4");

        lbSong_12.setText("jLabel4");

        lbSong_5.setText("jLabel4");

        lbSong_6.setText("jLabel4");

        lbSong_13.setText("jLabel4");

        lbSong_7.setText("jLabel4");

        lbSong_14.setText("jLabel4");

        lnPlaylist_14.setText("jLabel4");

        lnPlaylist_13.setText("jLabel4");

        lnPlaylist_6.setText("jLabel4");

        lnPlaylist_7.setText("jLabel4");

        lnPlaylist_5.setText("jLabel4");

        lnPlaylist_12.setText("jLabel4");

        lnPlaylist_11.setText("jLabel4");

        lnPlaylist_4.setText("jLabel4");

        lnPlaylist_3.setText("jLabel4");

        lnPlaylist_10.setText("jLabel4");

        lnPlaylist_9.setText("jLabel4");

        lnPlaylist_8.setText("jLabel4");

        lnPlaylist_1.setText("jLabel4");

        lnPlaylist_2.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSong_1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSong_8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lnPlaylist_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnPlaylist_8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSong_2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSong_9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnPlaylist_2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnPlaylist_9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lnPlaylist_14, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSong_3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbSong_10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbSong_4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbSong_11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSong_6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbSong_13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lnPlaylist_6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lnPlaylist_13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lnPlaylist_10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lnPlaylist_3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lnPlaylist_4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lnPlaylist_11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSong_5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbSong_12, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lnPlaylist_5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lnPlaylist_12, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSong_7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSong_14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnPlaylist_7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(61, 61, 61))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lbKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lbKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSong_2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSong_1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSong_8, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSong_9, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSong_10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSong_11, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSong_12, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSong_13, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSong_14, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbSong_3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbSong_5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbSong_6, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbSong_7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbSong_4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lnPlaylist_13, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lnPlaylist_14, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lnPlaylist_12, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lnPlaylist_11, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lnPlaylist_10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lnPlaylist_9, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lnPlaylist_8, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lnPlaylist_6, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnPlaylist_5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnPlaylist_4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnPlaylist_3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnPlaylist_2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnPlaylist_1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnPlaylist_7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(187, 187, 187)))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchGeneral().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbKetQua;
    private javax.swing.JLabel lbSong_1;
    private javax.swing.JLabel lbSong_10;
    private javax.swing.JLabel lbSong_11;
    private javax.swing.JLabel lbSong_12;
    private javax.swing.JLabel lbSong_13;
    private javax.swing.JLabel lbSong_14;
    private javax.swing.JLabel lbSong_2;
    private javax.swing.JLabel lbSong_3;
    private javax.swing.JLabel lbSong_4;
    private javax.swing.JLabel lbSong_5;
    private javax.swing.JLabel lbSong_6;
    private javax.swing.JLabel lbSong_7;
    private javax.swing.JLabel lbSong_8;
    private javax.swing.JLabel lbSong_9;
    private javax.swing.JLabel lnPlaylist_1;
    private javax.swing.JLabel lnPlaylist_10;
    private javax.swing.JLabel lnPlaylist_11;
    private javax.swing.JLabel lnPlaylist_12;
    private javax.swing.JLabel lnPlaylist_13;
    private javax.swing.JLabel lnPlaylist_14;
    private javax.swing.JLabel lnPlaylist_2;
    private javax.swing.JLabel lnPlaylist_3;
    private javax.swing.JLabel lnPlaylist_4;
    private javax.swing.JLabel lnPlaylist_5;
    private javax.swing.JLabel lnPlaylist_6;
    private javax.swing.JLabel lnPlaylist_7;
    private javax.swing.JLabel lnPlaylist_8;
    private javax.swing.JLabel lnPlaylist_9;
    // End of variables declaration//GEN-END:variables
}
