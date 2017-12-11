package com.ttt.springboot.login.model.dao;

import com.ttt.springboot.login.model.vo.UserVO;
import com.ttt.springboot.login.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by tutingting on 17-12-27.
 */
public interface UserDao {
    List<UserVO> listAllUsers();

    UserVO getUserById(@Param("id")Long id);

    UserVO getUserByName(@Param("userName")String userName);

    void insert(User user);

    void update(User user);

    void delete(@Param("id")Long id);
}
