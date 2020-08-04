package com.qhit.service.Impl;

import com.qhit.dao.EasyBuyProductDao;
import com.qhit.dao.impl.EasyBuyProductDaoImpl;
import com.qhit.entity.EasyBuyProduct;
import com.qhit.service.EasyBuyProductService;
import com.qhit.until.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/9.
 */
@Service("pd")
public class EasyBuyProductServiceImpl implements EasyBuyProductService {
    @Autowired
    private EasyBuyProductDao pd;

    public void setPd(EasyBuyProductDao pd) {
        this.pd = pd;
    }

    @Override
    public List<EasyBuyProduct> getPage(int index, int pageSize, String name) {
        List<EasyBuyProduct> list = pd.getPage(index,pageSize,name);
        return list;
    }

    @Override
    public int getCount(String name) {
        int num = pd.getCount(name);
        return num;
    }

    @Override
    public int del(int id) {
        int num = pd.del(id);
        return num;
    }

    @Override
    public EasyBuyProduct getOne(int id) {
        EasyBuyProduct product =pd.getOne(id);
        return product;
    }

    @Override
    public int updateEasyBuyProduct(EasyBuyProduct product) {
        int num = pd.updateEasyBuyProduct(product);
        return num;
    }

    @Override
    public int addEasyBuyProduct(EasyBuyProduct product) {
        int num = pd.addEasyBuyProduct(product);
        return num;
    }
}
