package com.vcampus.client.main.college;

import com.alee.managers.style.StyleId;
import com.vcampus.client.main.App;
import com.vcampus.client.main.AppLifeHelper;
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

public class AppCollegeAdmin extends JFrame{
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
    public AppCollegeAdmin(){

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

                }
            }
        });

        DefaultTableCellRenderer rGoodsList = new DefaultTableCellRenderer();
        rGoodsList.setHorizontalAlignment(JLabel.CENTER);
        tblInfoList.setDefaultRenderer(Object.class, rGoodsList);
        JScrollPane scrollPane=new JScrollPane(tblInfoList);
        scrollPane.setBounds(380, 170, 800, 300);
        contentPane.add(scrollPane);

        JButton insertCollegeInfo = new JButton("新增院校信息");
        insertCollegeInfo.setFont((new Font("微软雅黑", Font.PLAIN, 16)));
        insertCollegeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppCollegeAdminInsert app =new AppCollegeAdminInsert();
                app.setVisible(true);
            }
        });
        contentPane.add(insertCollegeInfo);
        insertCollegeInfo.setBounds(680, 550, 180, 30);

        JButton updateCollegeInfo = new JButton("修改院校信息");
        updateCollegeInfo.setFont((new Font("微软雅黑", Font.PLAIN, 16)));
        updateCollegeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppCollegeAdminUpdate app = new AppCollegeAdminUpdate();
                app.setVisible(true);
            }
        });
        contentPane.add(updateCollegeInfo);
        updateCollegeInfo.setBounds(680, 600, 180, 30);

        JButton deleteCollegeInfo = new JButton("删除院校信息");
        deleteCollegeInfo.setFont((new Font("微软雅黑", Font.PLAIN, 16)));
        deleteCollegeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppCollegeAdminDelete app =new AppCollegeAdminDelete();
                app.setVisible(true);
            }
        });
        contentPane.add(deleteCollegeInfo);
        deleteCollegeInfo.setBounds(680, 650, 180, 30);
    }
}
