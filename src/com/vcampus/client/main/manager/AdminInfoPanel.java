package com.vcampus.client.main.manager;

//import com.mysql.cj.xdevapi.Client;

import com.vcampus.client.main.App;
import com.vcampus.client.main.student.AppStudent;
import com.vcampus.client.main.student.StudentInfo.AppStudentInfoHelper;
import com.vcampus.util.StringUtils;
import com.vcampus.util.imageIO.ByteArray;
import com.vcampus.util.imageIO.Client;
import com.vcampus.util.imageIO.MessageForImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 管理员个人信息界面
 */
public class AdminInfoPanel extends JFrame {
    private JPanel contentPane;

    public AdminInfoPanel() {
        setResizable(true);//允许窗口大小更改
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/assets/icon/graLogo.png")));
        setTitle("管理员个人信息管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d.width, d.height);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setLocation(-871, -176);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        String AdminName = App.session.getAdmin().getName();//获取姓名
        String AdminCardNumber = App.session.getAdmin().getCardNumber();//获取一卡通号
        String AdminGender = App.session.getAdmin().getGender();//获取性别
        String AdminEmail = App.session.getAdmin().getEmail();//获取邮箱
        String AdminPassword = App.session.getAdmin().getPassword();//获取登录密码
        String AdminPhoneNumber = App.session.getAdmin().getPhoneNumber();//获取电话号码


        JButton returnButton = new JButton("← 返回");//返回按钮
        returnButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        returnButton.setBounds(220,5,80,30);
        returnButton.setForeground(new Color(33, 117, 206,100));
        contentPane.add(returnButton);


        JButton EditButton = new JButton("启动编辑");//编辑按钮
        EditButton.setFont(new Font("微软雅黑", Font.BOLD, 17));
        EditButton.setBounds(1150,695,90,40);
        EditButton.setForeground(new Color(0,100,100,250));
        contentPane.add(EditButton);

        JLabel HeadPortrait= new JLabel(new ImageIcon(getClass().getResource("/resources/assets/Student/StuInfo.jpg")));
        HeadPortrait.setOpaque(true);
        HeadPortrait.setBorder(BorderFactory.createMatteBorder(8,8,8,8,
                new Color(255, 255, 255, 255)));
        HeadPortrait.setBounds(610, 80, 280, 280);

        JLabel headPortraitTransLabelBg = new JLabel("");//透明框
        headPortraitTransLabelBg.setHorizontalAlignment(SwingConstants.CENTER);
        headPortraitTransLabelBg.setOpaque(true);
        headPortraitTransLabelBg.setBackground(new Color(255,255,255,0));
        headPortraitTransLabelBg.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                new Color(255,255,255,255)));
        headPortraitTransLabelBg.setBounds(610, 80, 280, 280);

        JLabel headPortraitTransLabel = new JLabel("上传头像");//文字框
        headPortraitTransLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        headPortraitTransLabel.setForeground(new Color(0, 0, 0,0));//字颜色
        headPortraitTransLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headPortraitTransLabel.setBounds(610, 80, 280, 280);

        contentPane.add(headPortraitTransLabel);
        contentPane.add(headPortraitTransLabelBg);//先添加的label在最上层
        contentPane.add(HeadPortrait);
        headPortraitTransLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==headPortraitTransLabel)
                {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setSize(510, 327);
                    chooser.setFileFilter(new FileNameExtensionFilter("JPG","jpg"));
                    String imgPath = null;
                    int returnVal = chooser.showOpenDialog(contentPane);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        imgPath = chooser.getSelectedFile().getAbsolutePath();
                    }
                    ByteArray byteArray = new ByteArray();
                    byte[] imageData = null;
                    try {
                        BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(image, "jpg", baos);
                        imageData = baos.toByteArray();
                        byteArray.setImageData(imageData);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    MessageForImage senderMessage = new MessageForImage(AdminCardNumber);
                    senderMessage.setByteArray(byteArray);
                    try {
                        MessageForImage messageBack =  new Client().start(senderMessage);
                        //更新界面的图片
                        HeadPortrait.setIcon(new ImageIcon(imgPath));

                        //validate();
                        contentPane.repaint();//重新绘制实现刷新


                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

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
                headPortraitTransLabel.setForeground(new Color(0, 0, 0, 243));//字颜色
                headPortraitTransLabelBg.setBackground(new Color(255,255,255,90));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                headPortraitTransLabel.setForeground(new Color(0, 0, 0,0));//字颜色
                headPortraitTransLabelBg.setBackground(new Color(255,255,255,0));

            }
        });




        JButton SaveButton = new JButton("保存");//保存按钮
        SaveButton.setFont(new Font("微软雅黑", Font.BOLD, 17));
        SaveButton.setBounds(1270,695,60,40);
        SaveButton.setForeground(new Color(0,100,100,250));
        contentPane.add(SaveButton);


        //输入框
        JTextField txt_1=new JTextField();
        txt_1.setBounds(763,400,170,40);
        txt_1.setEditable(false);
        txt_1.setText(AdminName);
        txt_1.setForeground(Color.GRAY);
        add(txt_1);

        JTextField txt_2=new JTextField();
        txt_2.setBounds(763,460,170,40);
        txt_2.setEditable(false);
        txt_2.setText(AdminCardNumber);
        txt_2.setForeground(Color.GRAY);
        add(txt_2);

        JTextField txt_3=new JTextField();
        txt_3.setBounds(763,520,170,40);
        txt_3.setEditable(false);
        txt_3.setText(AdminGender);
        txt_3.setForeground(Color.GRAY);
        add(txt_3);

        JTextField txt_4=new JTextField();
        txt_4.setBounds(763,580,170,40);
        txt_4.setEditable(false);
        txt_4.setText(AdminPhoneNumber);
        txt_4.setForeground(Color.GRAY);
        add(txt_4);

        JTextField txt_5=new JTextField();
        txt_5.setBounds(763,640,170,40);
        txt_5.setEditable(false);
        txt_5.setText(AdminEmail);
        txt_5.setForeground(Color.GRAY);
        add(txt_5);

        JTextField txt_6=new JTextField();
        txt_6.setBounds(763,700,170,40);
        txt_6.setEditable(false);
        txt_6.setText("输入重置密码");
        txt_6.setForeground(Color.GRAY);
        add(txt_6);


        JLabel label = new JLabel("姓名：");
        label.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label.setOpaque(true);
        label.setForeground(new Color(19, 188, 210));
        label.setBackground(new Color(255,255,255,80));
        label.setBounds(578, 400, 100, 40);
        add(label);

        JLabel label_1 = new JLabel("一卡通号：");
        label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label_1.setOpaque(true);
        label_1.setForeground(new Color(19, 188, 210));
        label_1.setBackground(new Color(255,255,255,80));
        label_1.setBounds(578, 460, 100, 40);
        add(label_1);

        JLabel label_2 = new JLabel("性别：");
        label_2.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label_2.setOpaque(true);
        label_2.setForeground(new Color(19, 188, 210));
        label_2.setBackground(new Color(255,255,255,80));
        label_2.setBounds(578, 520, 100, 40);
        add(label_2);

        JLabel label_3 = new JLabel("电话：");
        label_3.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label_3.setOpaque(true);
        label_3.setForeground(new Color(19, 188, 210));
        label_3.setBackground(new Color(255,255,255,80));
        label_3.setBounds(578, 580, 100, 40);
        add(label_3);

        JLabel label_4 = new JLabel("电子邮箱：");
        label_4.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label_4.setOpaque(true);
        label_4.setForeground(new Color(19, 188, 210));
        label_4.setBackground(new Color(255,255,255,80));
        label_4.setBounds(578, 640, 100, 40);
        add(label_4);

        JLabel label_5 = new JLabel("密码：");
        label_5.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label_5.setOpaque(true);
        label_5.setForeground(new Color(19, 188, 210));
        label_5.setBackground(new Color(255,255,255,80));
        label_5.setBounds(578, 700, 100, 40);
        add(label_5);

//        JLabel label_6 = new JLabel("");
//        label_6.setFont(new Font("微软雅黑", Font.BOLD, 18));
//        label_6.setOpaque(true);
//        label_6.setForeground(new Color(19, 188, 210));
//        label_6.setBackground(new Color(255,255,255,80));
//        label_6.setBounds(578, 460, 100, 40);
//        add(label_6);





        //事件响应
        //返回
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==returnButton)
                {
                    setVisible(false);
                }
            }
        });
        //启动编辑
        EditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置可编辑
                txt_4.setForeground(Color.BLACK);
                txt_4.setEditable(true);//电话
                txt_5.setForeground(Color.BLACK);
                txt_5.setEditable(true);//邮箱
                txt_6.setForeground(Color.BLACK);
                txt_6.setText("");
                txt_6.setEditable(true);//密码


            }
        });

        //保存
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String txtAdminPhoneNumber = txt_4.getText().trim();//获取修改后的电话
                String txtAdminEmail = txt_5.getText().trim();//获取修改后的邮箱
                String txtAdminPassword = txt_6.getText().trim();//获取修改后的密码
                HashMap<String,String> mapCardNum_AdminPhoneNumber = new HashMap<String, String>();
                mapCardNum_AdminPhoneNumber.put("cardNumber", AdminCardNumber );
                mapCardNum_AdminPhoneNumber.put("phoneNumber",txtAdminPhoneNumber);
                AppAdminInfoHelper.resetPhoneNumber(mapCardNum_AdminPhoneNumber);

                HashMap<String,String>mapCardNum_AdminEmail = new HashMap<String, String>();
                mapCardNum_AdminEmail.put("cardNumber", AdminCardNumber);
                mapCardNum_AdminEmail.put("email",txtAdminEmail );
                AppAdminInfoHelper.resetEmail(mapCardNum_AdminEmail);

                HashMap<String,String>mapCardNum_AdminPassword = new HashMap<String, String>();
                mapCardNum_AdminPassword.put("cardNumber", AdminCardNumber);
                System.out.println(StringUtils.MD5EncodeSalted(txtAdminPassword, ""));
                String tempAdminPassword=StringUtils.MD5EncodeSalted(txtAdminPassword, "");
                mapCardNum_AdminPassword.put("password", tempAdminPassword);
                //AppStudentInfoHelper.resetPassword(mapCardNum_StudentPassword);//会超时，待解决

                // txt_8.setText(App.session.getStudent().getPhoneNumber());
                txt_4.setForeground(Color.GRAY);
                txt_4.setEditable(false);//电话
                //  txt_9.setText(App.session.getStudent().getEmail());
                txt_5.setForeground(Color.GRAY);
                txt_5.setEditable(false);//邮箱
                txt_6.setForeground(Color.GRAY);
                txt_6.setEditable(false);//密码
                txt_6.setText("输入重置密码");

                txt_4.repaint();
                txt_5.repaint();
                txt_6.repaint();

                JOptionPane.showMessageDialog(null, "保存成功");

            }
        });



    }



}
