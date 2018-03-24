package com.ttt.springboot.profile.controller;

import com.ttt.springboot.profile.config.ConfigTest;
import com.ttt.springboot.profile.config.ConfigTest2;
import com.ttt.springboot.profile.other.jar.config.OtherConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tutingting
 * @date 2018/3/22 下午2:49
 */
@RestController
public class TestController {
    @Autowired
    private ConfigTest configTest;
    @Autowired
    private ConfigTest2 configTest2;
    @Autowired
    private OtherConfig otherConfig;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String viewTest(){
        return configTest.viewTest();
    }

    @RequestMapping(value = "/test2",method = RequestMethod.POST)
    public String viewTest2(){
        return configTest2.viewTest();
    }

    @RequestMapping(value = "/test3",method = RequestMethod.POST)
    public String viewTest3(){
        return otherConfig.viewTest();
    }
}
