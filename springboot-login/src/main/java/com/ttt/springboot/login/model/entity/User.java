package com.ttt.springboot.login.model.entity;

/**
 * Created by tutingting on 17-12-27.
 */
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotEmpty(message="姓名不能为空")
    private String userName;

    private Long roleId;
    @NotEmpty
    private String email;
    @NotEmpty
    @Length(min=6,message="密码长度不能小于6位")
    private String password;
    @NotNull
    private String sex;  //F: female ,M:male

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                '}';
    }
}