package com.ttt.demo.boottest.mapper;

import com.ttt.demo.boottest.entity.User;

import java.util.List;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/30 下午3:20
 */
public interface UserMapper {
    User getUserById(int id);
    List<User> listAllUser();
    void updateUser(User user);
    void deleteUser(int id);
    void addUser(User user);
}
