package com.qhit.dao.impl;

import com.qhit.dao.EasyBuyProductCategoryDao;
import com.qhit.entity.EasyBuyProduct;
import com.qhit.entity.EasyBuyProductCategory;
import com.qhit.until.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2019/12/10.
 */
public class EasyBuyProductCategoryDaoImpl extends BaseDao implements EasyBuyProductCategoryDao{
    private Connection conn  = null;
    private PreparedStatement past = null;
    private ResultSet rs = null;
    @Override
    public List<EasyBuyProductCategory> feilei(int type, int parentId) {
        List<EasyBuyProductCategory> list = new ArrayList<EasyBuyProductCategory>();
        EasyBuyProductCategory pc = null;
        conn = getConn();
        String sql = "SELECT id,name FROM easybuy_product_category WHERE type = ? AND parentId = ?";
        try {
            past =  conn.prepareStatement(sql);
            past.setInt(1,type);
            past.setInt(2,parentId);
            rs = past.executeQuery();
            while (rs.next()){
                pc = new EasyBuyProductCategory();
                pc.setId(rs.getInt("id"));
                pc.setName(rs.getString("name"));
                list.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return list;
    }
}
