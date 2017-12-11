package com.ttt.springboot.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created by tutingting on 17-12-28.
 */
@Controller
public class LoginController {
    @RequestMapping("/")
    public String homePage(){
        return "login/home";
    }

    @RequestMapping("/login")
    public ModelAndView loginPage(@RequestParam Optional<String> error){
        return new ModelAndView("login/login","error",error);
    }

    @RequestMapping("/403")
    public String error403(){
        return "error";
    }
}
