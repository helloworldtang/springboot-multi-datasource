package com.tangcheng.datasources.aop.business.db2;


import com.tangcheng.datasources.aop.mapper.UserDetailMapper;
import com.tangcheng.datasources.aop.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tang.cheng on 2017/3/9.
 */
@Repository
public class Test2Biz {

    @Autowired
    private UserDetailMapper userDetailMapper;

    public List<UserDetail> selectAll() {
        return userDetailMapper.selectAll();
    }

    public void postNew(String name, String qq) {
        UserDetail userDetail = new UserDetail();
        userDetail.setName(name);
        userDetail.setQq(qq);
        userDetailMapper.insert(userDetail);
    }


}
