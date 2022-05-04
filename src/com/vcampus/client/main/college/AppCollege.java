package com.vcampus.client.main.college;

import com.alee.managers.style.StyleId;
import com.vcampus.client.main.App;
import com.vcampus.entity.College;
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

public class AppCollege extends JFrame{

    private static String selectedCollege = "所有院校";
    private static String selectedMajor = "所有专业";

    private static JPanel contentPane;
    private static List<College> list;
    private static JTable tblInfoList;
    private static DefaultTableModel tableModel;



    private static JLabel infoPic;

    private static JTextArea infoDetail;

    public void handleCategorySelection(String college, String major) {
        tableModel.setRowCount(0);
        if (college.equals("所有院校") && major.equals("所有专业")) {
            list = ResponseUtils
                    .getResponseByHash(new Request(App.connectionToServer, null,
                            "com.vcampus.server.college.CollegeServer.listAllInfo", new Object[]{}).send())
                    .getListReturn(College.class);
        }
        else if(!(college.equals("所有院校"))&&!(major.equals("所有专业"))){
            HashMap<String,Object> conditionSelected = new HashMap<String,Object>();
            conditionSelected.put("college",selectedCollege);
            conditionSelected.put("major",selectedMajor);

            System.out.println(conditionSelected);
            list = ResponseUtils
                    .getResponseByHash(new Request(App.connectionToServer, null,
                            "com.vcampus.server.college.CollegeServer.listInfo", new Object[]{conditionSelected}).send())
                    .getListReturn(College.class);
        }
        else if(college.equals("所有院校")&&!(major.equals("所有专业"))){
            list = ResponseUtils
                    .getResponseByHash(new Request(App.connectionToServer, null,
                            "com.vcampus.server.college.CollegeServer.listInfoOnlyByMajor", new Object[]{selectedMajor}).send())
                    .getListReturn(College.class);
        }
        else if(!(college.equals("所有院校"))&&major.equals("所有专业")){
            list = ResponseUtils
                    .getResponseByHash(new Request(App.connectionToServer, null,
                            "com.vcampus.server.college.CollegeServer.listInfoOnlyByCollege", new Object[]{selectedCollege}).send())
                    .getListReturn(College.class);
        }

        if (list == null) {
            SwingUtils.showMessage(null, "ERROR", "提示");
        } else {
            for (int i = 0; i < list.size(); i++) {
                tableModel.addRow(new Object[]{
                        list.get(i).cname,
                        list.get(i).mname,
                        list.get(i).entry2022,
                        list.get(i).entry2021,
                        list.get(i).entry2020
                });
            }
        }
    }

    public AppCollege() {
        setResizable(true);//允许窗口大小更改
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/assets/icon/graLogo.png")));
        setTitle("院校分数信息查询");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d.width, d.height);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setLocation(-871, -176);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        tableModel = new DefaultTableModel();
        String[] columnNames={
                "院校名称",
                "专业名称",
                "2022年复试分数线",
                "2021年复试分数线",
                "2020年复试分数线",
        };
        String[][] tableValues = {};
        tableModel=new DefaultTableModel(tableValues, columnNames);

        handleCategorySelection(selectedCollege,selectedMajor);

        JButton btnBack = new JButton("返回");
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==btnBack)
                {
                    setVisible(false);
                }
            }
        });
        btnBack.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        btnBack.setBounds(1250, 30, 100, 40);
        contentPane.add(btnBack);

        JLabel lblCollegeName = new JLabel("学校名称");
        lblCollegeName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        lblCollegeName.setHorizontalAlignment(SwingConstants.CENTER);
        lblCollegeName.setBounds(490, 80, 100, 40);
        contentPane.add(lblCollegeName);

        JComboBox cmbCollegeName = new JComboBox();
        cmbCollegeName.addItem("所有院校");
        cmbCollegeName.addItem("北京大学");
        cmbCollegeName.addItem("清华大学");
        cmbCollegeName.addItem("中国人民大学");
        cmbCollegeName.addItem("上海交通大学");
        cmbCollegeName.addItem("南京大学");
        cmbCollegeName.addItem("东南大学");
        cmbCollegeName.setBounds(600, 87, 100, 30);
        contentPane.add(cmbCollegeName);
        cmbCollegeName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(ItemEvent.SELECTED == e.getStateChange()){
                    selectedCollege = e.getItem().toString();
                    handleCategorySelection(selectedCollege,selectedMajor);
                }
            }

        });

        JLabel lblMajorName = new JLabel("专业名称");
        lblMajorName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        lblMajorName.setHorizontalAlignment(SwingConstants.CENTER);
        lblMajorName.setBounds(800, 80, 100, 40);
        contentPane.add(lblMajorName);

        JComboBox cmbMajorName = new JComboBox();
        cmbMajorName.addItem("所有专业");
        cmbMajorName.addItem("计算机类");
        cmbMajorName.addItem("经济类");
        cmbMajorName.setBounds(910, 87, 100, 30);
        contentPane.add(cmbMajorName);
        cmbMajorName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(ItemEvent.SELECTED == e.getStateChange()){
                    selectedMajor = e.getItem().toString();
                    if(selectedMajor.equals("计算机类"))
                        selectedMajor= String.valueOf(0);
                    else if(selectedMajor.equals("经济类"))
                        selectedMajor= String.valueOf(1);
                    handleCategorySelection(selectedCollege,selectedMajor);
                }
            }
        });



        infoPic = new JLabel();
        infoPic.setBounds(500,550,100,100);
        infoPic.setIcon(new ImageIcon(getClass().getResource("/resources/assets/collegeicon/1.jpg")));
        contentPane.add(infoPic);

        infoDetail = new JTextArea("详细介绍在此处", 8, 30);
        infoDetail.setLineWrap(true);
        infoDetail.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        infoDetail.setBounds(700, 500, 300, 500);
        contentPane.add(infoDetail);


        tblInfoList = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        tblInfoList.putClientProperty ( StyleId.STYLE_PROPERTY, "table" );
        tblInfoList.setAutoResizeMode ( JTable.AUTO_RESIZE_ALL_COLUMNS );
        tblInfoList.setRowSelectionAllowed(true);
        tblInfoList.setCellSelectionEnabled(false);
        tblInfoList.setRowHeight(40);
        tblInfoList.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        tblInfoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblInfoList.setPreferredScrollableViewportSize(new Dimension(200, 100));
        tblInfoList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tblInfoList.getSelectedRow();
                if(row != -1)
                {
                    BufferedImage img = null;
                    try {img = ImageIO.read(getClass().getResource("/resources/assets/collegeicon/"
                            + list.get(row).cname + ".png"));
                    } catch (IOException ee) {
                        ee.printStackTrace();
                    }
                    Image dimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

                    infoPic.setIcon(new ImageIcon(dimg));

                    if(list.get(row).cname.equals("北京大学"))
                        AppCollege.infoDetail.setText("北京大学是中华人民共和国最顶尖的高校之一，现时属于“QS世界百强大学”及“泰晤士高等教育世界百强大学”，是“双一流A类”和原“985工程”、原“211工程”重点建设大学。如今，北京大学是一所兼具自然科学、人文学科、社会科学、医学以及新型工科的综合性大学，也是大中华地区和亚太地区综合实力最强的高等教育机构之一。");
                    else if(list.get(row).cname.equals("清华大学"))
                        AppCollege.infoDetail.setText("清华大学是中华人民共和国最顶尖的高校之一，现时属于“QS世界百强大学”及“泰晤士高等教育世界百强大学”，是“双一流A类”和原“985工程”、原“211工程”重点建设大学。截至2020年12月，清华大学拥有美术馆、博物馆、图书馆、21个学院，及近200个科研机构、5家校办产业以及一个科技园区。学校拥有固定资产超过240.2亿元人民币，控股资产超过4300亿元人民币。");
                    else if(list.get(row).cname.equals("中国人民大学"))
                        AppCollege.infoDetail.setText("中国人民大学是中华人民共和国顶尖高校之一，是“双一流A类”和原“985工程”、原“211工程”重点建设大学。如今，中国人民大学是属于中国著名代表高校，是当代中国人文社会科学高等教育和研究的重要基地，被誉为“中国人文社会科学的一面旗帜”。");
                    else if(list.get(row).cname.equals("上海交通大学"))
                        AppCollege.infoDetail.setText("上海交通大学是中华人民共和国最顶尖的高校之一，现时属于“QS世界百强大学”及“泰晤士高等教育世界百强大学”，是“双一流A类”和原“985工程”、原“211工程”重点建设大学。上海交大是近代中国历史最为悠久的大学之一。");
                    else if(list.get(row).cname.equals("南京大学"))
                        AppCollege.infoDetail.setText("南京大学是中华人民共和国顶尖高校之一，是“双一流A类”和原“985工程”、原“211工程”重点建设大学，为基础学科拔尖学生培养试验计划、九校联盟、中国大学校长联谊会、环太平洋大学联盟、21世纪学术联盟、国际应用科技开发协作网、东亚研究型大学协会、新工科教育国际联盟、中国高校行星科学联盟、长三角研究型大学联盟成员。");
                    else if(list.get(row).cname.equals("东南大学"))
                        AppCollege.infoDetail.setText("东南大学是中华人民共和国顶尖高校之一，是“双一流A类”和原“985工程”、原“211工程”重点建设大学，同时是“2011计划”、“111计划”入选高校，也是“卓越大学联盟”、“卓越工程师教育培养计划”、“卓越医生教育培养计划”、“一带一路”高校联盟、“中英大学工程教育与研究联盟”、“中俄工科大学联盟”、“中欧工程教育平台”、“长三角高校合作联盟”主要成员。");


                }
            }
        });

        DefaultTableCellRenderer rGoodsList = new DefaultTableCellRenderer();
        rGoodsList.setHorizontalAlignment(JLabel.CENTER);
        tblInfoList.setDefaultRenderer(Object.class, rGoodsList);
        JScrollPane scrollPane=new JScrollPane(tblInfoList);
        scrollPane.setBounds(380, 170, 800, 300);
        contentPane.add(scrollPane);

    }
}
