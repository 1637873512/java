package com.qhit.service;

import com.qhit.entity.EasyBuyProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ASUS on 2019/12/10.
 */
public interface EasyBuyProductCategoryService {
    List<EasyBuyProductCategory> feilei(@Param("type") int type, @Param("id") int parentId);
}
