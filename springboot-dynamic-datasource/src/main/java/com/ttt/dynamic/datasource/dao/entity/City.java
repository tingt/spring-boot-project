package com.ttt.dynamic.datasource.dao.entity;

/**
 * @author tutingting
 * @date 2018/3/24 下午6:36
 */
public class City {
    private Long id;

    private String name;

    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
