package com.qhit.service;

import com.qhit.entity.EasyBuyProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ASUS on 2019/12/9.
 */
public interface EasyBuyProductService {
    //分页
    List<EasyBuyProduct> getPage(@Param("index") int index, @Param("pageSize") int pageSize, @Param("name") String name);
    //查询总条数
    int getCount(@Param("name") String name);
    //删除
    int del(@Param("name") int id);
    //根据id查找
    EasyBuyProduct getOne(@Param("id") int id);
    //修改
    int updateEasyBuyProduct(EasyBuyProduct product);
    //添加商品
    int addEasyBuyProduct(EasyBuyProduct product);
}
