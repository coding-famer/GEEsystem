package com.vcampus.client.main.manager;

import com.vcampus.client.main.shop.AppShopAdmin;
import com.vcampus.client.main.manager.AdminInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 */
public class AdminPanel extends JPanel {

    public AdminPanel(){

        setLayout(null);


        JLabel PersonInfoLabel1 = new JLabel("个人信息管理");//文字框
        PersonInfoLabel1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        PersonInfoLabel1.setForeground(new Color(0, 0, 0,150));//字颜色
        PersonInfoLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        PersonInfoLabel1.setBounds(685, 230, 170, 170);
        PersonInfoLabel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==PersonInfoLabel1)
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
                PersonInfoLabel1.setForeground(new Color(0, 0, 0, 243));//字颜色

            }

            @Override
            public void mouseExited(MouseEvent e) {
                PersonInfoLabel1.setForeground(new Color(0, 0, 0,150));//字颜色
            }
        });
       add(PersonInfoLabel1);

        JLabel PersonInfoLabel = new JLabel("");//透明框
        PersonInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PersonInfoLabel.setOpaque(true);
        PersonInfoLabel.setBackground(new Color(255,255,255,90));
        PersonInfoLabel.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                new Color(255,255,255,255)));
        PersonInfoLabel.setBounds(685, 230, 170, 170);
        add(PersonInfoLabel);//先添加的label在最上层


        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/resources/assets/AdminImage/bg.jpg")));
        bg.setPreferredSize(new Dimension(400,400));
        add(bg);
        bg.setBounds(520, 65, 500, 500);
        //bg.setBorder(BorderFactory.createMatteBorder(5,5,5,5,new Color(255,255,255,80)));

        JLabel ShopInfoLabel1 = new JLabel("书店管理");
        ShopInfoLabel1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        ShopInfoLabel1.setForeground(new Color(0, 0, 0,150));//字颜色
        ShopInfoLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        ShopInfoLabel1.setBounds(685, 580, 340, 160);
        ShopInfoLabel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()== ShopInfoLabel1)
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
                ShopInfoLabel1.setForeground(new Color(0, 0, 0,243));//字颜色
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ShopInfoLabel1.setForeground(new Color(0, 0, 0,150));//字颜色
            }
        });
       add(ShopInfoLabel1);//先添加的label在最上层

        JLabel ShopInfoLabel = new JLabel("");
        ShopInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ShopInfoLabel.setOpaque(true);
        ShopInfoLabel.setBackground(new Color(255,255,255,90));
        ShopInfoLabel.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                new Color(255,255,255,255)));
        ShopInfoLabel.setBounds(685, 580, 340, 160);
        add(ShopInfoLabel);//先添加的label在最上层

        JLabel bg2 = new JLabel(new ImageIcon(getClass().getResource("/resources/assets/AdminImage/bg2.jpg")));
        add(bg2);
        bg2.setBounds(520, 585, 500, 150);

    }


    private class MyButtonListener implements ActionListener {//监听器类
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("button");
        }

    }
}
