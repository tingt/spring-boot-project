package com.ttt.springboot.mybatis.domain;

/**
 * Created by tutingting on 17-12-27.
 */
public class Course {
    private int id;
    private String courseName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
