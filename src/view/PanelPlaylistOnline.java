/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import controller.*;
import dto.Playlist;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author khacc
 */
public class PanelPlaylistOnline extends javax.swing.JPanel {
    private static PanelPlaylistOnline ins;
    private static ArrayList<JLabel> arrayList = new ArrayList<>();
    private PlaylistController playlistController = new PlaylistController();
    private GenresController genresController = new GenresController();
    public static PanelPlaylistOnline getInstant(){
        return ins;
    }
    /**
     * Creates new form PanelPlaylistOnline
     */
    public PanelPlaylistOnline() {
        ins = this;
        initComponents();
        setVisible(false);
        setDatatoCombobox();
        addLabelImnagetoList();
        setDatatoLabelImage();
        addButtonActionListener();
    }
    
    
    private void addLabelImnagetoList(){
        arrayList.add(lbPlaylist_1);
        arrayList.add(lbPlaylist_2);
        arrayList.add(lbPlaylist_3);
        arrayList.add(lbPlaylist_4);
        arrayList.add(lbPlaylist_5);
        arrayList.add(lbPlaylist_6);
        arrayList.add(lbPlaylist_7);
        arrayList.add(lbPlaylist_8);
        arrayList.add(lbPlaylist_9);
        arrayList.add(lbPlaylist_10);
        for (int i =0 ; i <arrayList.size() ;i++){
            arrayList.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
            arrayList.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
            arrayList.get(i).setText("");
            arrayList.get(i).addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e)   
                {   
                    JLabel label = (JLabel) e.getSource();
                    Playlist pl = new Playlist();
                    pl.setPlaylistName(label.getText());
                    FrameMain.getInstane().addFramePlaySongInPlaylist(playlistController.getIDByPlaylistName(pl));
                }   
            });
        }
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
                ArrayList<String> arrayList = null;
                if (txtSearch.getText().equals(""))
                    arrayList = playlistController.getDataForPlaylistHit();
                else
                    arrayList = playlistController.getAllDataPlaylistForPageSearch(0, 5, txtSearch.getText());
                ressetDatatoLabelImage(arrayList);
            }
        });
    }
    
    public void setDatatoLabelImage(){
        int i=0;
        ArrayList<String> array = new ArrayList<>();
        array = playlistController.getDataForPlaylistHit();
        for (i =0;i<array.size();i++){
            arrayList.get(i).setText(array.get(i));
            arrayList.get(i).setIcon(new ImageIcon(getClass().getResource("/Resoures/images.png")));
        }
        for (int j =i; j<arrayList.size();j++){
            arrayList.get(j).setText("");
            arrayList.get(j).setIcon(new ImageIcon(getClass().getResource("/Resoures/image.png")));
        }
    }
    
    public void ressetDatatoLabelImage(ArrayList<String> array){
        int i = 0;
        for (i =0;i<array.size();i++){
            arrayList.get(i).setText(array.get(i));
            arrayList.get(i).setIcon(new ImageIcon(getClass().getResource("/Resoures/images.png")));
        }
        for (int j =i; j<arrayList.size();j++){
            arrayList.get(j).setText("");
            arrayList.get(j).setIcon(new ImageIcon(getClass().getResource("/Resoures/image.png")));
        }
    }
    
    private BufferedImage Resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    
    
    
    
    private void setDatatoCombobox(){
        ArrayList<String> arr_genres = genresController.getGenres();
        for (int i=0; i< arr_genres.size() ; i++){
            cbbGenres.addItem(arr_genres.get(i));
        }
        cbbGenres.addItemListener(new PlaylistController());
    }
    
    private void addButtonActionListener(){
        btnPlaylistFirst.addActionListener(new PlaylistController());
        btnPlaylistPre.addActionListener(new PlaylistController());
        btnPlaylistNext.addActionListener(new PlaylistController());
        btnPlaylistLast.addActionListener(new PlaylistController());
    }
    
    public void updatePagePlaylist(int cur_page, int page){
        lbPagePlaylist.setText(cur_page + " of " + page);
    }
    
    public String getGenres(){
        return cbbGenres.getSelectedItem().toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbPlaylist_1 = new javax.swing.JLabel();
        lbPlaylist_5 = new javax.swing.JLabel();
        lbPlaylist_2 = new javax.swing.JLabel();
        lbPlaylist_3 = new javax.swing.JLabel();
        lbPlaylist_4 = new javax.swing.JLabel();
        lbPlaylist_6 = new javax.swing.JLabel();
        lbPlaylist_7 = new javax.swing.JLabel();
        lbPlaylist_8 = new javax.swing.JLabel();
        lbPlaylist_9 = new javax.swing.JLabel();
        lbPlaylist_10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnPlaylistFirst = new javax.swing.JButton();
        btnPlaylistPre = new javax.swing.JButton();
        lbPagePlaylist = new javax.swing.JLabel();
        btnPlaylistNext = new javax.swing.JButton();
        btnPlaylistLast = new javax.swing.JButton();
        cbbGenres = new javax.swing.JComboBox();
        txtSearch = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1072, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbPlaylist_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_1.setText("a");

        lbPlaylist_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_5.setText("a");

        lbPlaylist_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_2.setText("a");

        lbPlaylist_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_3.setText("a");

        lbPlaylist_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_4.setText("a");

        lbPlaylist_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_6.setText("a");

        lbPlaylist_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_7.setText("a");

        lbPlaylist_8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_8.setText("a");

        lbPlaylist_9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_9.setText("a");

        lbPlaylist_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resoures/image.png"))); // NOI18N
        lbPlaylist_10.setText("a");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnPlaylistFirst.setText("First");
        btnPlaylistFirst.setName("ButtonFirst"); // NOI18N

        btnPlaylistPre.setText("Previous");
        btnPlaylistPre.setName("ButtonPre"); // NOI18N

        lbPagePlaylist.setText("1 of n");

        btnPlaylistNext.setText("Next");
        btnPlaylistNext.setName("ButtonNext"); // NOI18N

        btnPlaylistLast.setText("Last");
        btnPlaylistLast.setName("ButtonLast"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnPlaylistFirst)
                .addGap(29, 29, 29)
                .addComponent(btnPlaylistPre)
                .addGap(18, 18, 18)
                .addComponent(lbPagePlaylist)
                .addGap(18, 18, 18)
                .addComponent(btnPlaylistNext)
                .addGap(27, 27, 27)
                .addComponent(btnPlaylistLast)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlaylistFirst)
                    .addComponent(btnPlaylistPre)
                    .addComponent(lbPagePlaylist)
                    .addComponent(btnPlaylistNext)
                    .addComponent(btnPlaylistLast))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lbPlaylist_1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPlaylist_2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbPlaylist_3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPlaylist_4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPlaylist_5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPlaylist_6, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPlaylist_7, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbPlaylist_8, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPlaylist_9, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPlaylist_10, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(259, 259, 259))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPlaylist_1)
                    .addComponent(lbPlaylist_2)
                    .addComponent(lbPlaylist_5)
                    .addComponent(lbPlaylist_3)
                    .addComponent(lbPlaylist_4))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPlaylist_6)
                    .addComponent(lbPlaylist_7)
                    .addComponent(lbPlaylist_10)
                    .addComponent(lbPlaylist_8)
                    .addComponent(lbPlaylist_9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cbbGenres.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mới & Hot" }));

        txtSearch.setText("Search");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(645, 645, 645)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbGenres, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbGenres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlaylistFirst;
    private javax.swing.JButton btnPlaylistLast;
    private javax.swing.JButton btnPlaylistNext;
    private javax.swing.JButton btnPlaylistPre;
    private javax.swing.JComboBox cbbGenres;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbPagePlaylist;
    private javax.swing.JLabel lbPlaylist_1;
    private javax.swing.JLabel lbPlaylist_10;
    private javax.swing.JLabel lbPlaylist_2;
    private javax.swing.JLabel lbPlaylist_3;
    private javax.swing.JLabel lbPlaylist_4;
    private javax.swing.JLabel lbPlaylist_5;
    private javax.swing.JLabel lbPlaylist_6;
    private javax.swing.JLabel lbPlaylist_7;
    private javax.swing.JLabel lbPlaylist_8;
    private javax.swing.JLabel lbPlaylist_9;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
