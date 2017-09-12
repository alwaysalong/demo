package com.sh.pri.dao;

import org.apache.ibatis.annotations.Param;

import com.sh.pri.pojo.UserInfo;

public interface UserInfoDao {

	/**
	 * 查询用户信息
	 */
	public UserInfo queryUserInfo(@Param("userName") String userName, @Param("passWord") String passWord);
	
	/**
	 * 查询用户是否存在
	 */
	public  UserInfo queryByName(@Param("userName") String userName);
	
	
	/**
	 * 新增用户
	 */
	public Long addUser(@Param("userName") String userName, @Param("passWord") String passWord);
	
	
	/**
	 * 删除用户,逻辑删除
	 * 
	 */
	public int deleUser(@Param("userName") String userName, @Param("passWord") String passWord);
	
	/**
	 * 修改密码
	 */
	public int updatePWD(@Param("userName") String userName, @Param("passWord") String passWord);

}
