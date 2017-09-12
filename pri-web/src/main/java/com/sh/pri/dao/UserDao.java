package com.sh.pri.dao;

import org.apache.ibatis.annotations.Param;

import com.sh.pri.pojo.User;

public interface UserDao {

	/**
	 * 根据id查用户
	 * @param id
	 * @return
	 */
	public User queryById(@Param("id")Long id);
	
	/**
	 * 添加用户  返回影响行数
	 * @param id
	 * @param name
	 * @param age
	 * @return
	 */
	public int addUser(@Param("id")Long id,@Param("name")String name,@Param("age")Long age);
	
	

	/**
	 * 删除用户
	 * @param id
	 */
	public int deleUser(Long id);
}
