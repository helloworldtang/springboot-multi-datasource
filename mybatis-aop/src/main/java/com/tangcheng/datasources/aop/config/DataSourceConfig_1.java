package com.tangcheng.datasources.aop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Created by tang.cheng on 2017/3/11.
 */
@Profile("ds1")
@Configuration
public class DataSourceConfig_1 {
    @Value("${dbpool.type}")
    private Class<? extends DataSource> type;

    @Bean("test1DataSource")
    @ConfigurationProperties(prefix = "dbpool.database1")
    public DataSource test1DataSource() throws Exception {
        return DataSourceBuilder.create().type(type).build();
    }

    @Bean("test2DataSource")
    @ConfigurationProperties(prefix = "dbpool.database2")
    @Primary
    public DataSource test2DataSource() throws Exception {
        return DataSourceBuilder.create().type(type).build();
    }


}
