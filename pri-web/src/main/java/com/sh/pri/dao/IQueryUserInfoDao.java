package com.sh.pri.dao;

import com.sh.pri.dto.UserInfoDto;
import org.apache.ibatis.annotations.Param;

/**
 * Created by admin on 2018/3/23.
 */
public interface IQueryUserInfoDao {

    /**
     * 查询用户信息
     * @param userName
     * @return
     */
    UserInfoDto queryUserInfo(@Param("userName") String userName);
}
