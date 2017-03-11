package com.tangcheng.datasources.aop.controller;

import com.tangcheng.datasources.aop.config.ReadOnly;
import com.tangcheng.datasources.aop.model.UserInfo;
import com.tangcheng.datasources.aop.service.Test1Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tang.cheng on 2017/3/11.
 */
@RestController
public class Test1Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test1Controller.class);

    @Autowired
    private Test1Service test1Service;

    @ReadOnly
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public List<UserInfo> selectUser() {
        LOGGER.info("查询第一个数据源");
        return test1Service.getUserInfo();
    }

    @ReadOnly
    @RequestMapping(value = "/test1/exception", method = RequestMethod.POST)
    public List<UserInfo> test1WriteWithException(@RequestParam(value = "hasError") Boolean hasError) {
        LOGGER.info("查询第一个数据源");
        test1Service.postNew(hasError);
        return test1Service.getUserInfo();
    }


}
