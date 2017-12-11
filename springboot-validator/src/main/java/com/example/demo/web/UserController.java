package com.example.demo.web;

import com.example.demo.validator.EmailValidator;
import com.example.demo.validator.UserValidator;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tutingting on 18-1-2.
 */
@Controller
@RequestMapping("/user")
public class UserController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private EmailValidator emailValidator;

    @InitBinder("userVO")
    public void dataBinding(WebDataBinder binder){
        binder.addValidators(userValidator);
        binder.addValidators(emailValidator);
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); //对输入的日期严格解析
        binder.registerCustomEditor(Date.class,"birth",new CustomDateEditor(dateFormat,false));   //false是否允许为空
    }

    @RequestMapping(value = "/")
    public ModelAndView user(){
        return new ModelAndView("user","userVO",new UserVO());
    }

    @PostMapping(value = "/save")
    public String createUser(@ModelAttribute("userVO")@Valid UserVO userVO, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("userVO",userVO);
            return "user";
        }
        return "redirect:/results";
    }
}
