package com.vcampus.util;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * 用于Swing框架的一些工具
 */

public class SwingUtils {
    /**
     * 判断文本控件是否为空
     *
     * @param field 文本控件对象
     * @return 文本控件所含内容在去除首尾空格后是否为空
     */
    public static Boolean isEmpty(JTextComponent field){
        return field.getText().trim().isEmpty();
    }

    /**
     * 弹出信息提示框
     */
    static public void showMessage(JPanel parent, String msg, String title) {
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 弹出错误提示框
     */
    static public void showError(JPanel parent, String msg, String title) {
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.ERROR_MESSAGE);
    }

}
