package com.ttt.springboot.mybatis.dao;

import com.ttt.springboot.mybatis.domain.Course;
import org.apache.ibatis.annotations.Param;

/**
 * Created by tutingting on 17-12-27.
 */
public interface CourseDao {
    /**
     * 通过id获取课程信息
     * @param id
     * @return
     */
    Course findById(@Param("id") Integer id);
}
