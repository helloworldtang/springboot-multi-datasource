package com.tangcheng.datasources.aop.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by tang.cheng on 2017/3/10.
 */
@Service
public class Test2CacheImpl implements Test2Cache {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test2CacheImpl.class);
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void postNew(String name, String qq) {
        if (redisTemplate.hasKey(name)) {
            LOGGER.warn("{} already exists.qq:{}", name, qq);
            return;
        }
        redisTemplate.boundValueOps(name).set(qq);
        Boolean hasKey = redisTemplate.hasKey(name);
        LOGGER.info("hasKey:{}", hasKey);
        if (hasKey) {
            LOGGER.error("should be false.hasKey:{}", hasKey);
        }
    }
}
