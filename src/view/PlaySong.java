/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CommentPlaylistController;
import controller.CommentSongController;
import controller.PlaylistController;
import controller.SongController;
import controller.UserController;
import dto.Comment_Playlist;
import dto.Comment_Song;
import dto.Playlist;
import dto.Playlist_Song;
import dto.User;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Administrator PC
 */
public class PlaySong extends JFrame {
    public static MP3Player player = new MP3Player();
    public static Comment_Song cmt_song = new Comment_Song();
    public static String ulrsong = null; 
    private static CommentSongController commentSongController = new CommentSongController();
    private static UserController userController = new UserController();
    private PlaylistController playlistController = new PlaylistController();
    private SongController songController = new SongController();
    private static PlaySong ins;
    public static JPanel jp_list_cmt;
    public static GridBagLayout gb_cmt;
    public JPanel jp_list_play;
    public static GridBagLayout gb_list;
    private JTextField text_cmt = new JTextField();
    private ArrayList<Comment_Song> arr_comment_Song = new ArrayList<>();
    private static boolean firstplay = false;
    public  JLabel jl_song_name_playing = new JLabel();
    private JLabel jl_play_image = new JLabel();
    public static PlaySong getInstane(){
        return ins;
    }
    public PlaySong() throws IOException{
        ins = this;
        firstplay = false;
        //cmt_song.setSongID(1);
        InitComponents();
    }
    
    
    //get data from database
    private void addData(){
        arr_comment_Song.clear();
        arr_comment_Song = commentSongController.getAllCommentBySongID(cmt_song);
        System.out.println(arr_comment_Song.size());
    }
    // get text comment
    public String getTextComment(){
        return text_cmt.getText();
    } 
    public void clearTextComment(){
        text_cmt.setText("");
    }
    /* Khoi tao thanh phan JFrame */
    private void InitComponents() throws IOException{
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setTitle("Play Song");
        this.setLocation(screenWidth / 4, screenHeight / 6);
        JPanel row = new JPanel();
        row.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row4 = new JPanel();
        JLabel lb_title2 = new JLabel();
        JLabel lb_title3 = new JLabel();
        BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/heart.png"));
        ImageIcon img_4 = new ImageIcon(Resize(bi2,25,25));
        lb_title2.setText("COMMENT");
        lb_title3.setText("BẢNG XẾP HẠNG");
        row2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        GridBagLayout gb_col1 = new GridBagLayout();
        row.add(row1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100,100,500, 150));
        row.add(row2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 500, 60));
        row.add(lb_title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 305, 150, 60));
        row.add(row4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 550, 200));
        row.setBackground(new java.awt.Color(255, 255, 255));
        row4.setBackground(new java.awt.Color(255, 255, 255));
        row.setBackground(new java.awt.Color(255, 255, 255));
        String k = "http://image.mp3.zdn.vn/banner/1/5/15f0ac89930dcd9d2dd2848f2ec094c7_1450150989.jpg";
        createimage(row1,k);
        CreatePlayer(row2);
        CreateCommentList(row4);
        this.setContentPane(row);
        this.pack();
        bi2 = ImageIO.read(getClass().getResource("/resources/audio.png"));
        ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
        this.setIconImage(img_1.getImage());
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                player.stop();
                player = new MP3Player();
                ulrsong = null;
            }
        });
        
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
        JLabel jl_repeat = new JLabel();
        JLabel jl_addplaylist = new JLabel();
        JLabel jl_download = new JLabel();
        JLabel jl_favorite = new JLabel();
        JLabel jl_timer = new JLabel();
        jl_timer.setText("0:00/4:15");
        jp_player.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jp_player.add(jl_play_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5,50, 40));
        jp_player.add(jl_song_name_playing, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 5,200, 40));
        jp_player.add(jl_timer, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 5,70, 40));
        jp_player.add(jl_repeat, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 5,40, 40));
        jp_player.add(jl_addplaylist, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 5,40, 40));
        jp_player.add(jl_download, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 5,40, 40));
        jp_player.add(jl_favorite, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 5,40, 40));
        jl_addplaylist.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                new FrameAddSongtoPlaylist().setVisible(true);
                FrameAddSongtoPlaylist.song_id = cmt_song.getSongID();
            }
        });
        jl_download.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                Dowload t = new Dowload();
                t.linkfile = "http://" + ulrsong;
                t.setVisible(true);
            }
        });
        jl_favorite.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                songController.updateLikeSong(cmt_song.getSongID());
                System.out.println("thanh cong");
            }
        });
        t.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        t.add(jp_player, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,5,480, 50));
        //them nhac vao day
        player.addToPlayList(new URL("http://" + ulrsong));
        jl_play_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               if(firstplay == false)
               {
                   playm(jl_play_image);
                   System.out.println("Đang phat ----");
               }
               else{
                   pausem(jl_play_image);
                   System.out.println("Đang dung ------");
               }
            }
        });
        try {
            BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/ic_play_arrow_black_48dp.png"));
            ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
            jl_play_image.setIcon(img_1);
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
    private  void playm(JLabel jl_play) {                                      
        // TODO add your handling code here:
      player.play();
      firstplay=true;
      System.out.print(player.getPlayList());
      try {
            BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/ic_pause_black_48dp.png"));
            ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
             jl_play.setIcon(img_1);
        } catch (IOException ex) {
            Logger.getLogger(PlaySong.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    private void pausem(JLabel jl_play) {                                      
        // TODO add your handling code here:
      player.pause();
      firstplay = false;
      System.out.print(player.getPlayList());
        try {
            BufferedImage bi2 = ImageIO.read(getClass().getResource("/resources/ic_play_arrow_black_48dp.png"));
            ImageIcon img_1 = new ImageIcon(Resize(bi2,50,50));
             jl_play.setIcon(img_1);
        } catch (IOException ex) {
            Logger.getLogger(PlaySong.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
   
    private void CreateCommentList(JPanel t){
        jp_list_cmt = new JPanel();
        JPanel jp_text_cmt = new JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout());
        // create jtextFiled comment
        jp_text_cmt.add(text_cmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 4, 370, 40)); //add textfiled comment to jpanel
        JButton btnCmt = new JButton("Submit"); //create a button to submit comment
        btnCmt.setName("ButtonSubmitCMT");
        btnCmt.addActionListener(commentSongController);
        jp_text_cmt.add(btnCmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 100, 30));
        jp_list_cmt.setBackground(new java.awt.Color(255, 255, 255));
        gb_cmt = new GridBagLayout();
        jp_list_cmt.setLayout(gb_cmt);
        setDataCommentList(jp_list_cmt, gb_cmt);  
        JScrollPane scrollPane = new JScrollPane(jp_list_cmt);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 150, 150);
        t.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        t.add(jp_text_cmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 50));
        t.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 500, 140));
    }
    public void setDataCommentList(JPanel t, GridBagLayout gb){
        jp_list_cmt.removeAll();
        jp_list_cmt.repaint();
        addData();
        
        for(int i=0; i<arr_comment_Song.size(); i++)
            {
                User user = new User();
                user.setUserID(arr_comment_Song.get(i).getUserID());
                CreateCommentItem(t, gb,0,i,
                        "http://image.mp3.zdn.vn/banner/1/5/15f0ac89930dcd9d2dd2848f2ec094c7_1450150989.jpg",
                        userController.getNameByID(user),
                        arr_comment_Song.get(i).getCommentText(),
                        arr_comment_Song.get(i).getCommentDate().toString());
                System.out.println(arr_comment_Song.get(i).getCommentText());
                
            }
                
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
      
    }
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try {
                    new PlaySong();
                } catch (IOException ex) {
                    Logger.getLogger(PlaySong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
  
