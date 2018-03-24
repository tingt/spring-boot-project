package com.ttt.springboot.profile.other.jar.controller;

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
public class OrderController {
    @Autowired
    private OtherConfig otherConfig;

    @RequestMapping(value = "/otherTest",method = RequestMethod.POST)
    public String viewTest(){
        return otherConfig.viewTest();
    }
}
