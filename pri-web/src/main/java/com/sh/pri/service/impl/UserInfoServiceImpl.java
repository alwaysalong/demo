package com.sh.pri.service.impl;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sh.pri.commons.ConstantsClass;
import com.sh.pri.dao.IUserInfoDao;
import com.sh.pri.pojo.TUserInfo;
import com.sh.pri.service.IUserInfoService;

@Service
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {

	private static Log log = LogFactory.getLog(UserInfoServiceImpl.class);
	@Autowired
	private IUserInfoDao userInfoDao;
	private Map<String, Object> map = new HashMap<String, Object>();

	public Map<String, Object> queryUserInfo(String userName, String passWord) {
		if (null == userName || "".equals(userName) || null == passWord
				|| "".equals(passWord)) {
			map.put("msg", "参数不能为空!");
			return map;
		}
		String pwd = DigestUtils.md5Hex(passWord);
		TUserInfo result = userInfoDao.queryUserInfo1(userName, pwd);
		result.setPassWord(null);
		map.put("msg", "查询成功!");
		map.put("id", result.getId());
		map.put("用户名", result.getUserName());
		return map;
	}

	public Map<String, Object> addUser(TUserInfo userInfo) {
		try {
			userInfo.setPassWord(DigestUtils.md5Hex(userInfo.getPassWord()));
			userInfo.setRegTime(new Date());
			userInfo.setRegIp(String.valueOf(InetAddress.getLocalHost().getHostAddress()));
			log.info(">>>>>>>>>>>>>>>>>>>"+userInfo);
			Long row = userInfoDao.addUser(userInfo);
			log.info("影响行数:          "+row );
			if (row > 0) {
				map.put("code",ConstantsClass.REQUEST_SUCCESS);
				map.put("msg", "添加用户成功!");
				log.info("添加用户成功!");
				return map;
			}
			map.put("code",ConstantsClass.REQUEST_FAIL);
			map.put("msg", "添加用户失败!");
			log.info("添加用户失败!");
			return map;
		} catch (Exception e) {
			log.info("UserInfoServiceImpl.addUser   exception :" + e);
			log.info(e.getMessage(),e);
			//默认情况下只有service层手动抛出RuntimeException异常,数据才会进行回滚
			throw new RuntimeException();
		}
	}

	public Map<String, Object> deleUser(String userName, String passWord) {
		if (null == userName || "".equals(userName) || null == passWord
				|| "".equals(passWord)) {
			map.put("msg", "参数不能为空!");
			return map;
		}
		try {
			String pwd = DigestUtils.md5Hex(passWord);
			TUserInfo user = userInfoDao.queryUserInfo1(userName, pwd);
			if (user == null) {
				map.put("msg", "用户名或密码不正确!");
				return map;
			}
			userInfoDao.deleUser(userName, pwd);
			map.put("msg", "删除用户成功!");
			map.put("已删除", userName);
			return map;
		}catch (Exception e){
			log.info("UserInfoServiceImpl.deleUser  exception   :" + e);
			log.info(e.getMessage(),e);
			//默认情况下只有service层手动抛出RuntimeException异常,数据才会进行回滚
			throw new RuntimeException();
		}

	}

	public Map<String, Object> updatePWD(String userName, String passWord,
			String oldPWD) {
		if (null == userName || "".equals(userName) || null == passWord
				|| "".equals(passWord) || null == oldPWD || "".equals(oldPWD)) {
			map.put("code", ConstantsClass.PARAMETER_ERROR);
			map.put("msg", "参数不能为空!");
			return map;
		}
		try {
			String pwd = DigestUtils.md5Hex(oldPWD);
			TUserInfo user = userInfoDao.queryUserInfo1(userName, pwd);
			if (user == null) {
				map.put("code", ConstantsClass.PARAMETER_ERROR);
				map.put("msg", "原密码不正确!");
				return map;
			}
			String newPWD = DigestUtils.md5Hex(passWord);
			userInfoDao.updatePWD(userName, newPWD);
			map.put("code", ConstantsClass.REQUEST_SUCCESS);
			map.put("msg", "密码修改成功!");
			return map;
		}catch (Exception e){
			log.info("UserInfoServiceImpl.updatePWD   exception :" + e);
			log.info(e.getMessage(), e);
			throw new RuntimeException();
		}

	}


	public Map<String, Object> queryUserByName(String userName) {
		try {
			TUserInfo userInfo = userInfoDao.queryUserByName(userName);
			if (userInfo == null) {
				log.info("该用户不存在!");
				map.put("code", ConstantsClass.REQUEST_SUCCESS);
				map.put("msg", "该用户不存在!");
				return map;
			}
			log.info("该用户已存在!");
			map.put("code", ConstantsClass.REQUEST_SUCCESS);
			map.put("msg", "该用户已存在!");
			map.put("userInfo", userInfo);
			return map;
		} catch (Exception e) {
			log.info("UserInfoServiceImpl.queryUserByName   exception :" + e);
			log.info(e.getMessage(),e);
			throw new RuntimeException();
		}
	}
	
	/*
	 * 查所有用户
	 * @see com.sh.pri.service.IUserInfoService#selectUserAll()
	 */
	public List<TUserInfo> selectUserAll(){
		List<TUserInfo> userList = null;
		try {
			userList = userInfoDao.selectUserAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

}
