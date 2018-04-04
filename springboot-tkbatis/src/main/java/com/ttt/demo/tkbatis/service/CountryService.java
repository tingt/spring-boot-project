package com.ttt.demo.tkbatis.service;

import com.github.pagehelper.PageHelper;
import com.ttt.demo.tkbatis.entity.Country;
import com.ttt.demo.tkbatis.mapper.CountryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/4/2 下午9:02
 */
@Service
public class CountryService {
    @Resource
    private CountryMapper countryMapper;

    public List<Country> getAllByPage(){
        PageHelper.startPage(1, 10);
        List<Country> countries = countryMapper.selectAll();
        return countries;
    }

    public Country getCountry(int id){
        return countryMapper.selectByPrimaryKey(id);
    }

    public void insert(){
        Country country=new Country();
        country.setCountryname("test");
        country.setCountrycode("t");
        countryMapper.insert(country);
    }

    public void update(int id){
        Country country=new Country();
        country.setId(id);
        country.setCountryname("newtest");
        countryMapper.updateByPrimaryKeySelective(country);
    }
}
