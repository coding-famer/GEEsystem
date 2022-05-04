package com.vcampus.client;

import com.vcampus.entity.*;
import com.vcampus.net.Request;
import com.vcampus.util.StringUtils;
import com.vcampus.client.main.App;
import com.vcampus.util.ResponseUtils;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * 向服务器发送验证请求所需的工具类
 */
public class Verifier {
    public static Student checkStudent(String cardNumber, String password) {
        return ResponseUtils
                .getResponseByHash(new Request(App.connectionToServer, null, "com.vcampus.server.Auth.studentLoginChecker",
                        new Object[] { new Student(cardNumber, password) }).send())
                .getReturn(Student.class);
    }


    public static Admin checkAdmin(String cardNumber, String password) {
        return ResponseUtils
                .getResponseByHash(new Request(App.connectionToServer, null, "com.vcampus.server.Auth.adminLoginChecker",
                        new Object[] { new Student(cardNumber,password) }).send())
                .getReturn(Admin.class);
    }

}
