package com.tangcheng.datasources.aop.config;

import com.tangcheng.datasources.aop.config.util.DatabaseType;
import com.tangcheng.datasources.aop.config.util.DynamicDataSource;
import com.tangcheng.datasources.aop.util.BaseMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * springboot集成mybatis的基本入口
 * 1）创建数据源(如果采用的是默认的tomcat-jdbc数据源，则不需要)
 * 2）创建SqlSessionFactory
 * 3）配置事务管理器，除非需要使用事务，否则不用配置
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class MyBatisConfig {

    @Bean
    public AbstractRoutingDataSource routingDataSource(@Qualifier("test1DataSource")DataSource test1DataSource,
                                                       @Qualifier("test2DataSource")DataSource test2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.TEST1, test1DataSource);
        targetDataSources.put(DatabaseType.TEST2, test2DataSource);

        AbstractRoutingDataSource routingDataSource = new DynamicDataSource();
        routingDataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        routingDataSource.setDefaultTargetDataSource(test2DataSource);// 默认的datasource设置为myTestDbDataSource
        return routingDataSource;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.tangcheng.datasources.aop.mapper");
        Properties props = new Properties();
        props.setProperty("IDENTITY", "MYSQL");
        props.setProperty("notEmpty", "true");
        scannerConfigurer.setProperties(props);
        scannerConfigurer.setMarkerInterface(BaseMapper.class);
        return scannerConfigurer;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(AbstractRoutingDataSource routingDataSource) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(routingDataSource);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        factoryBean.setTypeAliasesPackage("com.tangcheng.datasources.aop.model");// 指定基包
        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));//
        return factoryBean.getObject();
    }


}