package com.vcampus.server;

import com.vcampus.dao.IStudentMapper;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 学生生活服务的服务器端，通过调用接口实现修改数据库或获取数据库数据。各函数大同小异
 */

public class AppLife {
    @Deprecated
    // moved to BankServer
    public static BigDecimal chargeCard(HashMap map) {
        BigDecimal result = new BigDecimal(0);
        try {
            SqlSession sqlSession = App.sqlSessionFactory.openSession(); //MyBatis提供的方法函数用于通信
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);//对应接口的实体

            studentMapper.chargeCard(map);//接口中的函数

            result = studentMapper.getBalance((String) map.get("cardNumber"));//返回值

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Deprecated
    // moved to BankServer
    public static BigDecimal setBalance(HashMap map) {
        BigDecimal result = new BigDecimal(0);
        try {
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);

            studentMapper.setBalance(map);

            result = studentMapper.getBalance((String) map.get("cardNumber"));

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String lossJudge(String cardNumber){
        String curLossStatus="挂失";
        try {
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);

            studentMapper.setLossStatusByCardNumber(cardNumber);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curLossStatus;
    }

    public static String foundJudge(String cardNumber){
        String curFoundStatus="正常";
        try {
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);

            studentMapper.setFoundStatusByCardNumber(cardNumber);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curFoundStatus;
    }

    public static String getDormAddress(String cardNumber) {
        String dormAddress="ERROR";
        try {
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);

            dormAddress=studentMapper.getDormAddress(cardNumber);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dormAddress;
    }


    public static String getBankAccountPassword(String cardNumber) {
        String bankAccountPassword="!ERROR!";
        try {
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);

            bankAccountPassword=studentMapper.getBankAccountPassword(cardNumber);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankAccountPassword;
    }

    public static String getBankAccountPasswordSalt(String cardNumber) {
        String bankAccountPasswordSalt="!ERROR!";
        try {
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);

            bankAccountPasswordSalt=studentMapper.getBankAccountPasswordSalt(cardNumber);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankAccountPasswordSalt;
    }


}
