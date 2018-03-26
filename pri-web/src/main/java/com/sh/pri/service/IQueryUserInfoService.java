package com.sh.pri.service;

import com.sh.pri.dto.UserInfoDto;

import java.util.List;

/**
 * Created by admin on 2018/3/23.
 */
public interface IQueryUserInfoService {

    List<UserInfoDto> queryUserInfo(String userName);
}
