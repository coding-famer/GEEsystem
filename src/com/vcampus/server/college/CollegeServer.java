package com.vcampus.server.college;

import com.vcampus.dao.*;
import com.vcampus.entity.DealHistory;
import com.vcampus.entity.College;
import com.vcampus.entity.GoodsHistory;
import com.vcampus.net.Request;
import com.vcampus.server.App;
import com.vcampus.util.ResponseUtils;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollegeServer {
    public static List<College> listAllInfo() {

        List<College> result = new ArrayList<>();
        SqlSession sqlSession = null;
        try {

            sqlSession = App.sqlSessionFactory.openSession();
            ICollegeMapper CollegeMapper = sqlSession.getMapper(ICollegeMapper.class);
            result = CollegeMapper.listAllInfo();

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            // sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static List<College> listInfo(HashMap map) {

        List<College> result = new ArrayList<>();
        SqlSession sqlSession = null;
        try {

            sqlSession = App.sqlSessionFactory.openSession();
            ICollegeMapper CollegeMapper = sqlSession.getMapper(ICollegeMapper.class);
            result = CollegeMapper.listInfo(map);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            // sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static List<College> listInfoOnlyByMajor(String string) {

        List<College> result = new ArrayList<>();
        SqlSession sqlSession = null;
        try {

            sqlSession = App.sqlSessionFactory.openSession();
            ICollegeMapper CollegeMapper = sqlSession.getMapper(ICollegeMapper.class);
            result = CollegeMapper.listInfoOnlyByMajor(string);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            // sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static List<College> listInfoOnlyByCollege(String string) {

        List<College> result = new ArrayList<>();
        SqlSession sqlSession = null;
        try {

            sqlSession = App.sqlSessionFactory.openSession();
            ICollegeMapper CollegeMapper = sqlSession.getMapper(ICollegeMapper.class);
            result = CollegeMapper.listInfoOnlyByCollege(string);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            // sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean insertInfo(Map map) {

        Boolean result = true;
        SqlSession sqlSession = null;
        try {

            sqlSession = App.sqlSessionFactory.openSession();
            ICollegeMapper CollegeMapper = sqlSession.getMapper(ICollegeMapper.class);
            result = CollegeMapper.insertInfo(map);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            // sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean changeInfo(Map map) {

        Boolean result = true;
        SqlSession sqlSession = null;
        try {

            sqlSession = App.sqlSessionFactory.openSession();
            ICollegeMapper CollegeMapper = sqlSession.getMapper(ICollegeMapper.class);
            result = CollegeMapper.changeInfo(map);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            // sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean deleteInfo(Map map) {

        Boolean result = true;
        SqlSession sqlSession = null;
        try {

            sqlSession = App.sqlSessionFactory.openSession();
            ICollegeMapper CollegeMapper = sqlSession.getMapper(ICollegeMapper.class);
            result = CollegeMapper.deleteInfo(map);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            // sqlSession.rollback();
            e.printStackTrace();
        }
        return result;
    }



}
