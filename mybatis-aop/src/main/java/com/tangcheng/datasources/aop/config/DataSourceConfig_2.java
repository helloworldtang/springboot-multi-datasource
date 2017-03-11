package com.tangcheng.datasources.aop.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.tangcheng.datasources.aop.config.util.DatabaseType;
import com.tangcheng.datasources.aop.config.util.DynamicDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by tang.cheng on 2017/3/11.
 */
@Profile("ds2")
@Configuration
public class DataSourceConfig_2 implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    @Bean
    public DataSource test1DataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("dbpool.database1.driver-class-name"));
        props.put("url", environment.getProperty("dbpool.database1.url"));
        props.put("username", environment.getProperty("dbpool.database1.username"));
        props.put("password", environment.getProperty("dbpool.database1.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource test2DataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("dbpool.database2.driver-class-name"));
        props.put("url", environment.getProperty("dbpool.database2.url"));
        props.put("username", environment.getProperty("dbpool.database2.username"));
        props.put("password", environment.getProperty("dbpool.database2.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    @Primary
    public AbstractRoutingDataSource routingDataSource(DataSource test1DataSource, DataSource test2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.TEST1, test1DataSource);
        targetDataSources.put(DatabaseType.TEST2, test2DataSource);

        AbstractRoutingDataSource routingDataSource = new DynamicDataSource();
        routingDataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        routingDataSource.setDefaultTargetDataSource(test2DataSource);// 默认的datasource设置为myTestDbDataSource
        return routingDataSource;
    }

}
