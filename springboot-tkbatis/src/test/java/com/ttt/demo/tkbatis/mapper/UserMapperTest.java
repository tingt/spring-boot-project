package com.ttt.demo.tkbatis.mapper;

import com.ttt.demo.tkbatis.SpringbootTkbatisApplication;
import com.ttt.demo.tkbatis.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.hamcrest.core.Is.is;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/4/2 下午3:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringBootTest(classes = SpringbootTkbatisApplication.class)
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        User user=userMapper.selectByPrimaryKey(1);
        Assert.assertThat(user.getUsername(),is("ttt"));
    }

}