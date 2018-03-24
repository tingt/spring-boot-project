package com.ttt.demo.web;

import com.ttt.demo.utils.LocaleMessageSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tutingting on 18-1-10.
 */
@Controller
public class HelloController {
    @Autowired
    private LocaleMessageSourceUtil messageSourceUtil;

    @RequestMapping("/")
    public String hello() {
        String welcome = messageSourceUtil.getMessage("hello.world",new Object[]{"ttt","dsw"});
        System.out.println(welcome);
        return "hello";
    }

    @RequestMapping("/user")
    public String viewUser(){
        return "user";
    }
}
