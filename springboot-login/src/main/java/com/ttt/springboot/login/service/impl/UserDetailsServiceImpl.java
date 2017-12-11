package com.ttt.springboot.login.service.impl;

import com.ttt.springboot.login.model.vo.CurrentUser;
import com.ttt.springboot.login.model.vo.UserVO;
import com.ttt.springboot.login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by tutingting on 17-12-28.
 * @author tutingting
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserVO user=userService.getUserByName(s).orElseThrow(() -> new UsernameNotFoundException(String.format("User with name=%s was not found",s)));
        return new CurrentUser(user);
    }
}
