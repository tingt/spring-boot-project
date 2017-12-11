package com.ttt.springboot.login.web;

import com.ttt.springboot.login.model.vo.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by tutingting on 17-12-28.
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication){
        return (authentication==null)? null: (CurrentUser) authentication.getPrincipal();
    }
}
