package com.ttt.demo.boottest.service;

import com.ttt.demo.boottest.SpringbootTestApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/30 下午8:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootTestApplication.class)
@Transactional
@Profile({ "test" })
public class BaseServiceTest {
}
