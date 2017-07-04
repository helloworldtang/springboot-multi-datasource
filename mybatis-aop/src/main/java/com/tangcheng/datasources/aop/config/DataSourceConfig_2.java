package com.tangcheng.datasources.aop.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
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
    @Primary
    public DataSource test2DataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("dbpool.database2.driver-class-name"));
        props.put("url", environment.getProperty("dbpool.database2.url"));
        props.put("username", environment.getProperty("dbpool.database2.username"));
        props.put("password", environment.getProperty("dbpool.database2.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }


}
