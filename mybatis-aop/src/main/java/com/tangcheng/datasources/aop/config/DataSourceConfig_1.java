package com.tangcheng.datasources.aop.config;

import com.tangcheng.datasources.aop.config.util.DatabaseType;
import com.tangcheng.datasources.aop.config.util.DynamicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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
    public DataSource test2DataSource() throws Exception {
        return DataSourceBuilder.create().type(type).build();
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
