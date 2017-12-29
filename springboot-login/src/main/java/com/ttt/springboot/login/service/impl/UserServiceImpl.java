package com.ttt.springboot.login.service.impl;

import com.ttt.springboot.login.model.vo.UserVO;
import com.ttt.springboot.login.model.dao.UserDao;
import com.ttt.springboot.login.model.entity.User;
import com.ttt.springboot.login.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by tutingting on 17-12-27.
 */
@Service
@Transactional
public class UserServiceImpl  implements IUserService{
    @Autowired
    private UserDao userDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<UserVO> listAllUsers() {
        return userDao.listAllUsers();
    }

    @Override
    public Optional<UserVO> getUserById(Long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userDao.getUserById(id));
    }

    @Override
    public Optional<UserVO> getUserByName(String name) {
        return Optional.ofNullable(userDao.getUserByName(name));
    }

    @Override
    public void insert(UserVO userVO) {
        User user=new User();
        user.setUserName(userVO.getUserName());
        user.setEmail(userVO.getEmail());
        user.setSex(userVO.getSex());
        user.setRoleId(userVO.getRoleId());
        user.setPassword(new BCryptPasswordEncoder().encode(userVO.getPassword()));
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
