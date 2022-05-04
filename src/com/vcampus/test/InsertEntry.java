package com.vcampus.test;

import com.alee.laf.WebLookAndFeel;
import com.vcampus.client.main.college.AppCollegeAdminInsert;
import com.vcampus.client.main.manager.AppAdmin;
import java.awt.EventQueue;

public class InsertEntry {
    public InsertEntry() {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install();
                AppCollegeAdminInsert app = new AppCollegeAdminInsert();
                app.setVisible(true);
            }
        });
    }
}
