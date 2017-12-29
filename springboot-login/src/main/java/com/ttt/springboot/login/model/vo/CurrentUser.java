package com.ttt.springboot.login.model.vo;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * Created by tutingting on 17-12-28.
 */
public class CurrentUser extends User{
    private UserVO user;
    public CurrentUser(UserVO user){
        super(user.getUserName(),user.getPassword(), AuthorityUtils.createAuthorityList(user.getRoleName()));
        this.user=user;
    }

    public UserVO getUser(){
        return user;
    }
    public String getUserName(){
        return user.getUserName();
    }
    public String getRoleName(){
        return user.getRoleName();
    }
    public Long getId(){
        return user.getId();
    }
}
