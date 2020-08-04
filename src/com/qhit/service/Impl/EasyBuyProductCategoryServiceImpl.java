package com.qhit.service.Impl;

import com.qhit.dao.EasyBuyProductCategoryDao;
import com.qhit.dao.impl.EasyBuyProductCategoryDaoImpl;
import com.qhit.entity.EasyBuyProductCategory;
import com.qhit.service.EasyBuyProductCategoryService;
import com.qhit.until.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/10.
 */
@Service("pcd")
public class EasyBuyProductCategoryServiceImpl implements EasyBuyProductCategoryService {
    @Autowired
    private EasyBuyProductCategoryDao pd;

    public void setPd(EasyBuyProductCategoryDao pd) {
        this.pd = pd;
    }

    @Override
    public List<EasyBuyProductCategory> feilei(int type, int parentId) {
        List<EasyBuyProductCategory> list = pd.feilei(type,parentId);
        return list;
    }
}
