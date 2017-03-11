package com.tangcheng.datasources.aop.business.db1;

import com.tangcheng.datasources.aop.mapper.UserInfoMapper;
import com.tangcheng.datasources.aop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tang.cheng on 2017/3/9.
 */
@Repository
public class Test1Biz {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> selectAll() {
        return userInfoMapper.selectAll();
    }

    public void postNew(String username) {
        UserInfo record = new UserInfo();
        record.setUsername(username);
        userInfoMapper.insert(record);
    }
}
