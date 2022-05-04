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

public class AppCollegeAdminDelete extends JFrame{
    private static JPanel contentPane;
    private static List<College> list;
    public AppCollegeAdminDelete(){
        setResizable(true);//允许窗口大小更改
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/assets/icon/graLogo.png")));
        setTitle("院校分数信息修改");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(600, 250);
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
        lblCM.setBounds(70, 50, 100, 40);
        contentPane.add(lblCM);

        JComboBox cmbCM = new JComboBox();
        for(int i = 0; i<cm.size();i++){
            cmbCM.addItem(cm.get(i));
        }
        cmbCM.setBounds(220,57,250,30);
        contentPane.add(cmbCM);

        JButton insertCollegeInfo = new JButton("删除");
        insertCollegeInfo.setFont((new Font("微软雅黑", Font.PLAIN, 16)));
        insertCollegeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = cmbCM.getSelectedIndex();
                String cname = list.get(i).cname;
                String mname = list.get(i).mname;
                HashMap<String,Object> changeInfo = new HashMap<String,Object>();
                changeInfo.put("cname",cname);
                changeInfo.put("mname",mname);
                Boolean flag = ResponseUtils
                        .getResponseByHash(new Request(App.connectionToServer, null, "com.vcampus.server.college.CollegeServer.deleteInfo",
                                new Object[]{changeInfo}).send())
                        .getReturn(Boolean.class);
                if(flag) {
                    JOptionPane.showMessageDialog(null, "删除成功！");
                }

            }
        });
        contentPane.add(insertCollegeInfo);
        insertCollegeInfo.setBounds(200, 140, 180, 30);
    }
}
