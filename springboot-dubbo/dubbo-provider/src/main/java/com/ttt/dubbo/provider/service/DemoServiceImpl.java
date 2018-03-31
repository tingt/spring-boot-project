package com.ttt.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ttt.dubbo.api.DemoService;

/**
 * @author : tutingting
 * @date : 2018/3/31 上午11:34
 */
@Service(version = "1.0.0",group = "demo-group")
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
