package com.ttt.dynamic.datasource.db.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * @author tutingting
 * @date 2018/3/24 下午6:05
 */
public class MasterSlaveRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
