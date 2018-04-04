package com.ttt.demo.tkbatis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/3/31 下午6:33
 */
@Data
@Table(name = "t_user")
public class User {
    @Id
    @Column(name = "id")
    private int id;
    private String username;
}
