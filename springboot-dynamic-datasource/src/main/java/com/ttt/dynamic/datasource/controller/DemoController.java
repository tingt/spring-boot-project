package com.ttt.dynamic.datasource.controller;

import com.ttt.dynamic.datasource.dao.entity.City;
import com.ttt.dynamic.datasource.dao.entity.Product;
import com.ttt.dynamic.datasource.service.CityService;
import com.ttt.dynamic.datasource.service.impl.CityServiceImpl;
import com.ttt.dynamic.datasource.service2.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tutingting
 * @date 2018/3/26 下午1:46
 */
@RestController
public class DemoController {
    @Autowired
    private CityService cityService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/citySlave")
    @ResponseBody
    public List<City> getSlave(){
        return cityService.getCityList();
    }

    @RequestMapping("/cityMaster")
    @ResponseBody
    public List<City> getMaster(){
        return cityService.getCityList2();
    }

    @RequestMapping("/productSlave")
    @ResponseBody
    public List<Product> getSlave2(){
        return productService.getProductSlave();
    }

    @RequestMapping("/productMaster")
    @ResponseBody
    public List<Product> getMaster2(){
        return productService.getProductMaster();
    }


    @RequestMapping("/insertCity")
    @ResponseBody
    public String insertCity(){
        try{
            cityService.insertCity();
            return "success";
        }catch (Exception e){

        }
        return "failed";
    }

    @RequestMapping("/readFromDifferDs")
    @ResponseBody
    public List<Product> readFromDifferDs(){
        try{
            return cityService.readFromDifferDs();
        }catch (Exception e){

        }
        return new ArrayList<>();
    }

    @RequestMapping("/writeAndRead")
    @ResponseBody
    public List<Product> writeAndRead(){
        try{
            return cityService.writeAndRead();
        }catch (Exception e){

        }
        return new ArrayList<>();
    }

    @RequestMapping("/writeAndReadCity")
    @ResponseBody
    public List<City> writeAndReadCity(){
        try{
            return cityService.writeAndReadCity();
        }catch (Exception e){

        }
        return new ArrayList<>();
    }

    @RequestMapping("/writeAndReadByService")
    @ResponseBody
    public List<Product> writeAndReadByService(){
        try{
            return cityService.writeAndReadByService();
        }catch (Exception e){

        }
        return new ArrayList<>();
    }

    @RequestMapping("/writeDifDs")
    @ResponseBody
    public String writeDifDs(){
        try{
            cityService.writeDifDs();
            return "success";
        }catch (Exception e){

        }
        return "failed";
    }

    @RequestMapping("/writeDifDsNew")
    @ResponseBody
    public String writeDifDsNew(){
        try{
            cityService.writeDifDsNew();
            return "success";
        }catch (Exception e){

        }
        return "failed";
    }
}
