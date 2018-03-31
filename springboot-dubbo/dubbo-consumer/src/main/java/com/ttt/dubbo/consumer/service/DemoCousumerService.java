package com.ttt.dubbo.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ttt.dubbo.api.DemoService;
import com.ttt.dubbo.consumer.api.UserApi;
import org.springframework.stereotype.Service;

/**
 * @author tutingting
 * @date 2018/3/19 上午11:29
 */
@Service
public class DemoCousumerService {
    @Reference(version = "1.0.0",group = "demo-group")
    private DemoService demoService;

    @Reference(version = "1.0.0",group = "user-group")
    private UserApi userApi;

    public String sayHello(String name) {
        return demoService.sayHello(name);
    }

    public String getUserName(int id) {
        return userApi.getUserName(id);
    }
}
