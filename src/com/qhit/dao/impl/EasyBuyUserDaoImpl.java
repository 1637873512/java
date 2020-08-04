package com.qhit.dao.impl;

import com.mysql.jdbc.StringUtils;
import com.qhit.dao.EasyBuyUserDao;
import com.qhit.entity.EasyBuyUser;
import com.qhit.until.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2019/12/2.
 */
public class EasyBuyUserDaoImpl extends BaseDao implements EasyBuyUserDao {
    private Connection conn  = null;
    private PreparedStatement past = null;
    private ResultSet rs = null;
    @Override
    public EasyBuyUser login(String loginName,String password) {
        conn = getConn();
        EasyBuyUser user = null;
        String sql = "select * from easybuy_user where loginName = ? and password = ?";
        try {
            past = conn.prepareStatement(sql);
            past.setString(1,loginName);
            past.setString(2,password);
            rs =  past.executeQuery();
            while (rs.next()){
                user = new EasyBuyUser();
                user.setId(rs.getInt("id"));
                user.setLoginName(rs.getString("loginName"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getInt("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return user;
    }

    @Override
    public List<EasyBuyUser> getPage(int index, int pageSize, String name) {
        List<EasyBuyUser> list = new ArrayList<EasyBuyUser>();
        EasyBuyUser user = null;
        conn = getConn();
        String sql = "select * from easybuy_user";
        if (name!=null&&name.trim()!=""){
            sql+=" where loginName like '%"+name+"%'";
        }
        sql+=" limit ?,?";
        try {
                past =  conn.prepareStatement(sql);
                past.setInt(1,index);
                past.setInt(2,pageSize);
                rs = past.executeQuery();
            while (rs.next()){
                user = new EasyBuyUser();
                user.setId(rs.getInt("id"));
                user.setLoginName(rs.getString("loginName"));
                user.setUserName(rs.getString("userName"));
                list.add(user);
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
        String sql = "select count(1) from easybuy_user";
        if (name != null&&name.trim()!=""){
            sql+=" where loginName like '%"+name+"%'";
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
    public int del(int id) {
        String sql = "delete from easybuy_user where id = ?";
        Object[] objc = {id};
        int num = excuteUpdate(sql,objc);
        return num;
    }

    @Override
    public int add(EasyBuyUser user) {
        String sql = "insert into easybuy_user (loginName,userName,password,sex,identityCode,email,mobile,type) value (?,?,?,?,?,?,?,?)";
        Object[] objc ={user.getLoginName(),user.getUserName(),user.getPassword(),user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getType()};
        int num = excuteUpdate(sql,objc);
        return num;
    }

    @Override
    public EasyBuyUser getOne(int id) {
        EasyBuyUser user = null;
        conn = getConn();
        String sql = "select * from easybuy_user where id =?";
        try {
            past = conn.prepareStatement(sql);
            past.setInt(1,id);
            rs = past.executeQuery();
            while (rs.next()){
               user = new EasyBuyUser();
               user.setId(rs.getInt("id"));
               user.setUserName(rs.getString("userName"));
               user.setLoginName(rs.getString("loginName"));
               user.setPassword(rs.getString("password"));
               user.setSex(rs.getInt("sex"));
               user.setIdentityCode(rs.getString("identityCode"));
               user.setEmail(rs.getString("email"));
               user.setMobile(rs.getString("mobile"));
               user.setType(rs.getInt("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return user;
    }

    @Override
    public int updateEasyBuyUser(EasyBuyUser user) {
        String sql = "update easybuy_user set sex = ?, identityCode = ?, email = ?, mobile = ?, userName = ? where id = ?";
        Object[] objs = {user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getUserName(),user.getId()};
        int num = excuteUpdate(sql,objs);
        return num;
    }

    @Override
    public int changePasswprd(String loginName,String password) {
        String sql = "update easybuy_user set password = ? where loginName = ?";
        Object[] objs = {password,loginName};
        int num = excuteUpdate(sql,objs);
        return num;
    }
}
