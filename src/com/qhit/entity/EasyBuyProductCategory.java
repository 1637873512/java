package com.qhit.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2019/12/9.
 */
public class EasyBuyProductCategory implements Serializable {
    private int id;
    private String name;
    private int parentId;
    private int type;
    private String iconClass;
    private List<EasyBuyProduct> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public List<EasyBuyProduct> getProducts() {
        return products;
    }

    public void setProducts(List<EasyBuyProduct> products) {
        this.products = products;
    }
}
