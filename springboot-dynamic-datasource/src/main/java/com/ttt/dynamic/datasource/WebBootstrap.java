package com.ttt.dynamic.datasource;

import com.ttt.dynamic.datasource.db.config.DynamicDataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author tutingting
 * @date 2018/3/24 下午5:51
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfiguration.class}) // 注册动态多数据源
public class WebBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(WebBootstrap.class, args);
    }
}

