package com.ttt.springboot.mybatis.service;

import com.ttt.springboot.mybatis.domain.Course;

/**
 * Created by tutingting on 17-12-27.
 */
public interface CourseService {
    Course findById(Integer id);
}
