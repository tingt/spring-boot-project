package com.ttt.demo.tkbatis.service;

import com.ttt.demo.tkbatis.SpringbootTkbatisApplication;
import com.ttt.demo.tkbatis.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/4/2 下午2:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootTkbatisApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUserById() {
        User user=userService.getUserById(1);
        Assert.assertThat(user.getUsername(),is("ttt"));
    }

    @Test
    public void queryPage() {
    }
}