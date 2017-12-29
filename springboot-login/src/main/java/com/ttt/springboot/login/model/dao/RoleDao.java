package com.ttt.springboot.login.model.dao;

import com.ttt.springboot.login.model.entity.Role;

import java.util.List;

/**
 * Created by tutingting on 17-12-27.
 */
public interface  RoleDao {
    List<Role> listAllRoles();
}
