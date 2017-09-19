package com.sh.pri.service;

import java.util.Map;

import com.sh.pri.pojo.TUserInfo;

public interface IUserInfoService {

	/**
	 * 根据username查询用户信息
	 */
	public Map<String, Object> queryUserInfo(String userName,String passWord);
	
	/**
	 * 查询用户是否存在
	 */
//	publci Long
	
	
	/**
	 * 新增用户
	 */
	public Map<String, Object> addUser(TUserInfo userInfo);
	
	/**
	 * 删除用户,逻辑删除
	 * 
	 */
	public Map<String, Object> deleUser(String userName,String passWord);
	
	/**
	 * 修改密码
	 */
	public Map<String, Object> updatePWD(String userName, String passWord,String oldPWD);

	/*
	 * 根据用户名查询用户
	 */
	public Map<String, Object> queryUserByName(String userName);
	
}
