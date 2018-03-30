package com.ttt.demo.boottest.service;

import com.ttt.demo.boottest.entity.User;
import com.ttt.demo.boottest.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/30 下午2:44
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUser(int id){
        return userMapper.getUserById(id);
    }

    public void addUser(User user){
        userMapper.addUser(user);
    }

    public List<User> listAllUser(){
        return userMapper.listAllUser();
    }

    public void delete(int id){
        userMapper.deleteUser(id);
    }

    public void update(User user){
        userMapper.updateUser(user);
    }
}
