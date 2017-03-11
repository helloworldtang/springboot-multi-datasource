package com.tangcheng.datasources.aop.controller;

import com.tangcheng.datasources.aop.model.UserDetail;
import com.tangcheng.datasources.aop.service.Test2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Test2Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test2Controller.class);

    @Autowired
    private Test2Service test2Service;


    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public List<UserDetail> userDetail() {
        LOGGER.info("查询第二个数据源");
        return test2Service.getUserDetail();
    }


    @RequestMapping(value = "/test2/exception", method = RequestMethod.POST)
    public List<UserDetail> test2WriteWithException(@RequestParam(value = "hasError") Boolean hasError) {
        LOGGER.info("查询第二个数据源");
        test2Service.postNew(hasError);
        return test2Service.getUserDetail();
    }

}