package com.ttt.demo.boottest.service;

import com.ttt.demo.boottest.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/30 下午8:31
 */
public class UserServiceTest extends BaseServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUser(){
        User user=userService.getUser(1);
        Assert.assertThat(user.getUsername(),is("ttt"));
    }
}
