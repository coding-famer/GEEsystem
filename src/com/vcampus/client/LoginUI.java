package com.vcampus.client;

import com.vcampus.client.main.App;
import com.vcampus.entity.Student;
import com.vcampus.entity.Admin;
import com.vcampus.entity.UserType;
import com.vcampus.net.Session;
import com.vcampus.util.SwingUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.vcampus.util.CommonUtils.getUserTypeByCardNumber;

/**
 * 登陆界面的GUI
 *
 */

public class LoginUI extends JFrame {
    private JPanel loginPanel;
    private JButton btnLogin;
    private JRadioButton rdbAuto;
    private JRadioButton rdbStudent;
    private JRadioButton rdbTeacher;
    private JRadioButton rdbAdmin;
    private JTextField txtCardNumber;
    private JPasswordField txtPassword;

    private Locale locale;
    private ResourceBundle res;

    private UserType type;

    /**
     * 学生登录逻辑
     */
    private void studentLogin(){
        type = UserType.STUDENT;
        Student student = Verifier.checkStudent(txtCardNumber.getText(), new String(txtPassword.getPassword()));
        if (student != null) {
            SwingUtils.showMessage(null, res.getString("student_login_success"), res.getString("info"));
            // 填充App.session
            App.hasLogon = true;
            App.session = new Session(student);
            setVisible(false);
            // 要求界面路由
            App.requireRouting();
        } else {
            SwingUtils.showError(null, res.getString("wrong_password"), res.getString("error"));
            txtPassword.setText("");
        }
    }

    /**
     * 管理员登录逻辑
     */
    private void adminLogin(){
        type = UserType.ADMIN;
        Admin admin = Verifier.checkAdmin(txtCardNumber.getText(), new String(txtPassword.getPassword()));
        if (admin != null) {
            SwingUtils.showMessage(null, res.getString("admin_login_success"), res.getString("info"));
            // 填充App.session
            App.hasLogon = true;
            App.session = new Session(admin);
            setVisible(false);
            // 要求界面路由
            App.requireRouting();
        } else {
            SwingUtils.showError(null, res.getString("wrong_password"), res.getString("error"));
            txtPassword.setText("");
        }
    }

    /**
     * GUI登录逻辑
     * 检查是否有空字段、确定用户类型
     */
    public void login() {
        /* check if all fields are filled */
        if (SwingUtils.isEmpty(txtCardNumber) || SwingUtils.isEmpty(txtPassword)) {
            JOptionPane.showMessageDialog(null, res.getString("empty_field"), res.getString("error"), JOptionPane.ERROR_MESSAGE);
            return;
        }

        type = null;
        if (rdbStudent.isSelected()) {
            studentLogin();
        } else if (rdbAdmin.isSelected()) {
            adminLogin();
        } else if (rdbAuto.isSelected()){
            UserType _userType = getUserTypeByCardNumber(txtCardNumber.getText());
            if(_userType == null){
                JOptionPane.showMessageDialog(null, res.getString("invalid_card_number"), res.getString("error"), JOptionPane.ERROR_MESSAGE);
                return;
            }
            switch(_userType){
                case STUDENT:
                    studentLogin();
                    break;
                case ADMIN:
                    adminLogin();
                    break;
            }
        }

    }


    public LoginUI() {
        locale = Locale.getDefault();
        res = ResourceBundle.getBundle("com.vcampus.client.ClientResource", locale);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int x = Toolkit.getDefaultToolkit().getScreenSize().width/2 - 675;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height/2 - 250;
        setBounds(x, y, 1370, 520);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/assets/icon/graLogo.png")));
        setTitle(res.getString("window_title"));

        loginPanel = new JPanel();
        loginPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
        loginPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(loginPanel);

        JPanel pBody = new JPanel();
        loginPanel.add(pBody, BorderLayout.CENTER);
        pBody.setLayout(null);

        JLabel lblUsername = new JLabel(res.getString("card_number"));
        lblUsername.setFont(new Font("微软雅黑",Font.PLAIN, 14));
        lblUsername.setBounds(985, 215, 75, 18);
        pBody.add(lblUsername);

        txtCardNumber = new JTextField();
        txtCardNumber.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        txtCardNumber.setBounds(1045, 212, 180, 26);
        pBody.add(txtCardNumber);
        txtCardNumber.setColumns(8);

        JLabel lblPassword = new JLabel(res.getString("password"));
        lblPassword.setFont(new Font("微软雅黑",Font.PLAIN, 14));
        lblPassword.setBounds(996, 268, 40, 18);
        pBody.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        txtPassword.setBounds(1045, 265, 180, 26);
        pBody.add(txtPassword);

        rdbAuto = new JRadioButton(res.getString("auto"));
        rdbStudent = new JRadioButton(res.getString("student"));
        rdbTeacher = new JRadioButton(res.getString("teacher"));
        rdbAdmin = new JRadioButton(res.getString("admin"));

        rdbStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbStudent.isSelected()) {
                    rdbTeacher.setSelected(false);
                    rdbAdmin.setSelected(false);
                    rdbAuto.setSelected(false);
                } else {
                    rdbStudent.setSelected(true);
                }
            }
        });

        rdbTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbTeacher.isSelected()) {
                    rdbStudent.setSelected(false);
                    rdbAdmin.setSelected(false);
                    rdbAuto.setSelected(false);
                } else {
                    rdbTeacher.setSelected(true);
                }
            }
        });

        rdbAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbAdmin.isSelected()) {
                    rdbStudent.setSelected(false);
                    rdbTeacher.setSelected(false);
                    rdbAuto.setSelected(false);
                } else {
                    rdbAdmin.setSelected(true);
                }
            }
        });

        rdbAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbAuto.isSelected()) {
                    rdbStudent.setSelected(false);
                    rdbTeacher.setSelected(false);
                    rdbAdmin.setSelected(false);
                } else {
                    rdbAuto.setSelected(true);
                }
            }
        });

        rdbAdmin.setFont(new Font("微软雅黑", Font.PLAIN,18));
        rdbAuto.setFont(new Font("微软雅黑", Font.PLAIN,18));
        rdbStudent.setFont(new Font("微软雅黑", Font.PLAIN,18));

        rdbAuto.setBounds(1060,320,69,27);
        pBody.add(rdbAuto);
        rdbStudent.setBounds(1130, 320, 69, 27);
        pBody.add(rdbStudent);

        rdbAdmin.setBounds(1130, 350, 83, 27);
        pBody.add(rdbAdmin);
        rdbAuto.setSelected(true);

        /* 按下回车登录 */
        KeyAdapter loginKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        };

        txtCardNumber.addKeyListener(loginKeyAdapter);
        txtPassword.addKeyListener(loginKeyAdapter);
        //rdbStudent.addKeyListener(loginKeyAdapter);
        //rdbTeacher.addKeyListener(loginKeyAdapter);
        //rdbAdmin.addKeyListener(loginKeyAdapter);

        JLabel label = new JLabel(res.getString("dialog_title"));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 35));
        label.setBounds(997, 120, 233, 80);
        pBody.add(label);

        JLabel icon = new JLabel("");
        icon.setIcon(new ImageIcon(getClass().getResource("/resources/assets/icon/graTitle.png")));
        icon.setBounds(875, 28, 471, 88);
        pBody.add(icon);

        btnLogin = new JButton(res.getString("login"));
        btnLogin.setFont(new Font("微软雅黑", Font.BOLD, 30));
        //btnLogin.setIcon(new ImageIcon(getClass().getResource("/resources/assets/icon/right-circle.png")));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnLogin.setBounds(1045, 392, 140, 75);
        pBody.add(btnLogin);

        JLabel lblUserType = new JLabel(res.getString("usertype"));
        lblUserType.setFont(new Font("微软雅黑",Font.PLAIN, 14));
        lblUserType.setBounds(985, 323, 75, 18);
        pBody.add(lblUserType);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/resources/assets/bg/grau.jpg")));
        pBody.add(bg);
        bg.setBounds(0, 0, 870, 525);
    }
}
