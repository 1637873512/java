package com.qhit.entity;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/12/9.
 */
public class EasyBuyProduct implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private EasyBuyProductCategory pc1;
    private EasyBuyProductCategory pc2;
    private EasyBuyProductCategory pc3;
    private String fileName;
    private int isDelete;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public EasyBuyProductCategory getPc1() {
        return pc1;
    }

    public void setPc1(EasyBuyProductCategory pc1) {
        this.pc1 = pc1;
    }

    public EasyBuyProductCategory getPc2() {
        return pc2;
    }

    public void setPc2(EasyBuyProductCategory pc2) {
        this.pc2 = pc2;
    }

    public EasyBuyProductCategory getPc3() {
        return pc3;
    }

    public void setPc3(EasyBuyProductCategory pc3) {
        this.pc3 = pc3;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

}
