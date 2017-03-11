package com.tangcheng.datasources.aop.service;

import com.tangcheng.datasources.aop.business.db1.Test1Biz;
import com.tangcheng.datasources.aop.cache.Test1Cache;
import com.tangcheng.datasources.aop.model.UserInfo;
import com.tangcheng.datasources.aop.service.Test1Service;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Test1ServiceImpl implements Test1Service {

    @Autowired
    private Test1Biz test1Biz;

    @Autowired
    private Test1Cache test1Cache;


    @Override
    public List<UserInfo> getUserInfo() {
        return test1Biz.selectAll();
    }


    @Override
    public void postNew(Boolean hasError) {

        String username = "test1:" + LocalDateTime.now().toString("yyyy-MM-dd HH:mm:ss sss");
        test1Biz.postNew(username);
        test1Cache.postNew(username);

        if (hasError) {
            throw new RuntimeException("test1.Rollback when exception happened");
        }
    }


}