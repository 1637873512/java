package com.qhit.dao.impl;

import com.qhit.dao.EasyBuyNewsDao;
import com.qhit.entity.EasyBuyNews;
import com.qhit.entity.EasyBuyUser;
import com.qhit.until.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2019/12/9.
 */
public class EasyBuyNewsDaoImpl extends BaseDao implements EasyBuyNewsDao {
    private Connection conn  = null;
    private PreparedStatement past = null;
    private ResultSet rs = null;
    @Override
    public List<EasyBuyNews> getPage(int index, int pageSize, String name) {
        List<EasyBuyNews> list = new ArrayList<EasyBuyNews>();
        EasyBuyNews news = null;
        conn = getConn();
        String sql = "select * from easybuy_news";
        if (name!=null&&name.trim()!=""){
            sql+=" where title like '%"+name+"%'";
        }
        sql+=" limit ?,?";
        try {
            past =  conn.prepareStatement(sql);
            past.setInt(1,index);
            past.setInt(2,pageSize);
            rs = past.executeQuery();
            while (rs.next()){
                news = new EasyBuyNews();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setCreateTime(rs.getDate("createTime"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return list;
    }

    @Override
    public int getCount(String name) {
        int num = 0;
        conn = getConn();
        String sql = "select count(1) from easybuy_news";
        if (name != null&&name.trim()!=""){
            sql+=" where title like '%"+name+"%'";
        }
        try {
            past = conn.prepareStatement(sql);
            rs = past.executeQuery();
            while (rs.next()){
                num = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return num;
    }

    @Override
    public int add(EasyBuyNews news) {
        String sql = "insert into easybuy_news (title,content,createTime) value (?,?,?)";
        Object objs[] ={news.getTitle(),news.getContent(),news.getCreateTime()};
        int num = excuteUpdate(sql,objs);
        return num;
    }

    @Override
    public int del(int id) {
        String sql = "delete from easybuy_news where id = ?";
        Object[] objc = {id};
        int num = excuteUpdate(sql,objc);
        return num;
    }

    @Override
    public EasyBuyNews getOne(int id) {
        EasyBuyNews news = null;
        conn = getConn();
        String sql = "select * from easybuy_news where id =?";
        try {
            past = conn.prepareStatement(sql);
            past.setInt(1,id);
            rs = past.executeQuery();
            while (rs.next()){
                news = new EasyBuyNews();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setCreateTime(rs.getDate("createTime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return news;
    }

    @Override
    public int updateEasyBuyNews(EasyBuyNews news) {
        String sql = "update easybuy_news set title = ?, content = ? where id = ?";
        Object[] objs = {news.getTitle(),news.getContent(),news.getId()};
        int num = excuteUpdate(sql,objs);
        return num;
    }
}
