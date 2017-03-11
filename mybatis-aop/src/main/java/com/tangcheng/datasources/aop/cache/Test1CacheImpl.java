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
public class Test1CacheImpl implements Test1Cache {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test1CacheImpl.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void postNew(String userName) {
        if (redisTemplate.hasKey(userName)) {
            LOGGER.warn("{} already exists.", userName);
            return;
        }
        redisTemplate.boundValueOps(userName).set(userName);
        Boolean hasKey = redisTemplate.hasKey(userName);
        LOGGER.info("hasKey:{}", hasKey);
        if (hasKey) {
            LOGGER.error("should be false.hasKey:{}", hasKey);
        }
    }
}
