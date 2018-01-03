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
public class EmailValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserVO.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        UserVO userVO = (UserVO)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "","Email is empty");
        if (!userVO.getEmail().contains("@")) {
            errors.rejectValue("email","", "Email is not valid.");
        }
    }
}
