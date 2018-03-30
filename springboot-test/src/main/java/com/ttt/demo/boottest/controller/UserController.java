package com.ttt.demo.boottest.controller;

import com.ttt.demo.boottest.entity.User;
import com.ttt.demo.boottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/30 下午3:23
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<User> listUsers(){
        return userService.listAllUser();
    }

    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody User user){
        userService.addUser(user);
        return "success";
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable int id){
        userService.delete(id);
        return "success";
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateUser(@RequestBody User user){
        userService.update(user);
        return "success";
    }

}
