package com.example.demo.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by tutingting on 18-1-2.
 */
public class UserVO {
    @Size(min=2)
    private String name;
    @NotEmpty(message = "cannot be null")
    @Pattern(regexp = "\\S+", message = "Spaces are not allowed")
    private String password;
    @NotEmpty
    @Email(message = "Email is not allowed")
    private String email;
    private Date birth;
    @NotEmpty
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
