package com.tangcheng.datasources.aop.config;

import com.tangcheng.datasources.aop.config.util.DatabaseContextHolder;
import com.tangcheng.datasources.aop.config.util.DatabaseType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by tang.cheng on 2017/3/10.
 */
@Aspect
@Component
public class ReadOnlyAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadOnlyAspect.class);

    @Around("@annotation(ReadOnly)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            LOGGER.info("=============set db to test1=============");
            DatabaseContextHolder.setDatabaseType(DatabaseType.TEST1);
            return proceedingJoinPoint.proceed();
        } finally {
            DatabaseContextHolder.clearDbType();
            LOGGER.info("=============restore db connection=============");
        }
    }

}
