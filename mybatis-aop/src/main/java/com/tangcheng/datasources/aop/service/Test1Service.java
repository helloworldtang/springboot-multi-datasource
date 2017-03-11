package com.tangcheng.datasources.aop.service;


import com.tangcheng.datasources.aop.model.UserInfo;

import java.util.List;

public interface Test1Service {

    List<UserInfo> getUserInfo();

    void postNew(Boolean hasError);
}
