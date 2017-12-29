package com.ttt.springboot.login.service;

import com.ttt.springboot.login.model.vo.CurrentUser;

/**
 * Created by tutingting on 17-12-28.
 */
public interface ICurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
