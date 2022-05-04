package com.vcampus.client.main.manager;

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.style.StyleId;
import com.vcampus.UI.labels.MyJLabel;
import com.vcampus.client.LoginUI;
import com.vcampus.client.main.App;
import com.vcampus.client.main.AppLife;
import com.vcampus.client.main.college.AppCollegeAdmin;
import com.vcampus.client.main.shop.AppShopAdmin;
import com.vcampus.client.main.student.AppStudent;
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

public class AppAdmin extends JFrame {
    static int index=0;
    public MyJLabel avatarPhoto =new MyJLabel();
    private class TimeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(index==5) index=1;
            else index++;
            avatarPhoto.setIcon(new ImageIcon(getClass().getResource("/resources/assets/bgnew/"+index+".jpg")));
        }
    }

    public AppAdmin() {
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        setResizable(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/assets/icon/graLogo.png")));
        setTitle("管理员主页 - 研究生招生信息系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d.width, d.height);

        setLayout(null);

        JLabel InfoLabel1 = new JLabel("个人信息管理");//文字框
        InfoLabel1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        InfoLabel1.setForeground(new Color(0, 0, 0,150));//字颜色
        InfoLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        InfoLabel1.setBounds(985, 230, 160, 160);
        InfoLabel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==InfoLabel1)
                {
                    AdminInfoPanel app=new AdminInfoPanel();
                    app.setVisible(true);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                InfoLabel1.setForeground(new Color(0, 0, 0, 243));//字颜色

            }

            @Override
            public void mouseExited(MouseEvent e) {
                InfoLabel1.setForeground(new Color(0, 0, 0,150));//字颜色
            }
        });
        add(InfoLabel1);

        JLabel Label1 = new JLabel("");//透明框
        Label1.setHorizontalAlignment(SwingConstants.CENTER);
        Label1.setOpaque(true);
        Label1.setBackground(new Color(255,255,255,90));
        Label1.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                new Color(255,255,255,255)));
        Label1.setBounds(985, 230, 160, 160);
        add(Label1);//先添加的label在最上层


        JLabel bg1 = new JLabel(new ImageIcon(getClass().getResource("/resources/assets/bgnew/7.jpg")));
        bg1.setPreferredSize(new Dimension(400,400));
        add(bg1);
        bg1.setBounds(820, 65, 500, 500);


        JLabel InfoLabel2 = new JLabel("成绩管理");
        InfoLabel2.setFont(new Font("微软雅黑", Font.BOLD, 18));
        InfoLabel2.setForeground(new Color(0, 0, 0,150));//字颜色
        InfoLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        InfoLabel2.setBounds(985, 580, 340, 160);
        InfoLabel2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()== InfoLabel2)
                {
                    AppShopAdmin app=new AppShopAdmin();
                    app.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                InfoLabel2.setForeground(new Color(0, 0, 0,243));//字颜色
            }

            @Override
            public void mouseExited(MouseEvent e) {
                InfoLabel2.setForeground(new Color(0, 0, 0,150));//字颜色
            }
        });
        add(InfoLabel2);//先添加的label在最上层



        JLabel Label2 = new JLabel("");
        Label2.setHorizontalAlignment(SwingConstants.CENTER);
        Label2.setOpaque(true);
        Label2.setBackground(new Color(255,255,255,90));
        Label2.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                new Color(255,255,255,255)));
        Label2.setBounds(985, 580, 340, 160);
        add(Label2);//先添加的label在最上层


        JLabel InfoLabel2_2 = new JLabel("报名管理");
        InfoLabel2_2.setFont(new Font("微软雅黑", Font.BOLD, 18));
        InfoLabel2_2.setForeground(new Color(0, 0, 0,150));//字颜色
        InfoLabel2_2.setHorizontalAlignment(SwingConstants.CENTER);
        InfoLabel2_2.setBounds(815, 580, 170, 160);
        InfoLabel2_2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()== InfoLabel2_2)
                {
                    AppShopAdmin app=new AppShopAdmin();
                    app.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                InfoLabel2_2.setForeground(new Color(0, 0, 0,243));//字颜色
            }

            @Override
            public void mouseExited(MouseEvent e) {
                InfoLabel2_2.setForeground(new Color(0, 0, 0,150));//字颜色
            }
        });
        add(InfoLabel2_2);//先添加的label在最上层



        JLabel Label2_2 = new JLabel("");
        Label2_2.setHorizontalAlignment(SwingConstants.CENTER);
        Label2_2.setOpaque(true);
        Label2_2.setBackground(new Color(255,255,255,90));
        Label2_2.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                new Color(255,255,255,255)));
        Label2_2.setBounds(815, 580, 170, 160);
        add(Label2_2);//先添加的label在最上层


        JLabel bg2 = new JLabel(new ImageIcon(getClass().getResource("/resources/assets/bgnew/1022.png")));
        add(bg2);
        bg2.setBounds(820, 585, 500, 150);




        JLabel InfoLabel3 = new JLabel("院校分数管理");
        InfoLabel3.setFont(new Font("微软雅黑", Font.BOLD, 18));
        InfoLabel3.setForeground(new Color(0, 0, 0,150));//字颜色
        InfoLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        InfoLabel3.setBounds(360, 500, 410, 240);
        InfoLabel3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()== InfoLabel3)
                {
                    AppCollegeAdmin app=new AppCollegeAdmin();
                    app.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                InfoLabel3.setForeground(new Color(0, 0, 0,243));//字颜色
            }

            @Override
            public void mouseExited(MouseEvent e) {
                InfoLabel3.setForeground(new Color(0, 0, 0,150));//字颜色
            }
        });
        add(InfoLabel3);//先添加的label在最上层

        JLabel Lable3 = new JLabel("");
        Lable3.setHorizontalAlignment(SwingConstants.CENTER);
        Lable3.setOpaque(true);
        Lable3.setBackground(new Color(255,255,255,90));
        Lable3.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                new Color(255,255,255,255)));
        Lable3.setBounds(360, 500, 410, 240);
        add(Lable3);//先添加的label在最上层



        JLabel bg3 = new JLabel(new ImageIcon(getClass().getResource("/resources/assets/bgnew/102.png")));
        add(bg3);
        bg3.setBounds(195, 505, 570, 230);





        JLayeredPane self=new JLayeredPane();
        self.setBounds(70,50,820,600);

        avatarPhoto.setIcon(new ImageIcon(getClass().getResource("/resources/assets/bgnew/1.jpg")));
        avatarPhoto.setBounds(120,17,600,400);
        avatarPhoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://yz.chsi.com.cn/"));
                } catch (Exception l){
                    l.printStackTrace();
                }
            }
        });

        Timer timer=new Timer(5000,new AppAdmin.TimeListener());
        timer.start();

        self.add(avatarPhoto,2);
        contentPane.add(self);
    }

}