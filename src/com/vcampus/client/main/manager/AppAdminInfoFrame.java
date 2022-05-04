package com.vcampus.client.main.manager;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 */
public class AppAdminInfoFrame extends JFrame {
    private JPanel contentPane;
    private int width = 1151;
    private int height = 800;

    public AppAdminInfoFrame()
    {
        setResizable(true);//允许窗口大小更改
        setTitle("个人信息管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1400, d.height);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 255, 240));
        contentPane.setLocation(-871, -176);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

//        JTree jt= new ManCategory().getJTree();
//        jt.setBounds(0,50,200,600);
//        contentPane.add(jt);//设置目录

        JButton returnButton = new JButton("← 返回");
        returnButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        returnButton.setBounds(220,5,80,30);
        returnButton.setForeground(new Color(33, 117, 206,100));
        contentPane.add(returnButton);
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==returnButton)
                {
                    setVisible(false);
                }
            }
        });

        //绘制panel
        AdminInfoPanel AdminInfoPanel =new AdminInfoPanel();
        AdminInfoPanel.setBounds(-150,0,1450,800);
        contentPane.add(AdminInfoPanel);



    }
}
