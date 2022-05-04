package com.vcampus.client.main.student;

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.style.StyleId;
import com.vcampus.UI.labels.MyJLabel;
import com.vcampus.client.LoginUI;
import com.vcampus.client.main.App;
import com.vcampus.client.main.AppLife;
import com.vcampus.client.main.student.StudentInfo.AppStuInfo;
import com.vcampus.client.main.shop.AppShop;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 学生主入口
 */

public class AppStudentBackup extends JFrame {
    private static Locale locale = Locale.getDefault();
    private static ResourceBundle res = ResourceBundle.getBundle("com.vcampus.client.ClientResource", locale);
    static int index=0;
    public MyJLabel avatarPhoto =new MyJLabel();
    private class TimeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(index==3) index=0;
            else index++;
            avatarPhoto.setIcon(new ImageIcon(getClass().getResource("/resources/assets/testphoto/pic"+index+".jpg")));
        }
    }

    public AppStudentBackup() {
        setResizable(true);
        setTitle(res.getString("student_main"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d.width, d.height);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblVcampus = new JLabel(res.getString("student_main"));
        lblVcampus.setHorizontalAlignment(SwingConstants.CENTER);
        lblVcampus.setFont(new Font("微软雅黑", Font.BOLD, 40));
        lblVcampus.setForeground(new Color(0xF70F535F, true));
        lblVcampus.setBounds(50, 22, 800, 43);
        contentPane.add(lblVcampus);

        JLayeredPane self=new JLayeredPane();
        self.setBounds(70,50,820,600);

        avatarPhoto.setIcon(new ImageIcon(getClass().getResource("/resources/assets/testphoto/pic0.jpg")));
        avatarPhoto.setBounds(20,60,800,400);
        avatarPhoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.lib.seu.edu.cn/"));
                } catch (Exception l){
                    l.printStackTrace();
                }
            }
        });
        JButton btnAddPhoto=new JButton("←");
        btnAddPhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(index<3) index++;
                else index=0;
                avatarPhoto.setIcon(new ImageIcon(getClass().getResource("/resources/assets/testphoto/pic"+index+".jpg")));
            }
        });
        btnAddPhoto.setBounds(20,241,50,50);
        btnAddPhoto.setFont(new Font("微软雅黑", Font.BOLD, 25));
        btnAddPhoto.setForeground(new Color(0xDA2E2E95, true));
        JButton nextPhoto=new JButton("→");
        nextPhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(index>0)  index--;
                else index=3;
                avatarPhoto.setIcon(new ImageIcon(getClass().getResource("/resources/assets/testphoto/pic"+index+".jpg")));
            }
        });
        nextPhoto.setBounds(745,240,50,50);
        nextPhoto.setFont(new Font("微软雅黑", Font.BOLD, 25));
        nextPhoto.setForeground(new Color(0xDA2E2E95, true));

        self.add(btnAddPhoto,1);
        self.add(nextPhoto,1);
        self.add(avatarPhoto,2);
        contentPane.add(self);

        Timer timer=new Timer(5000,new TimeListener());
        timer.start();

        JLabel label =new JLabel();
        label.setText(" 欢迎您,"+ App.session.getStudent().getName());
        label.setFont(new Font("微软雅黑", Font.BOLD, 14));
        label.setOpaque(true);
        label.setForeground(new Color(33, 117, 206));
        label.setBackground(new Color(33,177,206,80));
        label.setBounds(1270, 5, 100, 30);
        contentPane.add(label);


        JButton logout = new JButton(res.getString("logout"));
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==logout)
                {
                    LoginUI app=new LoginUI();
                    app.setVisible(true);
                    setVisible(false);
                }
            }
        });
        logout.setFont(new Font("微软雅黑", Font.BOLD, 14));
        logout.setBounds(1380,5,50,30);
        logout.setForeground(new Color(33, 117, 206,100));
        contentPane.add(logout);

        MyJLabel lblNews = new MyJLabel();
        lblNews.setText("点击进入图书馆");
        lblNews.setBounds(765,470,185,85);
        lblNews.setFont(new Font("微软雅黑", Font.BOLD, 16));
        lblNews.setForeground(new Color(17, 70, 123));
        lblNews.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblNews);

        lblNews.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        final JButton btnOpencourse = new JButton("图书卡管理");
        btnOpencourse.putClientProperty( StyleId.STYLE_PROPERTY, StyleId.buttonHover);
        btnOpencourse.setBounds(285, 560, 150, 150);
        btnOpencourse.setOpaque(true);
        btnOpencourse.setFont( new Font("微软雅黑", Font.BOLD, 18));
        btnOpencourse.setBackground(new Color(0xDA61A4B8, true));
        btnOpencourse.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                new Color(255,255,255,255)));
        btnOpencourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btnOpencourse)
                {
                    AppLife app=new AppLife();
                    app.setVisible(true);
                }
            }
        });
        contentPane.add(btnOpencourse);

        final JButton btnShop = new JButton("网上在线书店");
        btnShop.putClientProperty( StyleId.STYLE_PROPERTY, StyleId.buttonHover);
        btnShop.setBounds(520, 560, 150, 150);
        btnShop.setOpaque(true);
        btnShop.setFont( new Font("微软雅黑", Font.BOLD, 18));
        btnShop.setBackground(new Color(0xDAF1AA7C, true));
        btnShop.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                new Color(255,255,255,255)));
        btnShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btnShop)
                {
                    AppShop app=new AppShop();
                    app.setVisible(true);
                }
            }
        });
        contentPane.add(btnShop);


        MyJLabel lblNewLabel_1 = new MyJLabel();
        lblNewLabel_1.setText("关于我(点击可进行编辑)");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setForeground(new Color(21, 85, 151));
        //lblNewLabel_1.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/aboutme.png")));
        lblNewLabel_1.setBounds(1006, 103, 300, 80);
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==lblNewLabel_1)
                {
                    AppStuInfo app=new AppStuInfo();
                    app.setVisible(true);
                }
            }
        });
        contentPane.add(lblNewLabel_1);


        StuInfoPanel jplStuInfo=new StuInfoPanel();
        jplStuInfo.init();
        jplStuInfo.setBounds(1006,170,350,230);
        contentPane.add(jplStuInfo);



        /*StuCoursePanel jplStuCourse=new StuCoursePanel();
        jplStuCourse.init();
        jplStuCourse.setBounds(1006,500,350,230);
        contentPane.add(jplStuCourse);*/

        //297-302,110
    }

}