package com.qhit.service.Impl;

import com.qhit.dao.EasyBuyNewsDao;
import com.qhit.dao.impl.EasyBuyNewsDaoImpl;
import com.qhit.entity.EasyBuyNews;
import com.qhit.service.EasyBuyNewsService;
import com.qhit.until.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/9.
 */
@Service("nd")
public class EasyBuyNewsServiceImpl implements EasyBuyNewsService {
    @Autowired
    private EasyBuyNewsDao nd;

    public void setNd(EasyBuyNewsDao nd) {
        this.nd = nd;
    }

    @Override
    public List<EasyBuyNews> getPage(int index, int pageSize, String name) {
        List<EasyBuyNews> list = nd.getPage(index,pageSize,name);
        return list;
    }

    @Override
    public int getCount(String name) {
        int num = nd.getCount(name);
        return num;
    }

    @Override
    public int add(EasyBuyNews news) {
        int num = nd.add(news);
        return num;
    }

    @Override
    public int del(int id) {
        int num = nd.del(id);
        return num;
    }

    @Override
    public EasyBuyNews getOne(int id) {
        EasyBuyNews news = nd.getOne(id);
        return news;
    }

    @Override
    public int updateEasyBuyNews(EasyBuyNews news) {
        int num = nd.updateEasyBuyNews(news);
        return num;
    }
}
