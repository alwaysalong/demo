package com.sh.pri.service.impl;

import com.sh.pri.dao.IUserInfoDao;
import com.sh.pri.dto.UserInfoDto;
import com.sh.pri.service.IQueryUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2018/3/23.
 */
@Service
public class QueryUserInfoServiceImpl implements IQueryUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public List<UserInfoDto> queryUserInfo(String userName) {

        List<UserInfoDto> userInfoDto = userInfoDao.queryUserInfo(userName);
        return userInfoDto;
    }
}
