package com.ttt.dubbo.consumer.controller;

import com.ttt.dubbo.consumer.service.DemoCousumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tutingting
 * @date 2018/3/17 下午5:47
 */
@RestController
public class DemoConsumerController {

    @Autowired
    private DemoCousumerService demoCousumerService;

    @RequestMapping(value = "/sayHello",method = RequestMethod.POST)
    public String sayHello(@RequestParam String name) {
        return demoCousumerService.sayHello(name);
    }

    @RequestMapping(value = "/getName",method = RequestMethod.POST)
    public String getUserName(@RequestParam int id) {
        return demoCousumerService.getUserName(id);
    }

    @RequestMapping(value = "test",method = RequestMethod.POST)
    public String testService() {
        return "hello,world";
    }
}
