package com.qhit.dao.impl;

import com.qhit.dao.EasyBuyProductDao;
import com.qhit.entity.EasyBuyNews;
import com.qhit.entity.EasyBuyProduct;
import com.qhit.entity.EasyBuyProductCategory;
import com.qhit.entity.EasyBuyUser;
import com.qhit.until.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2019/12/9.
 */
public class EasyBuyProductDaoImpl extends BaseDao implements EasyBuyProductDao {

    private Connection conn  = null;
    private PreparedStatement past = null;
    private ResultSet rs = null;
    @Override
    public List<EasyBuyProduct> getPage(int index, int pageSize, String name) {
        List<EasyBuyProduct> list = new ArrayList<EasyBuyProduct>();
        EasyBuyProduct product = null;
        conn = getConn();
        String sql = "select p.id,p.name,p.price,c1.name \"一级分类名称\",c2.name \"二级分类名称\",c3.name \"三级分类名称\",p.fileName,p.isDelete from easybuy_product p \n" +
                "LEFT JOIN easybuy_product_category c1 ON p.categoryLevel1Id = c1.id\n" +
                "LEFT JOIN easybuy_product_category c2 ON p.categoryLevel2Id = c2.id\n" +
                "LEFT JOIN easybuy_product_category c3 ON p.categoryLevel3Id = c3.id";
        if (name!=null&&name.trim()!=""){
            sql+=" where p.name like '%"+name+"%'";
        }
        sql+=" limit ?,?";
        try {
            past =  conn.prepareStatement(sql);
            past.setInt(1,index);
            past.setInt(2,pageSize);
            rs = past.executeQuery();
            while (rs.next()){
                product = new EasyBuyProduct();
                EasyBuyProductCategory pc1 = new EasyBuyProductCategory();
                EasyBuyProductCategory pc2 = new EasyBuyProductCategory();
                EasyBuyProductCategory pc3 = new EasyBuyProductCategory();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                pc1.setName(rs.getString(4));
                pc2.setName(rs.getString(5));
                pc3.setName(rs.getString(6));
                product.setPc1(pc1);
                product.setPc2(pc2);
                product.setPc3(pc3);
                product.setFileName(rs.getString("fileName"));
                product.setIsDelete(rs.getInt("isDelete"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return list;
    }

    @Override
    public int getCount(String name) {
        int num = 0;
        conn = getConn();
        String sql = "select count(1) from easybuy_product";
        if (name != null&&name.trim()!=""){
            sql+=" where name like '%"+name+"%'";
        }
        try {
            past = conn.prepareStatement(sql);
            rs = past.executeQuery();
            while (rs.next()){
                num = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return num;
    }

    @Override
    public int del(int id) {
        String sql = "update easybuy_product set isDelete = 1 where id = ?";
        Object[] objc = {id};
        int num = excuteUpdate(sql,objc);
        return num;
    }

    @Override
    public EasyBuyProduct getOne(int id) {
        EasyBuyProduct product = null;
        conn = getConn();
        String sql = "select * from easybuy_product where id =?";
        try {
            past = conn.prepareStatement(sql);
            past.setInt(1,id);
            rs = past.executeQuery();
            while (rs.next()){
                product = new EasyBuyProduct();
                EasyBuyProductCategory pc1 = new EasyBuyProductCategory();
                EasyBuyProductCategory pc2 = new EasyBuyProductCategory();
                EasyBuyProductCategory pc3 = new EasyBuyProductCategory();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setDescription(rs.getString("description"));
//                pc1.setName(rs.getString(4));
//                pc2.setName(rs.getString(5));
//                pc3.setName(rs.getString(6));
                pc1.setId(rs.getInt(6));
                pc2.setId(rs.getInt(7));
                pc3.setId(rs.getInt(8));
                product.setPc1(pc1);
                product.setPc2(pc2);
                product.setPc3(pc3);
                product.setFileName(rs.getString("fileName"));
                product.setIsDelete(rs.getInt("isDelete"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,past,rs);
        }
        return product;
    }

    @Override
    public int updateEasyBuyProduct(EasyBuyProduct product) {
        String sql = "update easybuy_product set name = ?, price = ?, stock = ?, description = ?, categoryLevel1Id = ?, categoryLevel2Id = ?, categoryLevel3Id = ?, fileName = ? where id = ?";
        Object[] objs = {product.getName(),product.getPrice(),product.getStock(),product.getDescription(),product.getPc1().getId(),product.getPc2().getId(),product.getPc3().getId(),product.getFileName(),product.getId()};
        int num = excuteUpdate(sql,objs);
        return num;
    }

    @Override
    public int addEasyBuyProduct(EasyBuyProduct product) {
        String sql = "insert into easybuy_product (name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete) values (?,?,?,?,?,?,?,?,?)";
        Object[] objs = {product.getName(),product.getDescription(),product.getPrice(),product.getStock(),product.getPc1().getId(),product.getPc2().getId(),product.getPc3().getId(),product.getFileName(),product.getIsDelete()};
        int num = excuteUpdate(sql,objs);
        return num;
    }
}
