package com.sh.pri.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sh.pri.commons.ConstantsClass;
import com.sh.pri.dao.UserInfoDao;
import com.sh.pri.pojo.UserInfo;
import com.sh.pri.service.IUserInfoService;

@Service
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {

	private static Log log = LogFactory.getLog(UserInfoServiceImpl.class);
	@Autowired
	private UserInfoDao userInfoDao;
	private Map<String, Object> map = new HashMap<String, Object>();

	public Map<String, Object> queryUserInfo(String userName, String passWord) {
		if (null == userName || "".equals(userName) || null == passWord
				|| "".equals(passWord)) {
			map.put("msg", "参数不能为空!");
			return map;
		}
		String pwd = DigestUtils.md5Hex(passWord);
		UserInfo result = userInfoDao.queryUserInfo(userName, pwd);
		result.setPassWord(null);
		map.put("msg", "查询成功!");
		map.put("id", result.getId());
		map.put("用户名", result.getUserName());
		return map;
	}

	public Map<String, Object> addUser(String userName, String passWord) {
		if (null == userName || "".equals(userName) || null == passWord
				|| "".equals(passWord)) {
			log.error("注册用户名或密码不能为空!");
			map.put("code", ConstantsClass.PARAMETER_ERROR);
			map.put("msg", "参数不能为空!");
			return map;
		}
		try {
			UserInfo userId = userInfoDao.queryByName(userName);
			if (userId != null) {
				log.error("该用户名已存在!");
				map.put("code", ConstantsClass.REQUEST_FAIL);
				map.put("msg", "用户名已存在,请重新输入!!!");
				return map;
			}
			String pwd = DigestUtils.md5Hex(passWord);
			Long row = userInfoDao.addUser(userName, pwd);
			map.put("code", ConstantsClass.REQUEST_SUCCESS);
			map.put("msg", "注册成功!");
			map.put("用户名", userName);
			return map;
		} catch (Exception e) {
			log.info("UserInfoServiceImpl.addUser   exception :" + e);
			log.info(e.getMessage(),e);
			throw new RuntimeException();
		}
	}

	public Map<String, Object> deleUser(String userName, String passWord) {
		if (null == userName || "".equals(userName) || null == passWord
				|| "".equals(passWord)) {
			map.put("msg", "参数不能为空!");
			return map;
		}
		String pwd = DigestUtils.md5Hex(passWord);
		UserInfo user = userInfoDao.queryUserInfo(userName, pwd);
		if (user == null) {
			map.put("msg", "用户名或密码不正确!");
			return map;
		}
		int row = userInfoDao.deleUser(userName, pwd);
		map.put("msg", "删除用户成功!");
		map.put("已删除", userName);
		return map;
	}

	public Map<String, Object> updatePWD(String userName, String passWord,
			String oldPWD) {
		if (null == userName || "".equals(userName) || null == passWord
				|| "".equals(passWord) || null == oldPWD || "".equals(oldPWD)) {
			map.put("msg", "参数不能为空!");
			return map;
		}
		String pwd = DigestUtils.md5Hex(oldPWD);
		UserInfo user = userInfoDao.queryUserInfo(userName, pwd);
		if (user == null) {
			map.put("msg", "用户名或密码不正确!");
			return map;
		}
		String newPWD = DigestUtils.md5Hex(passWord);
		int row = userInfoDao.updatePWD(userName, newPWD);
		map.put("msg", "密码修改成功!");
		return map;
	}

}
