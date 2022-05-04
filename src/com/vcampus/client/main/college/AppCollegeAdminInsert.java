package com.vcampus.client.main.college;

import com.alee.managers.style.StyleId;
import com.vcampus.client.main.App;
import com.vcampus.client.main.AppLifeHelper;
import com.vcampus.entity.College;
import com.vcampus.entity.DealHistory;
import com.vcampus.net.Request;
import com.vcampus.util.ResponseUtils;
import com.vcampus.util.SwingUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AppCollegeAdminInsert extends JFrame{

    private static JPanel contentPane;
    public  AppCollegeAdminInsert(){
        setResizable(true);//允许窗口大小更改
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/assets/icon/graLogo.png")));
        setTitle("院校分数信息录入");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(600, 800);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setLocation(500, 300);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton returnButton = new JButton("← 返回");//返回按钮
        returnButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        returnButton.setBounds(10,5,80,30);
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

        JTextField txt_1 = new JTextField();
        txt_1.setBounds(300, 100, 170, 40);
        txt_1.setEditable(true);
        txt_1.setText("");
        txt_1.setForeground(Color.GRAY);
        this.contentPane.add(txt_1);
        JTextField txt_2 = new JTextField();
        txt_2.setBounds(300, 160, 170, 40);
        txt_2.setEditable(true);
        txt_2.setText("");
        txt_2.setForeground(Color.GRAY);
        this.contentPane.add(txt_2);
        JTextField txt_3 = new JTextField();
        txt_3.setBounds(300, 220, 170, 40);
        txt_3.setEditable(true);
        txt_3.setText("");
        txt_3.setForeground(Color.GRAY);
        this.contentPane.add(txt_3);
        JTextField txt_4 = new JTextField();
        txt_4.setBounds(300, 280, 170, 40);
        txt_4.setEditable(true);
        txt_4.setText("");
        txt_4.setForeground(Color.GRAY);
        this.contentPane.add(txt_4);
        JTextField txt_5 = new JTextField();
        txt_5.setBounds(300, 340, 170, 40);
        txt_5.setEditable(true);
        txt_5.setText("");
        txt_5.setForeground(Color.GRAY);
        this.contentPane.add(txt_5);
        JTextField txt_6 = new JTextField();
        txt_6.setBounds(300, 400, 170, 40);
        txt_6.setEditable(true);
        txt_6.setText("");
        txt_6.setForeground(Color.GRAY);
        this.contentPane.add(txt_6);
        JTextField txt_7 = new JTextField();
        txt_7.setBounds(300, 460, 170, 40);
        txt_7.setEditable(true);
        txt_7.setText(String.valueOf(""));
        txt_7.setForeground(Color.GRAY);
        this.contentPane.add(txt_7);
        final JTextField txt_8 = new JTextField();
        txt_8.setBounds(300, 520, 170, 40);
        txt_8.setEditable(true);
        txt_8.setText("");
        txt_8.setForeground(Color.GRAY);
        this.contentPane.add(txt_8);

        JLabel label = new JLabel("院校编号：");
        label.setFont(new Font("微软雅黑", 1, 18));
        label.setOpaque(true);
        label.setForeground(new Color(0,0,0));
        label.setBackground(new Color(255, 255, 255, 80));
        label.setBounds(115, 100, 100, 40);
        this.contentPane.add(label);
        JLabel label_1 = new JLabel("院校名称：");
        label_1.setFont(new Font("微软雅黑", 1, 18));
        label_1.setOpaque(true);
        label_1.setForeground(new Color(0,0,0));
        label_1.setBackground(new Color(255, 255, 255, 80));
        label_1.setBounds(115, 160, 100, 40);
        this.contentPane.add(label_1);
        JLabel label_2 = new JLabel("专业编号：");
        label_2.setFont(new Font("微软雅黑", 1, 18));
        label_2.setOpaque(true);
        label_2.setForeground(new Color(0,0,0));
        label_2.setBackground(new Color(255, 255, 255, 80));
        label_2.setBounds(115, 220, 100, 40);
        this.contentPane.add(label_2);
        JLabel label_3 = new JLabel("学院名称：");
        label_3.setFont(new Font("微软雅黑", 1, 18));
        label_3.setOpaque(true);
        label_3.setForeground(new Color(0,0,0));
        label_3.setBackground(new Color(255, 255, 255, 80));
        label_3.setBounds(115, 280, 100, 40);
        this.contentPane.add(label_3);
        JLabel label_4 = new JLabel("22分数线：");
        label_4.setFont(new Font("微软雅黑", 1, 18));
        label_4.setOpaque(true);
        label_4.setForeground(new Color(0,0,0));
        label_4.setBackground(new Color(255, 255, 255, 80));
        label_4.setBounds(115, 340, 100, 40);
        this.contentPane.add(label_4);
        JLabel label_5 = new JLabel("21分数线：");
        label_5.setFont(new Font("微软雅黑", 1, 18));
        label_5.setOpaque(true);
        label_5.setForeground(new Color(0,0,0));
        label_5.setBackground(new Color(255, 255, 255, 80));
        label_5.setBounds(115, 400, 100, 40);
        this.contentPane.add(label_5);
        JLabel label_6 = new JLabel("20分数线：");
        label_6.setFont(new Font("微软雅黑", 1, 18));
        label_6.setOpaque(true);
        label_6.setForeground(new Color(0,0,0));
        label_6.setBackground(new Color(255, 255, 255, 80));
        label_6.setBounds(115, 460, 100, 40);
        this.contentPane.add(label_6);
        JLabel label_7 = new JLabel("专业类号：");
        label_7.setFont(new Font("微软雅黑", 1, 18));
        label_7.setOpaque(true);
        label_7.setForeground(new Color(0,0,0));
        label_7.setBackground(new Color(255, 255, 255, 80));
        label_7.setBounds(115, 520, 100, 40);
        this.contentPane.add(label_7);

        JButton insertCollegeInfo = new JButton("录入");
        insertCollegeInfo.setFont((new Font("微软雅黑", Font.PLAIN, 16)));
        insertCollegeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cid = txt_1.getText().trim();
                String cname = txt_2.getText().trim();
                String mid = txt_3.getText().trim();
                String mname = txt_4.getText().trim();
                if(txt_5.getText().trim().equals("")||txt_6.getText().trim().equals("")||txt_7.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "项不能为空或不符合格式！");
                    return;
                }
                int entry2022 = Integer.parseInt(txt_5.getText().trim());
                int entry2021 = Integer.parseInt(txt_6.getText().trim());
                int entry2020 = Integer.parseInt(txt_7.getText().trim());
                String mcatid = txt_8.getText().trim();
                if(cid.equals("")||cname.equals("")||mid.equals("")||mname.equals("")||mcatid.equals("")){
                    JOptionPane.showMessageDialog(null, "项不能为空或不符合格式！");
                    return;
                }
                else{
                    HashMap<String,Object> insertInfo = new HashMap<String,Object>();
                    insertInfo.put("cid",cid);
                    insertInfo.put("cname",cname);
                    insertInfo.put("mid",mid);
                    insertInfo.put("mname",mname);
                    insertInfo.put("entry2022",entry2022);
                    insertInfo.put("entry2021",entry2021);
                    insertInfo.put("entry2020",entry2020);
                    insertInfo.put("mcatid",mcatid);
                    Boolean flag = ResponseUtils
                            .getResponseByHash(new Request(App.connectionToServer, null, "com.vcampus.server.college.CollegeServer.insertInfo",
                                    new Object[]{insertInfo}).send())
                            .getReturn(Boolean.class);
                    if(flag){
                        JOptionPane.showMessageDialog(null, "录入成功！");
                    }
                }

            }
        });
        contentPane.add(insertCollegeInfo);
        insertCollegeInfo.setBounds(200, 600, 180, 30);


    }

}
