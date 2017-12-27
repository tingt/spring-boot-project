package com.ttt.springboot.mybatis.controller;

import com.ttt.springboot.mybatis.domain.Course;
import com.ttt.springboot.mybatis.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tutingting on 17-12-27.
 */
@RestController
public class CourseRestController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value= "/api/course")
    public Course findCourseById(@RequestParam(value="id",required = true)Integer id){
        return courseService.findById(id);
    }
}
