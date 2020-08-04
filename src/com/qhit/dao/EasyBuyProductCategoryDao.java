package com.qhit.dao;

import com.qhit.entity.EasyBuyProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ASUS on 2019/12/10.
 */
public interface EasyBuyProductCategoryDao {
    List<EasyBuyProductCategory> feilei(@Param("type") int type,@Param("parentId") int parentId);
}
