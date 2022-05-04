package com.vcampus.client.main.manager;

import com.kitfox.svg.A;
import com.vcampus.client.main.AppLife;
import com.vcampus.client.main.shop.AppShopAdmin;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
/**
 * 管理员目录
 */
public class ManCategory extends JTree {
    private JTree jt;
    private DefaultMutableTreeNode nodLogin;
    /* constructor */
    public ManCategory() {
        init();
    }

    /**
     * Build a JTree.
     */
    private void init() {
        nodLogin= new DefaultMutableTreeNode("管理员登陆");
        DefaultMutableTreeNode nodInfor = new DefaultMutableTreeNode("个人信息");
        DefaultMutableTreeNode nodTeaInfor = new DefaultMutableTreeNode("教师信息管理");
        DefaultMutableTreeNode nodStuInfor = new DefaultMutableTreeNode("学生信息管理");
        DefaultMutableTreeNode nodClassManage = new DefaultMutableTreeNode("课程管理");
        DefaultMutableTreeNode nodLibrary = new DefaultMutableTreeNode("图书馆");
        DefaultMutableTreeNode nodDorm = new DefaultMutableTreeNode("生活管理");
        DefaultMutableTreeNode nodShop = new DefaultMutableTreeNode("商店管理");
        DefaultMutableTreeNode nodDailyReport = new DefaultMutableTreeNode("每日上报管理");
        nodLogin.add(nodInfor);nodLogin.add(nodTeaInfor); nodLogin.add(nodStuInfor);
        nodLogin.add(nodClassManage);nodLogin.add(nodLibrary);nodLogin.add(nodDorm);
        nodLogin.add(nodDailyReport);nodLogin.add(nodShop);

        jt = new JTree(nodLogin);

        TreeSelectionModel treeSelectionModel;
        treeSelectionModel=jt.getSelectionModel();
        treeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jt.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (!jt.isSelectionEmpty()) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) jt.getLastSelectedPathComponent();
                    if(node==nodInfor){
                        if(this.getClass().getName()!="com.vcampus.client.main.manager.AppAdminInfoFrame")
                        {
                            AppAdminInfoFrame app=new AppAdminInfoFrame();
                            app.setVisible(true);
                            setVisible(false);
                        }
                    }
                    else if(node==nodStuInfor){
                        if(this.getClass().getName()!="com.vcampus.client.main.manager.StuManage") {
                            StuManage app = new StuManage();
                            app.setVisible(true);
                            setVisible(false);
                        }
                    }
                    else if(node==nodDorm){
                        if(this.getClass().getName()!="com.vcampus.client.main.AppDormAdmin") {
                            AppLife app = new AppLife();
                            app.setVisible(true);
                            setVisible(false);
                        }
                    }
                    else if(node==nodShop){
                        if(this.getClass().getName()!="com.vcampus.client.main.shop.AppShopAdmin") {
                            AppShopAdmin app = new AppShopAdmin();
                            app.setVisible(true);
                            setVisible(false);
                        }
                    }
                }
            }
        });
    }

    public JTree getJTree() {
        return jt;
    }

    public DefaultMutableTreeNode getNode() {
        return nodLogin;
    }
}
