package com.ttt.demo.tkbatis.service;

import com.github.pagehelper.PageInfo;
import com.ttt.demo.tkbatis.SpringbootTkbatisApplication;
import com.ttt.demo.tkbatis.entity.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.core.Is.is;
import org.junit.Assert;

import java.util.List;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/4/2 下午9:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootTkbatisApplication.class)
public class CountryServiceTest {
    @Autowired
    private CountryService countryService;
    @Test
    public void getAllByPage() {
        List<Country> countryList=countryService.getAllByPage();
        Assert.assertThat(countryList.size(),is(10));
        //用PageInfo对结果进行包装
        PageInfo page = new PageInfo(countryList);
        //测试PageInfo全部属性
        //PageInfo包含了非常全面的分页属性
        Assert.assertThat(page.getPageNum(),is(1));
        Assert.assertThat(page.getPageSize(),is(10));
        Assert.assertThat(page.getStartRow(),is(1));
        Assert.assertThat(page.getEndRow(),is(10));
        Assert.assertThat(page.getTotal(),is(183l));
        Assert.assertThat(page.getPages(),is(19));
        Assert.assertThat(page.getNavigateFirstPage(),is(1));
        Assert.assertThat(page.isIsFirstPage(),is(true));
        Assert.assertThat(page.isIsLastPage(),is(false));
        Assert.assertThat(page.isHasPreviousPage(),is(false));
        Assert.assertThat(page.isHasNextPage(),is(true));
    }

    @Test
    @Rollback
    public void insert(){
        countryService.insert();
        Country country=countryService.getCountry(184);
        System.out.println(country.toString());
    }

    @Test
    @Rollback
    public void update(){
       countryService.update(1);
        Country country=countryService.getCountry(1);
        System.out.println(country.toString());
    }
}