package com.example.demo.validator;

import com.example.demo.vo.UserVO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by tutingting on 18-1-2.
 */
@Component
public class UserValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserVO userVO=(UserVO)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "","Username is empty");
     //   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is empty");
        if(!userVO.getPassword().equals(userVO.getConfirmPassword())){
            errors.rejectValue("confirmPassword","","Password is not the same as above");
        }
        if(userVO.getPassword().length()<5){
            errors.reject("password","Password is less than 5");
        }
    }
}
