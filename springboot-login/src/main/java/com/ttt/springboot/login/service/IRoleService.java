package com.ttt.springboot.login.service;

import com.ttt.springboot.login.model.entity.Role;

import java.util.List;

/**
 * Created by tutingting on 17-12-28.
 */
public interface IRoleService {
    List<Role> listAllRoles();
}
