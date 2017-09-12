package com.sh.pri.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sh.pri.dao.UserDao;
import com.sh.pri.pojo.User;
import com.sh.pri.service.IUserTestService;

@Service
@Transactional
public class UserTestServiceImpl implements IUserTestService {

	@Autowired
	private UserDao userDao;
	
	
	public User queryUser(Long id) {
		User result = userDao.queryById(id);
		return result;
	}

	public int addUser(Long id, String name, Long age) {
		int row = userDao.addUser(id, name, age);
		return row;
	}

	public int delUser(Long id) {
		int row = userDao.deleUser(id);
		return row;
	}
	

}
