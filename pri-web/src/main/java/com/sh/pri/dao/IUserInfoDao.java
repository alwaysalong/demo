package com.sh.pri.dao;

import java.util.Date;
import java.util.List;

import com.sh.pri.dto.UserInfoDto;
import org.apache.ibatis.annotations.Param;

import com.sh.pri.pojo.TUserInfo;
public interface IUserInfoDao {

	/**
	 * 查询用户自己的信息
	 */
	public TUserInfo queryUserInfo1(@Param("userName") String userName, @Param("passWord") String passWord);
	
	
	/**
	 * 新增用户
	 */
	public Long addUser(@Param("userName") String userName, @Param("passWord") String passWord, @Param("sex") String sex);

	/**
	 * 添加最后登录ip地址
	 */
	public void addLastLoginIPAndTime(@Param("userName") String userName, @Param("lastLoginIpd") String lastLoginIp, @Param("lastLoginTime") Date lastLoginTime);

	/**
	 * 删除用户,逻辑删除
	 * 
	 */
	public int deleUser(@Param("userName") String userName, @Param("passWord") String passWord);
	
	/**
	 * 修改密码
	 */
	public void updatePWD(@Param("userName") String userName, @Param("passWord") String passWord);
	
	
	/*
	 * 查询用户
	 */
	public TUserInfo queryUserByName(@Param("userName") String userName);
	
	/*
	 * 根据用户名修改信息
	 */
	
	
	/*
	 * 添加用户(注册)
	 */
	public Long  addUser( TUserInfo userInfo);
	
	/*
	 * 删除用户(逻辑删除)
	 */
	
	/*
	 * 分页查询所有用户
	 */
	public List<TUserInfo> selectUserAll();


	/**
	 * 查询用户信息
	 * @param userName
	 * @return
	 */
	UserInfoDto queryUserInfo(@Param("userName") String userName);
}
