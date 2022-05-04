package com.vcampus.test;

import com.alee.laf.WebLookAndFeel;
import com.vcampus.client.main.manager.AppAdmin;
import java.awt.EventQueue;

public class AppAdminEntry {
    public AppAdminEntry() {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install();
                AppAdmin app = new AppAdmin();
                app.setVisible(true);
            }
        });
    }
}
