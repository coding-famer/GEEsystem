package com.vcampus.server.shop;

import com.vcampus.dao.IGoodsHistoryMapper;
import com.vcampus.dao.IGoodsMapper;
import com.vcampus.entity.Goods;
import com.vcampus.server.App;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;

/**
 * 商店管理员角色的服务器后端。
 *
 */
public class ShopAdminServer {
    public static Boolean insertNewGoods(Goods goods) {
        Boolean result = null;

        SqlSession sqlSession = null;
        try {
            sqlSession = App.sqlSessionFactory.openSession();
            IGoodsMapper goodsMapper = sqlSession.getMapper(IGoodsMapper.class);
            result = goodsMapper.insertNewGoods(goods);
            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean insertNewGoodsWithoutId(Goods goods) {
        Boolean result = null;

        SqlSession sqlSession = null;
        try {
            sqlSession = App.sqlSessionFactory.openSession();
            IGoodsMapper goodsMapper = sqlSession.getMapper(IGoodsMapper.class);
            result = goodsMapper.insertNewGoodsWithoutId(goods);
            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean deleteGoods(Goods goods) {
        Boolean result = null;

        SqlSession sqlSession = null;
        try {
            sqlSession = App.sqlSessionFactory.openSession();
            IGoodsMapper goodsMapper = sqlSession.getMapper(IGoodsMapper.class);
            result = goodsMapper.deleteGoods(goods);
            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static int getMonthSum(int month) {
        int result = -1;

        SqlSession sqlSession = null;
        try {
            sqlSession = App.sqlSessionFactory.openSession();
            IGoodsHistoryMapper goodsHistoryMapper = sqlSession.getMapper(IGoodsHistoryMapper.class);
            result = goodsHistoryMapper.getMonthSum(month);
            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static BigDecimal getMonthSaleMoney(int month) {
        BigDecimal result=BigDecimal.valueOf(0);
        SqlSession sqlSession = null;
        try {
            sqlSession = App.sqlSessionFactory.openSession();
            IGoodsHistoryMapper goodsHistoryMapper = sqlSession.getMapper(IGoodsHistoryMapper.class);
            result = goodsHistoryMapper.getMonthSaleMoney(month);
            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }
}
