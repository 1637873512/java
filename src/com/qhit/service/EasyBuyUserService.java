package com.qhit.service;

import com.qhit.entity.EasyBuyUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/2.
 */
public interface EasyBuyUserService {
    ///登录
    EasyBuyUser login(@Param("loginName") String loginName, @Param("password") String password);
    //分页
    List<EasyBuyUser> getPage(@Param("index")int index,@Param("pageSize")int pageSize,@Param("name")String name);
    //查询总条数
    int getCount(@Param("name")String name);
    //删除
    int del(@Param("id")int id);
    //添加管理员
    int add(EasyBuyUser user);
    //根据id查找
    EasyBuyUser getOne(@Param("id")int id);
    //修改
    int updateEasyBuyUser(EasyBuyUser user);
    //修改密码
    int changePasswprd(@Param("loginName")String loginName,@Param("password")String password);
}
