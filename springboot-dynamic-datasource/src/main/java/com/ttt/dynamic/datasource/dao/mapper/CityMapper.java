package com.ttt.dynamic.datasource.dao.mapper;

import com.ttt.dynamic.datasource.dao.entity.City;

import java.util.List;

/**
 * @author tutingting
 * @date 2018/3/24 下午6:39
 */
public interface CityMapper{
    List<City> listSlave();
    List<City> listMaster();
    void insert(City city);

    void update(City city);
}
