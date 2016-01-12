/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ConnectController;
import controller.SongController;
import controller.TableControlSongClass;

/**
 *
 * @author khacc
 */
public class PanelSongOffline extends javax.swing.JPanel {
    
    private static ConnectController connectController = new ConnectController(); //create a new connect to database
    private static PanelSongOffline ins;
    public static PanelSongOffline getInstane(){
        return ins;
    }
    /**
     * Creates new form PanelSongOffline
     */
    public PanelSongOffline() {
        initComponents();
        addActionListener();
        addDatatoListSong();
        SongController songcontroller = new SongController();
        this.updatePageSong(1, songcontroller.getRowCount()/5+1);
        this.updateSongCount(songcontroller.getRowCount());
        ins = this;
    }
    
    private void addActionListener(){
        btn_First.addActionListener(new SongController());
        btn_Back.addActionListener(new SongController());
        btn_Next.addActionListener(new SongController());
        btn_Last.addActionListener(new SongController());
    }
    
    public void updatePageSong(int cur_page, int page){
        lbl_Page.setText(cur_page + " of " + page);
    }
    
    public void updateSongCount(int countSong){
        lbl_Num.setText("Số lượng song: " + countSong);
    }
    
    private void addDatatoListSong(){
        TableControlSongClass tableModelClass = new TableControlSongClass();
        tableModelClass.setModelTable(tbl_PerSong);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_title = new javax.swing.JLabel();
        btn_Up = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_PerSong = new javax.swing.JTable();
        lbl_Num = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_First = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();
        lbl_Page = new javax.swing.JLabel();
        btn_Next = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lbl_title.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_title.setText("Danh sách nhạc cá nhân");

        btn_Up.setText("Tải lên");
        btn_Up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpActionPerformed(evt);
            }
        });

        tbl_PerSong.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_PerSong.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_PerSong.setAutoscrolls(false);
        tbl_PerSong.setRowHeight(45);
        tbl_PerSong.setShowHorizontalLines(false);
        tbl_PerSong.setShowVerticalLines(false);
        tbl_PerSong.setTableHeader(null);
        jScrollPane1.setViewportView(tbl_PerSong);

        lbl_Num.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_Num.setText("Số lượng bài");

        btn_First.setText("First");
        btn_First.setName("First"); // NOI18N

        btn_Back.setText("Pre");
        btn_Back.setName("Pre"); // NOI18N

        lbl_Page.setText("1 of n");

        btn_Next.setText("Next");
        btn_Next.setName("Next"); // NOI18N

        btn_Last.setText("Last");
        btn_Last.setName("Last"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(btn_First)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Back)
                .addGap(29, 29, 29)
                .addComponent(lbl_Page)
                .addGap(26, 26, 26)
                .addComponent(btn_Next)
                .addGap(18, 18, 18)
                .addComponent(btn_Last)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Last)
                    .addComponent(btn_Next)
                    .addComponent(btn_Back)
                    .addComponent(btn_First)
                    .addComponent(lbl_Page))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(396, 396, 396)
                .addComponent(lbl_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Up)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Num)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_title)
                    .addComponent(btn_Up))
                .addGap(8, 8, 8)
                .addComponent(lbl_Num)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_UpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpActionPerformed
        // TODO add your handling code here:
        new Upload().setVisible(true);
    }//GEN-LAST:event_btn_UpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_First;
    private javax.swing.JButton btn_Last;
    private javax.swing.JButton btn_Next;
    private javax.swing.JButton btn_Up;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Num;
    private javax.swing.JLabel lbl_Page;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JTable tbl_PerSong;
    // End of variables declaration//GEN-END:variables
}
