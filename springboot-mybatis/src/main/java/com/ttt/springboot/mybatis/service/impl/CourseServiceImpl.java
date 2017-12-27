package com.ttt.springboot.mybatis.service.impl;

import com.ttt.springboot.mybatis.dao.CourseDao;
import com.ttt.springboot.mybatis.domain.Course;
import com.ttt.springboot.mybatis.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tutingting on 17-12-27.
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

    public Course findById(Integer id){
        return courseDao.findById(id);
    }
}
