package com.vcampus.UI.labels;

import com.vcampus.UI.DropShadowBorder;

import javax.swing.*;
import java.awt.*;

/**
 */
public class MyJLabel4 extends JLabel {
    public MyJLabel4(){
        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowSize(10);
        //shadow.setShadowColor(Color.BLACK);
        shadow.setShowLeftShadow(true);
        shadow.setShowRightShadow(true);
        shadow.setShowBottomShadow(true);
        shadow.setShowTopShadow(true);
        shadow.setShadowColor(new Color(250, 64, 64, 230));
        shadow.setCornerSize(10);//默认12
        shadow.setShadowOpacity((float) 0.55);//透明度为0-1的浮点数
        //System.out.println(shadow.getShadowOpacity());
        //System.out.println(shadow.getCornerSize());
        this.setBorder(shadow);
    }
}