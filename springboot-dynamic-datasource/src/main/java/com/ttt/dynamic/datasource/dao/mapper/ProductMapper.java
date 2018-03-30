package com.ttt.dynamic.datasource.dao.mapper;

import com.ttt.dynamic.datasource.dao.entity.Product;

import java.util.List;

/**
 * @author tutingting
 * @date 2018/3/24 下午6:39
 */
public interface ProductMapper{
    List<Product> listMaster();
    List<Product> listSlave();
    void insert();
}
