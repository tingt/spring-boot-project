package com.ttt.demo.tkbatis.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author : tutingting
 * @version : V2.0.0
 * @description:
 * @date : 2018/4/2 下午3:27
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
