//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.vcampus.test;

import com.alee.laf.WebLookAndFeel;
import com.vcampus.client.main.student.AppStudent;
import java.awt.EventQueue;

public class AppStudentEntry {
    public AppStudentEntry() {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install();
                AppStudent app = new AppStudent();
                app.setVisible(true);
            }
        });
    }
}
