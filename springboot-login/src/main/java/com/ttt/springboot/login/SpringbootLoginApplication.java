package com.ttt.springboot.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ttt.springboot.login.model.dao")
public class SpringbootLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLoginApplication.class, args);
	}
}
