package com.ttt.springboot.profile.other.jar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author tutingting
 * @date 2018/3/22 上午11:26
 */
@Configuration
public class OtherConfig {
    @Value("${other.id}")
    private  String id;
    @Value("${other.key}")
    private  String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String viewTest(){
        return id+","+name;
    }


}
