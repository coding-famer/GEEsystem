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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AppCollegeAdminUpdate extends JFrame {
    private static JPanel contentPane;
    private static List<College> list;


    public AppCollegeAdminUpdate() {
        setResizable(true);//允许窗口大小更改
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/assets/icon/graLogo.png")));
        setTitle("院校分数信息修改");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(600, 550);
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

        list = ResponseUtils
                .getResponseByHash(new Request(App.connectionToServer, null,
                        "com.vcampus.server.college.CollegeServer.listAllInfo", new Object[]{}).send())
                .getListReturn(College.class);

        ArrayList<String> cm = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String display = list.get(i).cname + " " + list.get(i).mname;
            cm.add(display);
        }

        JLabel lblCM = new JLabel("需修改项");
        lblCM.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        lblCM.setHorizontalAlignment(SwingConstants.CENTER);
        lblCM.setBounds(70, 90, 100, 40);
        contentPane.add(lblCM);

        JComboBox cmbCM = new JComboBox();
        for(int i = 0; i<cm.size();i++){
            cmbCM.addItem(cm.get(i));
        }
        cmbCM.setBounds(220,97,250,30);
        contentPane.add(cmbCM);

        JLabel label_4 = new JLabel("22分数线：");
        label_4.setFont(new Font("微软雅黑", 1, 18));
        label_4.setOpaque(true);
        label_4.setForeground(new Color(0,0,0));
        label_4.setBackground(new Color(255, 255, 255, 80));
        label_4.setBounds(115, 160, 100, 40);
        this.contentPane.add(label_4);
        JLabel label_5 = new JLabel("21分数线：");
        label_5.setFont(new Font("微软雅黑", 1, 18));
        label_5.setOpaque(true);
        label_5.setForeground(new Color(0,0,0));
        label_5.setBackground(new Color(255, 255, 255, 80));
        label_5.setBounds(115, 220, 100, 40);
        this.contentPane.add(label_5);
        JLabel label_6 = new JLabel("20分数线：");
        label_6.setFont(new Font("微软雅黑", 1, 18));
        label_6.setOpaque(true);
        label_6.setForeground(new Color(0,0,0));
        label_6.setBackground(new Color(255, 255, 255, 80));
        label_6.setBounds(115, 280, 100, 40);
        this.contentPane.add(label_6);


        JTextField txt_4 = new JTextField();
        txt_4.setBounds(300, 160, 170, 40);
        txt_4.setEditable(true);
        txt_4.setText("");
        txt_4.setForeground(Color.GRAY);
        this.contentPane.add(txt_4);
        JTextField txt_5 = new JTextField();
        txt_5.setBounds(300, 220, 170, 40);
        txt_5.setEditable(true);
        txt_5.setText("");
        txt_5.setForeground(Color.GRAY);
        this.contentPane.add(txt_5);
        JTextField txt_6 = new JTextField();
        txt_6.setBounds(300, 280, 170, 40);
        txt_6.setEditable(true);
        txt_6.setText(String.valueOf(""));
        txt_6.setForeground(Color.GRAY);
        this.contentPane.add(txt_6);

        JButton insertCollegeInfo = new JButton("修改");
        insertCollegeInfo.setFont((new Font("微软雅黑", Font.PLAIN, 16)));
        insertCollegeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = cmbCM.getSelectedIndex();
                if(txt_4.getText().trim().equals("")||txt_5.getText().trim().equals("")||txt_6.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "项不能为空或不符合格式！");
                    return;
                }
                String cname = list.get(i).cname;
                String mname = list.get(i).mname;
                int entry2022 = Integer.parseInt(txt_4.getText().trim());
                int entry2021 = Integer.parseInt(txt_5.getText().trim());
                int entry2020 = Integer.parseInt(txt_6.getText().trim());
                HashMap<String,Object> changeInfo = new HashMap<String,Object>();
                changeInfo.put("cname",cname);
                changeInfo.put("mname",mname);
                changeInfo.put("entry2022",entry2022);
                changeInfo.put("entry2021",entry2021);
                changeInfo.put("entry2020",entry2020);
                Boolean flag = ResponseUtils
                        .getResponseByHash(new Request(App.connectionToServer, null, "com.vcampus.server.college.CollegeServer.changeInfo",
                                new Object[]{changeInfo}).send())
                        .getReturn(Boolean.class);
                if(flag) {
                    JOptionPane.showMessageDialog(null, "修改成功！");
                }

            }
        });
        contentPane.add(insertCollegeInfo);
        insertCollegeInfo.setBounds(200, 370, 180, 30);


    }
}
