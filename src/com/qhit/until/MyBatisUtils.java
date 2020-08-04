package com.qhit.until;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ASUS on 2019/12/18.
 */
public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory=null;
    static{
        //Mybatis的配置文件路径
        String resource = "mybatis-conf.xml";
        //通过配置文件路径取得输入流
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            //通过配置文件构建SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //封装获取sqlSession的方法
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession(true);
    }
}
