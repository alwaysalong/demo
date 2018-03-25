package com.sh.pri.service.impl;

import com.sh.pri.dao.IQueryUserInfoDao;
import com.sh.pri.dao.IUserInfoDao;
import com.sh.pri.dto.UserInfoDto;
import com.sh.pri.service.IQueryUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2018/3/23.
 */
@Service
public class QueryUserInfoServiceImpl implements IQueryUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public UserInfoDto queryUserInfo(String userName) {

        UserInfoDto userInfoDto = userInfoDao.queryUserInfo(userName);
        return userInfoDto;
    }
}
