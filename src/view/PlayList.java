/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import jaco.mp3.player.MP3Player;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator PC
 */
public class PlayList extends JFrame {
    public static MP3Player player = new MP3Player();
    public PlayList() throws IOException{
        InitComponents();
    }
    /* Khoi tao thanh phan JFrame */
    private void InitComponents() throws IOException{
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        JFrame g_main = new JFrame("Play List");
        g_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //g_main.setSize(screenWidth / 2+200, screenHeight / 2+200);
        g_main.setLocation(screenWidth / 4, screenHeight / 4);
        JPanel row = new JPanel();
        row.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row3 = new JPanel();
        JPanel row4 = new JPanel();
        JPanel col1 = new JPanel();
        JLabel lb_title1 = new JLabel();
        JLabel lb_title2 = new JLabel();
        JLabel lb_title3 = new JLabel();
        lb_title1.setText("PLAY LIST");
        lb_title2.setText("COMMENT");
        lb_title3.setText("BẢNG XẾP HẠNG");
        row2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        GridBagLayout gb_col1 = new GridBagLayout();
        col1.setLayout(gb_col1);
        row.add(row1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100,100,500, 150));
        row.add(row2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 500, 60));
        row.add(lb_title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 100, 60));
        row.add(row3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 550, 220));
        row.add(lb_title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 150, 60));
        row.add(row4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 600, 550, 220));
        row.add(col1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 400, 500));
        row.setBackground(new java.awt.Color(255, 255, 255));
        row3.setBackground(new java.awt.Color(255, 255, 255));
        row4.setBackground(new java.awt.Color(255, 255, 255));
        row.setBackground(new java.awt.Color(255, 255, 255));
        col1.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.RED));
        String k = "http://image.mp3.zdn.vn/banner/1/5/15f0ac89930dcd9d2dd2848f2ec094c7_1450150989.jpg";
        createimage(row1,k);
        CreatePlayer(row2);
        CreateListPlay(row3);
        CreateCommentList(row4);
        CreateList(col1,gb_col1,0,0,"VPOP");
        CreateList(col1,gb_col1,0,1,"US/UK");
        //CreateItemPlay(row3);
        g_main.setContentPane(row);
        g_main.pack();
        g_main.setVisible(true);
        BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/audio.png"));
        ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
        g_main.setIconImage(img_1.getImage());
    }
    private static void createimage(JPanel h,String y){
        JPanel jp = new JPanel();
        JLabel lb_img = new JLabel();
        jp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jp.add(lb_img, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0,500, 150));
        lb_img.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        GridBagLayout gbl = new GridBagLayout();
        h.setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        gbl.setConstraints(jp, c);
        h.add(jp);
        try {
            BufferedImage bi2 = ImageIO.read(new URL (y));
            lb_img.setSize(250,100);
            int w = 500;
            int he = 150;
            ImageIcon img = new ImageIcon(Resize(bi2,w,he));
            lb_img.setIcon(img);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private  void CreatePlayer(JPanel t) throws MalformedURLException{
        JPanel jp_player = new JPanel();
        JLabel jl_play = new JLabel();
        JLabel jl_name = new JLabel();
        JLabel jl_repeat = new JLabel();
        JLabel jl_addplaylist = new JLabel();
        JLabel jl_download = new JLabel();
        JLabel jl_favorite = new JLabel();
        jl_name.setText("Destiny - Hồ Ngọc Hà ");
        JLabel jl_timer = new JLabel();
        jl_timer.setText("2:10/4:15");
        jp_player.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jp_player.add(jl_play, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5,50, 40));
        jp_player.add(jl_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 5,200, 40));
        jp_player.add(jl_timer, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 5,70, 40));
        jp_player.add(jl_repeat, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 5,40, 40));
        jp_player.add(jl_addplaylist, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 5,40, 40));
        jp_player.add(jl_download, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 5,40, 40));
        jp_player.add(jl_favorite, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 5,40, 40));
        t.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        t.add(jp_player, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,5,480, 50));
        player.addToPlayList(new URL("http://audioalbum.esy.es/mp3/Destiny%20-%20Ho%20Ngoc%20Ha.mp3"));
        jl_play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                boolean firstplay = false;
               if(firstplay == false)
               {
                   playm(evt,jl_play,firstplay);
                   System.out.println("Đang dừng");
               }
               else{
                   pausem(evt,jl_play,firstplay);
                   System.out.println("Đang phát");
               }
            }
        });
        try {
            BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/ic_play_arrow_black_48dp.png"));
            ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
            jl_play.setIcon(img_1);
            bi2 = ImageIO.read(getClass().getResource("/resources/ic_repeat_black_48dp.png"));
            ImageIcon img_2 = new ImageIcon(Resize(bi2,25,25));
            jl_repeat.setIcon(img_2);
            bi2 = ImageIO.read(getClass().getResource("/resources/download.png"));
            ImageIcon img_3 = new ImageIcon(Resize(bi2,25,25));
            jl_download.setIcon(img_3);
            bi2 = ImageIO.read(getClass().getResource("/resources/heart.png"));
            ImageIcon img_4 = new ImageIcon(Resize(bi2,25,25));
            jl_favorite.setIcon(img_4);
            bi2 = ImageIO.read(getClass().getResource("/resources/ic_my_library_add_black_48dp.png"));
            ImageIcon img_5 = new ImageIcon(Resize(bi2,25,25));
            jl_addplaylist.setIcon(img_5);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private  void playm(java.awt.event.MouseEvent evt,JLabel jl_play, boolean t) {                                      
        // TODO add your handling code here:
      player.play();
      t=true;
      System.out.print(player.getPlayList());
      try {
            BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/ic_pause_black_48dp.png"));
            ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
             jl_play.setIcon(img_1);
        } catch (IOException ex) {
            Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    private void pausem(java.awt.event.MouseEvent evt,JLabel jl_play,boolean t) {                                      
        // TODO add your handling code here:
      player.pause();
      t = false;
      System.out.print(player.getPlayList());
        try {
            BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/ic_play_arrow_black_48dp.png"));
            ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
             jl_play.setIcon(img_1);
        } catch (IOException ex) {
            Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    private  void CreateListPlay(JPanel t){
        JPanel jp_list_play = new JPanel();
        jp_list_play.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        GridBagLayout gb_list = new GridBagLayout();
        jp_list_play.setLayout(gb_list);
        boolean h;
        for(int i=0;i<10;i++)
        {   
            h=false;
            if(i==2){
                h=true;
            }
            CreateItemPlay(jp_list_play,gb_list,0,i,h);
        }
        JScrollPane scrollPane = new JScrollPane(jp_list_play);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 150, 150);
        t.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        t.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5,500, 200));
    }
    private  void CreateItemPlay(JPanel t,GridBagLayout h, int px, int py, boolean play){
        JPanel jp_item = new JPanel();
        JLabel jl_playing = new JLabel();
        JLabel jl_name_playing = new JLabel();
        JLabel jl_add_list = new JLabel();
        JLabel jl_down_playing = new JLabel();
        JLabel jl_fav_playing = new JLabel();
        jp_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clickplay(evt);
            }
        });
        jp_item.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        if(py%2!=0){
            jp_item.setBackground(new java.awt.Color(204, 204, 204));
        }
        jl_name_playing.setText("Destiny - Ho Ngoc Ha");
        jp_item.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jp_item.add(jl_playing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5,50, 40));
        jp_item.add(jl_name_playing, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 5,200, 40));
        jp_item.add(jl_add_list, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 5,70, 40));
        jp_item.add(jl_down_playing, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 5,40, 40));
        jp_item.add(jl_fav_playing, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 5,40, 40));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = px;
        c.gridy = py;
        c.insets = new Insets(5, 0, 5, 0);
        h.setConstraints(jp_item, c);
        t.add(jp_item);
        try {
            BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/ic_play_arrow_black_48dp.png"));
            ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
            if(play==true){
                jl_playing.setIcon(img_1);
            }
            bi2 = ImageIO.read(getClass().getResource("/resources/ic_my_library_add_black_48dp.png"));
            ImageIcon img_3 = new ImageIcon(Resize(bi2,25,25));
            jl_add_list.setIcon(img_3);
            bi2 = ImageIO.read(getClass().getResource("/resources/download.png"));
            ImageIcon img_4 = new ImageIcon(Resize(bi2,25,25));
            jl_down_playing.setIcon(img_4);
            bi2 = ImageIO.read(getClass().getResource("/resources/heart.png"));
            ImageIcon img_5 = new ImageIcon(Resize(bi2,25,25));
            jl_fav_playing.setIcon(img_5);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void CreateCommentList(JPanel t){
        JPanel jp_list_cmt = new JPanel();
        jp_list_cmt.setBackground(new java.awt.Color(255, 255, 255));
        GridBagLayout gb_cmt = new GridBagLayout();
        jp_list_cmt.setLayout(gb_cmt);
        for(int i=0;i<15;i++)
        {
            CreateCommentItem(jp_list_cmt,gb_cmt,0,i,"http://image.mp3.zdn.vn/banner/1/5/15f0ac89930dcd9d2dd2848f2ec094c7_1450150989.jpg","Truong Hoang Gia","Những bài hát về ngày tết hay nhất, nhạc xuân tuyển chọn 2016, nhạc tết 2016 cực hay","10/12/2015");
        }
        JScrollPane scrollPane = new JScrollPane(jp_list_cmt);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 150, 150);
        t.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        t.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5,500, 200));
    }
    private void CreateCommentItem(JPanel t, GridBagLayout gb,int px, int py, String s_img,String s_name, String s_cmt, String s_time){
        JPanel jp_comment = new JPanel();
        JLabel lb_avata = new JLabel();
        JLabel lb_name = new JLabel();
        JLabel lb_comment = new JLabel();
        JLabel lb_time = new JLabel();
        lb_name.setText(s_name);
        lb_name.setFont(new java.awt.Font("Times New Roman", 3, 12));
        lb_name.setForeground(new java.awt.Color(153, 153, 153));
        lb_comment.setText(s_cmt);
        lb_time.setText(s_time);
        jp_comment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jp_comment.add(lb_avata, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5,50, 50));
        jp_comment.add(lb_comment, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10,300, 40));
        jp_comment.add(lb_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -10,200, 50));
        jp_comment.add(lb_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 5,70, 40));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = px;
        c.gridy = py;
        c.insets = new Insets(5, 0, 5, 0);
        gb.setConstraints(jp_comment, c);
        t.add(jp_comment);
         try {
            BufferedImage bi2 = ImageIO.read(new URL (s_img));
            lb_avata.setSize(250,100);
            int w = 50;
            int he = 50;
            ImageIcon img = new ImageIcon(Resize(bi2,w,he));
            lb_avata.setIcon(img);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void CreateList(JPanel t, GridBagLayout y, int px, int py,String xh){
        String j = "One direction";
        String z = "What Makes You Beautiful";
        JPanel x = new JPanel();
        GridBagLayout h = new GridBagLayout();
        JLabel k = new JLabel();
        k.setText(xh);
        k.setFont(new Font("TimesRoman", Font.BOLD, 15));
        x.setLayout(h);
        for(int i =1;i<7;i++){
         CreateListItem(x,h,z,j,0,i);   
        }
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
        JLabel jb_name = new JLabel();
        JLabel jb_view = new JLabel();
        x.setPreferredSize(new Dimension(300, 20));
        x.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        jb_name.setText(h);
        jb_name.setPreferredSize(new Dimension(200, 20));
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
    }
    private static BufferedImage Resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    private static void clickplay(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
      System.out.print("click");
    }
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try {
                    new PlayList();
                } catch (IOException ex) {
                    Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
  
