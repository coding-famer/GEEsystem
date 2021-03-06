package com.vcampus.dao;

import com.vcampus.entity.Admin;

import java.util.Map;

/**
 * 一系列管理员数据库操作的接口，用于mybatis的映射
 *
 */
public interface IAdminMapper {
    @Deprecated
    /**
     * 仅在密码明文存储可用。
     */
    public Boolean verifyAdmin(Admin admin);

    public Admin getAdminDetailByCardNumber(String cardNumber);

    public String getSaltByCardNumber(String cardNumber);

    public String getNameByCardNumber(String cardNumber);

    public String getPasswordByCardNumber(String cardNumber);

    public Boolean resetEmail(Map map);
    public Boolean resetPhoneNumber(Map map);
    public Boolean resetPassword(Map map);
}
