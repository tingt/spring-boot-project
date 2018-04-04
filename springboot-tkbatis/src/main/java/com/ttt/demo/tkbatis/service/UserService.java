package com.ttt.demo.tkbatis.service;

import com.github.pagehelper.PageHelper;
import com.ttt.demo.tkbatis.entity.User;
import com.ttt.demo.tkbatis.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/31 下午6:37
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUserById(int id) {
       return userMapper.selectByPrimaryKey(id);
    }

    public List<User> queryPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<User> list = userMapper.selectAll();
        return list;
    }

}
