package com.ttt.demo.boottest.controller;

import com.google.gson.Gson;
import com.ttt.demo.boottest.entity.User;
import com.ttt.demo.boottest.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/30 下午5:59
 */
public class UserControllerTest extends BaseControllerTest{
    @Autowired
    private UserService userService;

    @Test
    public void getUser() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("ttt"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void listUsers() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/user/list")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void add() throws Exception{
        User user=new User();
        user.setUsername("spring");
        Gson gson = new Gson();
        String json = gson.toJson(user);
        mvc.perform(MockMvcRequestBuilders.post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void deleteUser() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/user/delete/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void updateUser() throws Exception{
        User user=new User();
        user.setId(1);
        user.setUsername("spring");
        Gson gson = new Gson();
        String json = gson.toJson(user);
        mvc.perform(MockMvcRequestBuilders.post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}