package com.qhit.service.Impl;

import com.qhit.dao.EasyBuyUserDao;
import com.qhit.dao.impl.EasyBuyUserDaoImpl;
import com.qhit.entity.EasyBuyUser;
import com.qhit.service.EasyBuyUserService;
import com.qhit.until.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/2.
 */
///用来给service层创建对象
//里面的参数就是对象名字相当于bean的id
@Service
public class EasyBuyUserServiceImpl implements EasyBuyUserService {
    @Autowired
    private EasyBuyUserDao ud;

    public void setUd(EasyBuyUserDao ud) {
        this.ud = ud;
    }

    @Override
    public EasyBuyUser login(String loginName, String password) {
        EasyBuyUser user = ud.login(loginName,password);
        return user;
    }

    @Override
    public List<EasyBuyUser> getPage(int index, int pageSize, String name) {
        List<EasyBuyUser> list = ud.getPage(index,pageSize,name);
        return list;
    }

    @Override
    public int getCount(String name) {
        int num = ud.getCount(name);
        return num;
    }

    @Override
    public int del(int id) {
        int num = ud.del(id);
        return num;
    }

    @Override
    public int add(EasyBuyUser user) {
        int num = ud.add(user);
        return num;
    }

    @Override
    public EasyBuyUser getOne(int id) {
        EasyBuyUser user = ud.getOne(id);
        return user;
    }

    @Override
    public int updateEasyBuyUser(EasyBuyUser user) {
        int num = ud.updateEasyBuyUser(user);
        return num;
    }

    @Override
    public int changePasswprd(String loginName, String password) {
        int num = ud.changePasswprd(loginName,password);
        return num;
    }
}
