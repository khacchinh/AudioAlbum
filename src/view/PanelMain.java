/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import AppPackage.AnimationClass;
import controller.SongController;
import controller.TableControlSongClass;
import dto.Song;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import model.SingerModel;
import model.SongModel;

/**
 *
 * @author Administrator PC
 */
public class PanelMain extends JPanel {
    JSLSlider slider=new JSLSlider();
    private SongController songController = new SongController();
    public PanelMain(){
        try {
            InitComponents();
        } catch (IOException ex) {
            Logger.getLogger(PanelMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /* Khoi tao thanh phan JFrame */
    private void InitComponents() throws IOException{
            Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setSize(screenWidth / 2+200, screenHeight / 2+200);
        this.setBackground(new java.awt.Color(255, 255, 255));
        JPanel row = new JPanel();
        GridBagLayout glr = new GridBagLayout();
        row.setLayout(glr);
        row.setOpaque(false);
        row.setBackground(new java.awt.Color(255, 255, 255));
        this.add(row);
        JPanel row2 = new JPanel();
        JPanel row1 = new JPanel();
        JPanel row3 = new JPanel();
        JPanel rows = new JPanel();
        row1.setBackground(new java.awt.Color(255, 255, 255));
        row2.setBackground(new java.awt.Color(255, 255, 255));
        row3.setBackground(new java.awt.Color(255, 255, 255));
        rows.setBackground(new java.awt.Color(255, 255, 255));
        row2.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        row3.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        GridBagLayout lp = new GridBagLayout();
        row3.setLayout(lp);
        GridBagLayout gl = new GridBagLayout();
        row2.setLayout(gl);
        GridBagConstraints cr = new GridBagConstraints();
        cr.gridx=0;
        cr.gridy = 3;
        cr.fill = GridBagConstraints.HORIZONTAL;
        cr.insets = new Insets(20, 0, 20, 0);
        glr.setConstraints(row2, cr);
        row.add(row2);
        cr.gridx=0;
        cr.gridy = 5;
        cr.fill = GridBagConstraints.HORIZONTAL;
        glr.setConstraints(row3, cr);
        row.add(row3);
        cr.gridx=0;
        cr.gridy = 1;
        cr.fill = GridBagConstraints.HORIZONTAL;
        glr.setConstraints(row1, cr);
        row.add(row1);
        cr.gridx=0;
        cr.gridy = 0;
        cr.fill = GridBagConstraints.HORIZONTAL;
        cr.insets = new Insets(20, 0, 20, 0);
        glr.setConstraints(rows, cr);
        row.add(rows);
        JLabel title3 = new JLabel();
        title3.setText("GENRES");
        title3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        title3.setFont(new Font("TimesRoman", Font.BOLD, 20));
        cr.gridx = 0;
        cr.gridy = 4;
        cr.insets = new Insets(20, 0, -20, 0);
        glr.setConstraints(title3, cr);
        row.add(title3);
        JLabel title4 = new JLabel();
        title4.setText("NEW MUSIC");
        title4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        title4.setFont(new Font("TimesRoman", Font.BOLD, 20));
        cr.gridx = 0;
        cr.gridy = 2;
        cr.insets = new Insets(20, 0, -20, 0);
        glr.setConstraints(title4, cr);
        row.add(title4);
        String k = "http://image.mp3.zdn.vn/thumb/240_240/covers/d/2/d2cb26e345420bc65edf47a9bd9f182a_1449212961.jpg";
        String x = "Hồ Ngọc Hà";
        String z = "Destiny (Single)";
        JButton tb = new JButton("<");
        row1.add(tb);
        Thread t =loop();
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                slider.previous();
            }
        });
        ArrayList<String> arr_song_name = new ArrayList<String>();
        ArrayList<String> arr_song_singer = new ArrayList<String>();
        SongModel songModel = new SongModel();
        SingerModel singerModel = new SingerModel();
        ResultSet resultSet = songModel.getAllDataBXH();
        arr_song_singer.clear();
        arr_song_name.clear();
        try{
            while(resultSet.next()){
                        arr_song_name.add(resultSet.getString(Song.col_name));
                        arr_song_singer.add(singerModel.getNameByIDint(resultSet.getInt(Song.col_singer)));
                    }
        }        
        catch (SQLException e){
               System.out.println("error code"+e.getErrorCode());
            }
        
        createslide(row1,"http://image.mp3.zdn.vn/banner/a/6/a6ed1f94b56872dd49246faafeaf91db_1449203373.jpg","http://image.mp3.zdn.vn/banner/6/7/6765d75ea6c8683e97277b7e8efe00f6_1449111636.jpg","http://image.mp3.zdn.vn/banner/c/7/c7c22df1d1347a88e848fe3268643e86_1449545312.jpg","http://image.mp3.zdn.vn/banner/6/0/605493181fba400002524defa5376340_1449569262.jpg","http://image.mp3.zdn.vn/banner/0/7/07d2d8a9a9749f95c03fcb0d0548548e_1449741502.jpg");
        searchMain(rows);
        JButton tb1 = new JButton(">");
        row1.add(tb1);
        tb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                slider.next();
            }
        });
        for(int i=0;i<10 && i <arr_song_name.size();i++){
             CreateThumnail(row2,gl,k,i,0,arr_song_singer.get(i),arr_song_name.get(i));
        }
        CreateList(row3,lp,0,0,"POP",1);
        CreateList(row3,lp,1,0,"Jazz",2);
        CreateList(row3,lp,2,0,"Rock",8);
        BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/audio.png"));
        ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
        this.setVisible(true);
        
    }
    private void searchMain(JPanel t){
        JPanel pSearch = new JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pSearch.setBackground(new java.awt.Color(255, 255, 255));
        JTextField txtSearch = new JTextField("Thông tin tìm kiếm...");
        pSearch.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 2, 300, 35));
        JButton button = new JButton("");
        pSearch.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 39, 39));
        button.setIcon(new ImageIcon(getClass().getResource("/Resoures/search.png")));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               //viết tìm kiếm trong đây
                if (txtSearch.getText().equals("Thông tin tìm kiếm..."))
                    return;
                SearchGeneral.key_search = txtSearch.getText();
                new SearchGeneral().setVisible(true);
            }
        });
        t.add(pSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 500, 40));
        
        
    }
    /* Tao SlideShow Cover Image hot Album */
    private void createslide(JPanel t, String img1, String img2, String img3,String img4, String img5){
        AnimationClass AC = new AnimationClass();
        JPanel x = new JPanel();
        JLabel image1 = new JLabel();
        JLabel image2 = new JLabel();
        JLabel image3 = new JLabel();
        JLabel image4 = new JLabel();
        JLabel image5 = new JLabel();
//        x.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
//        x.add(image1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 200));
//        x.add(image2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 400, 200));
//        x.add(image3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 400, 200));
//        x.add(image4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 400, 200));
//        x.add(image5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 0, 400, 200));
//        x.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.RED));
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridx = 0;
//        c.gridy = 0;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0;
//        t.add(x, new org.netbeans.lib.awtextra.AbsoluteConstraints(350,0, 400, 200));
            
            slider.addSliderComponent(image1);
            slider.addSliderComponent(image2);
            slider.addSliderComponent(image3);
            slider.addSliderComponent(image4);
            slider.addSliderComponent(image5);
            t.add(slider);
         try {
            BufferedImage bi2 = ImageIO.read(new URL (img1));
            ImageIcon img_1 = new ImageIcon(Resize(bi2,400,200));
            image1.setIcon(img_1);
            bi2 = ImageIO.read(new URL (img2));
            ImageIcon img_2 = new ImageIcon(Resize(bi2,400,200));
            image2.setIcon(img_2);
            bi2 = ImageIO.read(new URL (img3));
            ImageIcon img_3 = new ImageIcon(Resize(bi2,400,200));
            image3.setIcon(img_3);
            bi2 = ImageIO.read(new URL (img4));
            ImageIcon img_4 = new ImageIcon(Resize(bi2,400,200));
            image4.setIcon(img_4);
            bi2 = ImageIO.read(new URL (img5));
            ImageIcon img_5 = new ImageIcon(Resize(bi2,400,200));
            image5.setIcon(img_5);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    private Thread loop(){
        Thread t = new Thread(){
            @Override
                    public void run(){
                        while(true){
                        try {
                        Thread.sleep(4000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PanelMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        slider.next();
                    }}
        };
        t.start();
        return t;
    }
    /* Tao list song kieu thumnail*/
    private void CreateThumnail(JPanel t,GridBagLayout k, String y,int px,int py,String x , String z){
        JPanel jp = new JPanel();
        jp.setBackground(new java.awt.Color(255, 255, 255));
        jp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JLabel thumnail = new JLabel();
        JLabel lb_name = new JLabel("Until You sađâ adâđa");
        lb_name.setText(x);
        lb_name.setFont(new Font("TimesRoman", Font.BOLD, 8));
        JLabel lb_singer = new JLabel("One direction");
        lb_singer.setText(z);
        lb_singer.setFont(new Font("TimesRoman", Font.BOLD, 8));
        GridBagLayout gb_jp = new GridBagLayout();
        GridBagConstraints gb_ct = new GridBagConstraints();
        jp.setLayout(gb_jp);
        gb_ct.gridx =0;
        gb_ct.gridy =0;
        gb_jp.setConstraints(thumnail, gb_ct);
        jp.add(thumnail);
        gb_ct.gridx =0;
        gb_ct.gridy =1;
        gb_jp.setConstraints(lb_name, gb_ct);
        jp.add(lb_name);
        gb_ct.gridx =0;
        gb_ct.gridy =2;
        gb_jp.setConstraints(lb_singer, gb_ct);
        jp.add(lb_singer);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = px;
        c.gridy = py;
        c.insets = new Insets(5, 5, 5, 5);
        k.setConstraints(jp, c);
        t.add(jp);
         try {
            BufferedImage bi2 = ImageIO.read(new URL (y));
            thumnail.setSize(50,50);
            int w = 80;
            int h = 50;
            ImageIcon img = new ImageIcon(Resize(bi2,w,h));
            thumnail.setIcon(img);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (!lb_singer.getText().equals("")){
            lb_singer.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e){
                    JLabel lb = (JLabel) e.getSource();
                    SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                            try {
                                PlaySong.ulrsong = songController.getSRCSongByName(lb.getText());;
                                PlaySong.cmt_song.setSongID(songController.getSongIDByName(lb.getText()));
                                new PlaySong();
                                songController.updateLikeSong(PlaySong.cmt_song.getSongID());
                            } catch (IOException ex) {
                                Logger.getLogger(TableControlSongClass.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        }
                    });
                }
            });
        }
    }
    /* Tao list Song kieu list*/
    private void CreateList(JPanel t, GridBagLayout y, int px, int py,String xh,int genresid){
        ArrayList<String> arr_song_name = new ArrayList<String>();
        ArrayList<String> arr_song_singer = new ArrayList<String>();
        SongModel songModel = new SongModel();
        SingerModel singerModel = new SingerModel();
        ResultSet resultSet = songModel.getSongByGenres(genresid);
        arr_song_singer.clear();
        arr_song_name.clear();
        try{
            while(resultSet.next()){
                        arr_song_name.add(resultSet.getString(Song.col_name));
                        arr_song_singer.add(singerModel.getNameByIDint(resultSet.getInt(Song.col_singer)));
                    }
        }        
        catch (SQLException e){
               System.out.println("error code"+e.getErrorCode());
           }
        JPanel x = new JPanel();
        x.setBackground(new java.awt.Color(255, 255, 255));
        GridBagLayout h = new GridBagLayout();
        JLabel k = new JLabel();
        k.setText(xh);
        k.setFont(new Font("TimesRoman", Font.BOLD, 15));
        x.setLayout(h);
        if(!arr_song_name.isEmpty())
            for(int i =1;i<arr_song_name.size()+1&&i<7;i++){
                    CreateListItem(x,h,arr_song_name.get(i-1),arr_song_singer.get(i-1),0,i); 
            }
        if(arr_song_name.size()<7)
            for(int i =arr_song_name.size()+1;i<7;i++)
                CreateListItem(x,h,"","",0,i); 
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        h.setConstraints(k, c);
        x.add(k);
        c.gridx =px;
        c.gridy =py;
        c.insets = new Insets(15, 15, 15, 15);
        y.setConstraints(x, c);
        t.add(x);
        
    }
    /* Tao tung phan tu cua Song list*/
    private void CreateListItem(JPanel t,GridBagLayout y,String h,String k,int px,int py){
        JPanel x = new JPanel();
        x.setBackground(new java.awt.Color(255, 255, 255));
        x.setPreferredSize(new Dimension(300, 20));
        x.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        JLabel jb_name = new JLabel();
        jb_name.setText(h);
        jb_name.setPreferredSize(new Dimension(200, 20));
        JLabel jb_view = new JLabel();
        jb_view.setText(k);
        jb_view.setPreferredSize(new Dimension(100, 20));
        GridBagLayout row = new GridBagLayout();
        GridBagConstraints crow = new GridBagConstraints();
        crow.gridx = 0;
        crow.gridx = 0;
        crow.insets = new Insets(5, 5, 5, 5);
        row.setConstraints(jb_name, crow);
        x.setLayout(row);
        x.add(jb_name);
        crow.gridx = 0;
        crow.gridx = 1;
        row.setConstraints(jb_view, crow);
        x.add(jb_view);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = px;
        c.gridy = py;
        c.insets = new Insets(5, 0, 5, 0);
        y.setConstraints(x, c);
        t.add(x);
        
        //event 
        if (!jb_name.getText().equals("")){
            jb_name.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e){
                    JLabel lb = (JLabel) e.getSource();
                    SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                            try {
                                PlaySong.ulrsong = songController.getSRCSongByName(lb.getText());;
                                PlaySong.cmt_song.setSongID(songController.getSongIDByName(lb.getText()));
                                new PlaySong();
                                songController.updateLikeSong(PlaySong.cmt_song.getSongID());
                            } catch (IOException ex) {
                                Logger.getLogger(TableControlSongClass.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        }
                    });
                }
            });
        }
    }
    /* Thay doi lai kich thuoc Image */
    private BufferedImage Resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
}
  
