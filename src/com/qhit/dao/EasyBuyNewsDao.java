package com.qhit.dao;

import com.qhit.entity.EasyBuyNews;
import com.qhit.entity.EasyBuyProduct;
import com.qhit.entity.EasyBuyUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ASUS on 2019/12/9.
 */
public interface EasyBuyNewsDao {
    //分页
    List<EasyBuyNews> getPage(@Param("index") int index,@Param("pageSize") int pageSize,@Param("name") String name);
    //查询总条数
    int getCount(@Param("name") String name);
    //添加资讯
    int add(EasyBuyNews news);
    //删除
    int del(@Param("id") int id);
    //根据id查找
    EasyBuyNews getOne(@Param("id")int id);
    //修改
    int updateEasyBuyNews(EasyBuyNews news);
}
