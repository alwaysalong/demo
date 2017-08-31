package com.sh.demo.service;

import com.sh.demo.pojo.User;

public interface IUserTestService {

	public User queryUser(Long id);
	
	public int addUser(Long id , String name ,Long age);
	
	public int delUser(Long id);
	 
}
