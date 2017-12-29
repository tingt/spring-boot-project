package com.ttt.springboot.login.service.impl;

import com.ttt.springboot.login.model.vo.CurrentUser;
import com.ttt.springboot.login.service.ICurrentUserService;
import org.springframework.stereotype.Service;

/**
 * Created by tutingting on 17-12-28.
 */
@Service
public class CurrentUserServiceImpl implements ICurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser!=null && ("ADMIN".equals(currentUser.getRoleName())|| currentUser.getId().equals(userId));
    }
}
