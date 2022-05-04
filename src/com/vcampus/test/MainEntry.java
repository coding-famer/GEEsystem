package com.vcampus.test;

import com.vcampus.server.App;

import java.awt.*;
import java.io.IOException;

/**
 * 服务器程序主入口
 *
 */
public class MainEntry {
    public static void main(String[] args) throws IOException {
        /* AES encryption and decryption
        try {
            AES128 _AES128 = new AES128();
            _AES128.main(args);
        }
        catch (Exception e){
            //Do nothing
        }*/


        EventQueue.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });

    }
}