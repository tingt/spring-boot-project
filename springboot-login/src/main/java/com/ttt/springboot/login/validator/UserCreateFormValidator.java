package com.ttt.springboot.login.validator;

import com.ttt.springboot.login.model.vo.UserVO;
import com.ttt.springboot.login.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final IUserService userService;

    @Autowired
    public UserCreateFormValidator(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserVO user = (UserVO) target;
    //    validatePasswords(errors, form);
        validateUserName(errors, user);
    }

    private void validateUserName(Errors errors, UserVO user) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userName","","UserName is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","","Password is empty");
        if(!user.getPassword().equals(user.getPasswordRepeated())){
            errors.reject("password","repeated password is not equal to the password");
        }
        if(userService.getUserByName(user.getUserName()).isPresent()){
            errors.reject("userName.exists", "User name already exists");
        }
    }
}
