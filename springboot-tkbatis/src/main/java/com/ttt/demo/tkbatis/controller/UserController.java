package com.ttt.demo.tkbatis.controller;

import com.ttt.demo.tkbatis.entity.User;
import com.ttt.demo.tkbatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/31 下午6:41
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list(){
        return userService.queryPage(1,1);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id){
        return userService.getUserById(id);
    }
}
