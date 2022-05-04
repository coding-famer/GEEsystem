package com.vcampus.server.bank;

import com.vcampus.dao.IDealHistoryMapper;
import com.vcampus.dao.IStudentMapper;
import com.vcampus.entity.DealHistory;
import com.vcampus.server.App;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于银行相关的服务器类。
 *
 */
public class BankServer {
    /**
     * 将学生卡的余额增加money。
     * @param map <BigDecimal money, String cardNumber>
     * @return 操作后的余额
     */
    public static BigDecimal chargeStudentCard(HashMap map) {
        BigDecimal result = new BigDecimal(0);
        try {
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);

            studentMapper.chargeCard(map);

            result = studentMapper.getBalance((String) map.get("cardNumber"));

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将学生卡的余额设置为money。
     * @param map <BigDecimal money, String cardNumber>
     * @return 操作后的余额
     */
    public static BigDecimal setStudentBalance(HashMap map) {
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

    /**
     * 按学生一卡通号查询余额
     *
     * @param cardNumber 学生的一卡通号
     * @return 学生的余额
     */
    public static BigDecimal getStudentBalance(String cardNumber) {
        BigDecimal result = new BigDecimal(0);
        SqlSession sqlSession = null;
        try {
            sqlSession = App.sqlSessionFactory.openSession();
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);

            result = studentMapper.getBalance(cardNumber);;

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }




    /**
     * 插入交易记录
     *
     * @param dealHistory 交易记录
     * @return 插入操作的结果
     */
    public static Boolean insertDealHistory(DealHistory dealHistory) {

        try {
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IDealHistoryMapper dealHistoryMapper =sqlSession.getMapper(IDealHistoryMapper.class);

            dealHistoryMapper.insertDealHistory(dealHistory);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 查询交易记录
     *
     * @param cardNumber 一卡通号
     * @return 交易记录
     */
    public static List<DealHistory> getDealHistory(String cardNumber){
        List<DealHistory> list =new ArrayList<>();
        SqlSession sqlSession=null;
        try{
            sqlSession = App.sqlSessionFactory.openSession();
            IDealHistoryMapper dealHistoryMapper =sqlSession.getMapper(IDealHistoryMapper.class);


            list=dealHistoryMapper.getDealHistory(cardNumber);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
