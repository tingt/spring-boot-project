package com.ttt.springboot.login.service;

import com.ttt.springboot.login.model.vo.UserVO;
import com.ttt.springboot.login.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by tutingting on 17-12-27.
 */
public interface IUserService {
    List<UserVO> listAllUsers();
    Optional<UserVO> getUserById(Long id);
    Optional<UserVO> getUserByName(String name);
    void insert(UserVO userVO);
    void update(User user);
    void delete(Long id);
}
