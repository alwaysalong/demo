package com.sh.pri.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sh.pri.pojo.TUserInfo;
import com.sh.pri.pojo.UserInfo;

public interface UserInfoDao {

	/**
	 * 查询用户信息
	 */
	public TUserInfo queryUserInfo(@Param("userName") String userName, @Param("passWord") String passWord);
	
	
	/**
	 * 新增用户
	 */
	public Long addUser(@Param("userName") String userName, @Param("passWord") String passWord, @Param("sex") String sex);
	
	
	/**
	 * 删除用户,逻辑删除
	 * 
	 */
	public int deleUser(@Param("userName") String userName, @Param("passWord") String passWord);
	
	/**
	 * 修改密码
	 */
	public int updatePWD(@Param("userName") String userName, @Param("passWord") String passWord);
	
	
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

}
