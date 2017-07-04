package com.tangcheng.datasources.aop.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * Created by tang.cheng on 2017/3/11.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableTransactionManagement
public class TxConfig {

    @Bean
    public PlatformTransactionManager transactionManager(AbstractRoutingDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager transactionManager) {
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED");
        attributes.setProperty("put*", "PROPAGATION_REQUIRED");
        attributes.setProperty("post*", "PROPAGATION_REQUIRED");
        attributes.setProperty("delete*", "PROPAGATION_REQUIRED");
        return new TransactionInterceptor(transactionManager, attributes);
    }

    @Bean
    public AspectJExpressionPointcut aspectJExpressionPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.tangcheng.datasources.aop.service..*.*(..))");
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(PlatformTransactionManager transactionManager) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(aspectJExpressionPointcut());
        advisor.setAdvice(transactionInterceptor(transactionManager));
        return advisor;
    }

}
