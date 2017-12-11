package com.ttt.springboot.login.web;

import com.ttt.springboot.login.common.CommonResponse;
import com.ttt.springboot.login.model.entity.User;
import com.ttt.springboot.login.model.vo.UserVO;
import com.ttt.springboot.login.service.IRoleService;
import com.ttt.springboot.login.service.IUserService;
import com.ttt.springboot.login.validator.UserCreateFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by tutingting on 17-12-27.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private UserCreateFormValidator userCreateFormValidator;


    @InitBinder("userVO")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    /**
     * 获取所有用户列表　
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView listAllUsers(){
        LOGGER.info("list all users");
        return new ModelAndView("/user/list","userList",userService.listAllUsers());
    }

    @RequestMapping("/{id}")
    public ModelAndView getOneUser(@PathVariable Long id){
        LOGGER.info("get one user, id:{} ",id);
        return new ModelAndView("/user/view","user",userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String addUser(Model model){
        LOGGER.info("add user. ");
        if(!model.containsAttribute("userVO")){
            model.addAttribute("userVO",new UserVO());
        }
        model.addAttribute("roleList",roleService.listAllRoles());
        return "/user/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/doAdd",method = RequestMethod.POST)
    public String doAddUser(@Valid @ModelAttribute("userVO") UserVO userVO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        LOGGER.debug("Processing user create form={}, bindingResult={}", userVO, bindingResult);
        //验证失败时重定向到新的页面,此时需要把错误信息set到重定向的页面中
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userVO", bindingResult);
            redirectAttributes.addFlashAttribute("userVO",userVO);
            return "redirect:/user/add";
        }
        try {
            userService.insert(userVO);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate userName", e);
            bindingResult.reject("user.exists", "user already exists");
            return "user/add";
        }
        return "redirect:/user/list";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal,#id)")
    @RequestMapping("/{id}/update")
    public String  updateUser(@PathVariable Long id,Model model){
        LOGGER.info("update user.");
        model.addAttribute("user",userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
        model.addAttribute("roleList",roleService.listAllRoles());
        return "/user/update";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal,#id)")
    @RequestMapping("/doUpdate")
    public String doUpdateUser(User user){
        LOGGER.debug("Processing user update ", user);
        try {
            userService.update(user);
        } catch (DataIntegrityViolationException e) {
            return "user/update";
        }
        return "redirect:/user/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.POST)
    public CommonResponse delete(@PathVariable("id") Long id) {
        LOGGER.debug("Processing user delete ");
        userService.delete(id);
        return CommonResponse.successResponse("删除用户成功");
    }
}
