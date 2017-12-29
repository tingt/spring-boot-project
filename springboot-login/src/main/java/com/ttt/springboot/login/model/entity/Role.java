package com.ttt.springboot.login.model.entity;

import java.io.Serializable;

/**
 * Created by tutingting on 17-12-27.
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
