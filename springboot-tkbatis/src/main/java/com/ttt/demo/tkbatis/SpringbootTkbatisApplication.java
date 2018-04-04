package com.ttt.demo.tkbatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.ttt.demo.tkbatis.mapper")
public class SpringbootTkbatisApplication{
	public static void main(String[] args) {
		SpringApplication.run(SpringbootTkbatisApplication.class, args);
	}
}
