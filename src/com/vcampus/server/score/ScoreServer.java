package com.vcampus.server.score;


import com.vcampus.dao.*;
import com.vcampus.entity.College;
import com.vcampus.entity.StuScore;
import com.vcampus.server.App;
import com.vcampus.util.ResponseUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class ScoreServer {

    public static StuScore getScore(String cardNumber)
    {
        StuScore result =null;
        SqlSession sqlSession = null;
        try {

            sqlSession = App.sqlSessionFactory.openSession();
            IStuScoreMapper StuScoreMapper = sqlSession.getMapper(IStuScoreMapper.class);
            result = StuScoreMapper.getStuScore(cardNumber);

            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            // sqlSession.rollback();
            e.printStackTrace();
        }

        return result;
    }


}
