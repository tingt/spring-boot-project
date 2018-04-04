package com.ttt.dynamic.datasource.db.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author tutingting
 * @date 2018/3/24 下午5:51
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.ttt.dynamic.datasource.dao.mapper")
public class DynamicDataSourceConfiguration {
    private static Logger log= LoggerFactory.getLogger(DynamicDataSourceConfiguration.class);
    static String mapperLocations="classpath:mapper/*Mapper.xml";

    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Primary
    @Bean(name = "studyMasterDataSource")
    @ConfigurationProperties(prefix = "druid.study.master")
    public DataSource studyMasterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "studySlaveDataSource")
    @ConfigurationProperties(prefix = "druid.study.slave")
    public DataSource studySlaveDataSource1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "productMasterDataSource")
    @ConfigurationProperties(prefix = "druid.product.master")
    public DataSource productMasterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "productSlaveDataSource")
    @ConfigurationProperties(prefix = "druid.product.slave")
    public DataSource productSlaveDataSource1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 将所有数据源放入AbstractRoutingDataSource中，同时设置默认数据源
     * @return
     */
    @Bean(name = "dataSource")
    public AbstractRoutingDataSource dataSource() {
        MasterSlaveRoutingDataSource proxy = new MasterSlaveRoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap<>();
        targetDataResources.put("productMasterDataSource", productMasterDataSource());
        targetDataResources.put("productSlaveDataSource", productSlaveDataSource1());
        targetDataResources.put("studyMasterDataSource", studyMasterDataSource());
        targetDataResources.put("studySlaveDataSource", studySlaveDataSource1());
        proxy.setDefaultTargetDataSource(studyMasterDataSource());
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }

    /**
     * 配置mapper所在location. 类上通过mapperscan配置mapper所在包。也可在appliation.properties中配置
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dataSource") AbstractRoutingDataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocations));
        return sessionFactory.getObject();
    }
}