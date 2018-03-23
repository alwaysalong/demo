package com.sh.pri.service;

import com.sh.pri.dto.UserInfoDto;

/**
 * Created by admin on 2018/3/23.
 */
public interface IQueryUserInfoService {

    UserInfoDto queryUserInfo(String userName);
}
