package com.tangcheng.datasources.aop.service;

import com.tangcheng.datasources.aop.model.UserDetail;

import java.util.List;

public interface Test2Service {

    List<UserDetail> getUserDetail();

    void postNew(Boolean hasError);
}
