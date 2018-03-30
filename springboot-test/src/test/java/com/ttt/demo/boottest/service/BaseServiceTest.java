package com.ttt.demo.boottest.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/30 下午8:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Profile({ "test" })
public class BaseServiceTest {
}
