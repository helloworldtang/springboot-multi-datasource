package com.tangcheng.datasources.aop.service;

import com.tangcheng.datasources.aop.business.db2.Test2Biz;
import com.tangcheng.datasources.aop.cache.Test2Cache;
import com.tangcheng.datasources.aop.model.UserDetail;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Test2ServiceImpl implements Test2Service {

    @Autowired
    private Test2Biz test2Biz;

    @Autowired
    private Test2Cache test2Cache;


    public List<UserDetail> getUserDetail() {
        return test2Biz.selectAll();
    }

    @Override
    public void postNew(Boolean hasError) {
        String name = "test2: " + LocalDateTime.now().toString("yyyy-MM-dd HH:mm:ss sss");
        String qq = String.valueOf(Math.abs(Math.round(Math.random())));
        test2Biz.postNew(name, qq);
        test2Cache.postNew(name, qq);

        if (hasError) {
            throw new RuntimeException("test2.Rollback when exception happened");
        }
    }


}
