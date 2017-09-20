package com.sh.pri.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.pri.commons.ConstantsClass;
import com.sh.pri.dao.UserInfoDao;
import com.sh.pri.pojo.TUserInfo;
import com.sh.pri.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {

	private static Log log = LogFactory.getLog(LoginServiceImpl.class);

	@Autowired
	private UserInfoDao userInfoDao;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public Map<String, Object> loginIn(String userName, String passWord) {
		
		/*if (null == userName || "".equals(userName) || null == passWord
				|| "".equals(passWord)) {
			log.error("用户名或密码不能为空!");
			map.put("code", ConstantsClass.PARAMETER_ERROR);
			map.put("msg", "参数不能为空!");
			return map;
		}*/
		try {
			String pwd = DigestUtils.md5Hex(passWord);
			TUserInfo result = userInfoDao.queryUserInfo(userName, pwd);
			if (null == result || "".equals(result)) {
				map.put("code", ConstantsClass.REQUEST_FAIL);
				map.put("msg", "用户名或密码错误!");
				return map;
			}
			result.setPassWord(null);
			map.put("code", ConstantsClass.REQUEST_SUCCESS);
			map.put("msg", "查询成功!");
			map.put("id", result.getId());
			map.put("userName", result.getUserName());
			log.info("UserInfo :" + map);
			return map;
		} catch (Exception e) {
			log.info("LoginServiceImpl.loginIn   exception :" + e);
			log.info(e.getMessage(),e);
			throw new RuntimeException();
		}
	}

}
