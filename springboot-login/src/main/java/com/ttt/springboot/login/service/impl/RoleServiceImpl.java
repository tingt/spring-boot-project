package com.ttt.springboot.login.service.impl;

import com.ttt.springboot.login.model.dao.RoleDao;
import com.ttt.springboot.login.model.entity.Role;
import com.ttt.springboot.login.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tutingting on 17-12-28.
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> listAllRoles() {
        return roleDao.listAllRoles();
    }
}
