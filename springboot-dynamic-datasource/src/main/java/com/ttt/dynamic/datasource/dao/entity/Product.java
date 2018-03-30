package com.ttt.dynamic.datasource.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tutingting
 * @date 2018/3/24 下午6:37
 */
public class Product {
    private Integer id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
