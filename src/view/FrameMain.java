/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Button;
import javax.swing.DefaultListModel;
import controller.*;
import dto.Comment_Playlist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author khacc
 */
public class FrameMain extends javax.swing.JFrame {
    private PanelPlaylistOnline pPlaylistOnline = new PanelPlaylistOnline();
    private PanelSongOffline pSongOffline = new PanelSongOffline();
    private PanelMain pMain = new PanelMain();
    private PlaylistController playlistController = new PlaylistController();
    private static FrameMain ins;
    public static FrameMain getInstane(){
        return ins;
    }
    /**
     * Creates new form NewJFrame
     */
    public FrameMain() {
        initComponents();
        this.setResizable(false);
        addPlaylistComponent();
        addSongComponent();
        addMainComponent();
        ins = this;
        tpMain.addChangeListener(new TabbPanelAction());
      //  connectController.connectToDatabase();
    }
    
    private void addMainComponent(){
        pipi.setVisible(false);
        pMain.setLocation(pipi.getLocation());
        pMain.setSize(pipi.getSize());
        Panel1.add(pMain);
    }
    
    private void addSongComponent(){
        pListSong.setVisible(false);
        pSongOffline.setLocation(pListSong.getLocation());
        pSongOffline.setSize(pListSong.getSize());
        Panel2.add(pSongOffline);
    }
    
    private void addPlaylistComponent(){
        pPlaylistOnline.setLocation(pPlaylistOwnUser.getLocation());
        pPlaylistOnline.setSize(pPlaylistOwnUser.getSize());
        Panel3.add(pPlaylistOnline);
        addActionListener();
        addDatatoListPlaylist();
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeText();
            }
            public void changeText(){
                System.out.println("change");
                DefaultTableModel defaultTableModel = (DefaultTableModel) tblPlaylist.getModel();
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        while (defaultTableModel.getRowCount()  > 0)
                        for(int i = 0 ; i < defaultTableModel.getRowCount();i++){
                            defaultTableModel.removeRow(i);
                        }
                        ArrayList<String> arrayList = null;
                        arrayList = playlistController.getAllDataPlaylistForPageSearchOwn(0, 5, UserController.userIns, txtSearch.getText());
                        for (int i=0;i<arrayList.size() ;i++){
                            String playlist_arr[] = arrayList.get(i).split(":");
                            defaultTableModel.addRow(new Object[] {playlist_arr[0],"Play","Delete",playlist_arr[1], playlist_arr[1]});
                        }
                    }
                });
            }
        });
    }
    
    private void addActionListener(){
        btnPlaylistFirst.addActionListener(new PlaylistController());
        btnPlaylistPre .addActionListener(new PlaylistController());
        btnPlaylistNext .addActionListener(new PlaylistController());
        btnPlaylistLast .addActionListener(new PlaylistController());
    }
    
    public void updatePagePlaylist(int cur_page, int page){
        lbPagePlaylist.setText(cur_page + " of " + page);
    }
    
    public void updatePlaylistCount(int countPlaylist){
        lbPlaylistCount.setText("Số lượng playlist: " + countPlaylist);
    }
    
    private void addDatatoListPlaylist(){
        TableControlPlaylistClass tableModelClass = new TableControlPlaylistClass();
        tableModelClass.setModelTable(tblPlaylist);
    }
    
    public void addFramePlaySongInPlaylist(int playlist_id) {
        
        PlaySongInList playSongInList;
        try {
            Comment_Playlist cp = new Comment_Playlist();
            cp.setPlaylistID(playlist_id);
            System.out.println("playlist id : " + playlist_id);
            PlaySongInList.getInstane().cmt_playlist = cp;
            playSongInList = new PlaySongInList();
        } catch (IOException ex) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean getJPanelPlaylistOwnVisible(){
        return pPlaylistOwnUser.isVisible();
    }
    
    public boolean getJPanelPlaylistOnlineVisible(){
        return pPlaylistOnline.isVisible();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpMain = new javax.swing.JTabbedPane();
        Panel1 = new javax.swing.JPanel();
        pipi = new javax.swing.JPanel();
        Panel2 = new javax.swing.JPanel();
        pListSong = new javax.swing.JPanel();
        Panel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pPlaylistOwnUser = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbPagePlaylist = new javax.swing.JLabel();
        btnPlaylistNext = new javax.swing.JButton();
        btnPlaylistLast = new javax.swing.JButton();
        btnPlaylistPre = new javax.swing.JButton();
        btnPlaylistFirst = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlaylist = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnCreatePlaylist = new javax.swing.JButton();
        lbPlaylistCount = new javax.swing.JLabel();
        btnPlaylistOwnPlaylist = new javax.swing.JButton();
        btnPlaylistOnline = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        tpMain.setBackground(new java.awt.Color(255, 255, 255));

        Panel1.setBackground(new java.awt.Color(255, 255, 255));

        pipi.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pipiLayout = new javax.swing.GroupLayout(pipi);
        pipi.setLayout(pipiLayout);
        pipiLayout.setHorizontalGroup(
            pipiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1258, Short.MAX_VALUE)
        );
        pipiLayout.setVerticalGroup(
            pipiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pipi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pipi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpMain.addTab("Main", Panel1);

        Panel2.setBackground(new java.awt.Color(255, 255, 255));

        pListSong.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pListSongLayout = new javax.swing.GroupLayout(pListSong);
        pListSong.setLayout(pListSongLayout);
        pListSongLayout.setHorizontalGroup(
            pListSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
        );
        pListSongLayout.setVerticalGroup(
            pListSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel2Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(pListSong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(pListSong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        tpMain.addTab("Nhạc cá nhân", Panel2);

        Panel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("DANH SÁCH PLAYLIST ");

        pPlaylistOwnUser.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lbPagePlaylist.setText("1 of n");

        btnPlaylistNext.setText("Next");
        btnPlaylistNext.setName("ButtonNext"); // NOI18N

        btnPlaylistLast.setText("Last");
        btnPlaylistLast.setName("ButtonLast"); // NOI18N

        btnPlaylistPre.setText("Pervious");
        btnPlaylistPre.setName("ButtonPre"); // NOI18N

        btnPlaylistFirst.setText("First");
        btnPlaylistFirst.setName("ButtonFirst"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnPlaylistFirst)
                .addGap(18, 18, 18)
                .addComponent(btnPlaylistPre)
                .addGap(15, 15, 15)
                .addComponent(lbPagePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPlaylistNext)
                .addGap(33, 33, 33)
                .addComponent(btnPlaylistLast)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPagePlaylist)
                    .addComponent(btnPlaylistNext)
                    .addComponent(btnPlaylistLast)
                    .addComponent(btnPlaylistPre)
                    .addComponent(btnPlaylistFirst))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblPlaylist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPlaylist.setAutoscrolls(false);
        tblPlaylist.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblPlaylist.setFocusable(false);
        tblPlaylist.setRowHeight(50);
        tblPlaylist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPlaylist.setShowHorizontalLines(false);
        tblPlaylist.setShowVerticalLines(false);
        tblPlaylist.setTableHeader(null);
        tblPlaylist.setUpdateSelectionOnSort(false);
        tblPlaylist.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(tblPlaylist);

        txtSearch.setText("Search");

        btnCreatePlaylist.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCreatePlaylist.setForeground(new java.awt.Color(0, 0, 51));
        btnCreatePlaylist.setText("Tạo playlist mới");
        btnCreatePlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePlaylistActionPerformed(evt);
            }
        });

        lbPlaylistCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbPlaylistCount.setText("Số lượng playlist: 0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lbPlaylistCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnCreatePlaylist)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreatePlaylist)
                    .addComponent(lbPlaylistCount)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout pPlaylistOwnUserLayout = new javax.swing.GroupLayout(pPlaylistOwnUser);
        pPlaylistOwnUser.setLayout(pPlaylistOwnUserLayout);
        pPlaylistOwnUserLayout.setHorizontalGroup(
            pPlaylistOwnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPlaylistOwnUserLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        pPlaylistOwnUserLayout.setVerticalGroup(
            pPlaylistOwnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPlaylistOwnUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnPlaylistOwnPlaylist.setText("Cá nhân");
        btnPlaylistOwnPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaylistOwnPlaylistActionPerformed(evt);
            }
        });

        btnPlaylistOnline.setText("Online");
        btnPlaylistOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaylistOnlineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel3Layout = new javax.swing.GroupLayout(Panel3);
        Panel3.setLayout(Panel3Layout);
        Panel3Layout.setHorizontalGroup(
            Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel3Layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addGroup(Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(201, 201, 201)
                        .addComponent(btnPlaylistOwnPlaylist)
                        .addGap(63, 63, 63)
                        .addComponent(btnPlaylistOnline)
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel3Layout.createSequentialGroup()
                        .addComponent(pPlaylistOwnUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
        );
        Panel3Layout.setVerticalGroup(
            Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel3Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnPlaylistOwnPlaylist)
                    .addComponent(btnPlaylistOnline))
                .addGap(29, 29, 29)
                .addComponent(pPlaylistOwnUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        tpMain.addTab("Playlist", Panel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreatePlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePlaylistActionPerformed
        // TODO add your handling code here:
        FrameCreateNewPlaylist frameCreateNewPlaylist = new FrameCreateNewPlaylist();
        frameCreateNewPlaylist.setVisible(true);
    }//GEN-LAST:event_btnCreatePlaylistActionPerformed

    private void btnPlaylistOwnPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaylistOwnPlaylistActionPerformed
        // TODO add your handling code here:
        pPlaylistOnline.setVisible(false);
        pPlaylistOwnUser.setVisible(true);
    }//GEN-LAST:event_btnPlaylistOwnPlaylistActionPerformed

    private void btnPlaylistOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaylistOnlineActionPerformed
        // TODO add your handling code here:
        pPlaylistOwnUser.setVisible(false);
        pPlaylistOnline.setVisible(true);
    }//GEN-LAST:event_btnPlaylistOnlineActionPerformed

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
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel2;
    private javax.swing.JPanel Panel3;
    private javax.swing.JButton btnCreatePlaylist;
    private javax.swing.JButton btnPlaylistFirst;
    private javax.swing.JButton btnPlaylistLast;
    private javax.swing.JButton btnPlaylistNext;
    private javax.swing.JButton btnPlaylistOnline;
    private javax.swing.JButton btnPlaylistOwnPlaylist;
    private javax.swing.JButton btnPlaylistPre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbPagePlaylist;
    private javax.swing.JLabel lbPlaylistCount;
    private javax.swing.JPanel pListSong;
    private javax.swing.JPanel pPlaylistOwnUser;
    private javax.swing.JPanel pipi;
    private javax.swing.JTable tblPlaylist;
    private javax.swing.JTabbedPane tpMain;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
