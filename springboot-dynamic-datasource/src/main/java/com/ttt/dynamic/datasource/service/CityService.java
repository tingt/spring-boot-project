package com.ttt.dynamic.datasource.service;

import com.ttt.dynamic.datasource.dao.entity.City;
import com.ttt.dynamic.datasource.dao.entity.Product;
import com.ttt.dynamic.datasource.db.annotation.TargetDataSource;

import java.util.List;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/30 下午4:47
 */
public interface CityService {
    @TargetDataSource("studySlaveDataSource")
    List<City> getCityList();

    @TargetDataSource("studyMasterDataSource")
    List<City> getCityList2();

    @TargetDataSource("studyMasterDataSource")
    void insertCity() throws  Exception;

    @TargetDataSource("studyMasterDataSource")
   List<Product> readFromDifferDs();

    @TargetDataSource("studyMasterDataSource")
    List<Product> writeAndRead() throws Exception;

    @TargetDataSource("studyMasterDataSource")
    List<City> writeAndReadCity() throws Exception;

    @TargetDataSource("studyMasterDataSource")
    List<Product> writeAndReadByService() throws Exception;

    @TargetDataSource("studyMasterDataSource")
    void writeDifDs() throws Exception;

    @TargetDataSource("studyMasterDataSource")
     void writeDifDsNew() throws Exception;

    @TargetDataSource("productMasterDataSource")
    List<Product> getProductMaster();

}
