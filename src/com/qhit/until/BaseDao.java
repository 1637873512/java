package com.qhit.until;

import java.sql.*;

/**
 * Created by ASUS on 2019/11/11.
 */
public class BaseDao {
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/easybuy";
    private String user = "root";
    private String pwd = "root";

    //获取链接的方法
    public Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(driver);
          conn =  DriverManager.getConnection(url,user,pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //封装关闭的方法
    public void closeAll(Connection conn, PreparedStatement past, ResultSet rs){
        try {
            if (rs!=null){
                rs.close();
            }
            if (past!=null){
                past.close();
            }
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //封装增删改
    public int excuteUpdate(String sql,Object[] objc){
        Connection conn = getConn();
        PreparedStatement past = null;
        int num = 0;
        ResultSet rs = null;

        try {
           past = conn.prepareStatement(sql);
           for (int i = 0;i<objc.length;i++){
               past.setObject(i+1,objc[i]);
           }
           num = past.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return num;
    }
}
