package com.vcampus.test;

import com.alee.laf.WebLookAndFeel;

import com.vcampus.client.main.college.AppCollegeAdminUpdate;
import com.vcampus.client.main.manager.AppAdmin;
import java.awt.EventQueue;

public class UpdateEntry {
    public UpdateEntry() {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install();
                AppCollegeAdminUpdate app = new AppCollegeAdminUpdate();
                app.setVisible(true);
            }
        });
    }
}
