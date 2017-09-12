package com.sh.pri.service;

import com.sh.pri.pojo.User;

public interface IUserTestService {

	public User queryUser(Long id);
	
	public int addUser(Long id , String name ,Long age);
	
	public int delUser(Long id);
	 
}
