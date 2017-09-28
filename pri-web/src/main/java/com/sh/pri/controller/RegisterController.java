package com.sh.pri.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sh.pri.commons.ConstantsClass;
import com.sh.pri.pojo.TUserInfo;
import com.sh.pri.service.IUserInfoService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private static Log log = LogFactory.getLog(RegisterController.class);
	@Autowired
	private IUserInfoService userInfoService;

	@RequestMapping("/addUser")
	public void addUser(TUserInfo userInfo, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			if (userInfo == null || userInfo.equals("")) {
				log.error("RegisterController . addUser   的参数不能为空!!");
				request.getSession().setAttribute("error", "请填写完整信息!");
				request.getRequestDispatcher("/register.jsp").forward(request,
						response);
				request.getSession().invalidate();
				return;
			}
			if (StringUtils.isBlank(userInfo.getUserName())
					|| StringUtils.isBlank(userInfo.getPassWord())
					|| StringUtils.isBlank(userInfo.getEmail())
					|| StringUtils.isBlank(userInfo.getMobile())) {
				log.error("RegisterController . addUser   的参数不完整!!");
				request.getSession().setAttribute("error", "请填写完整信息!");
				request.getRequestDispatcher("/register.jsp").forward(request,
						response);
				request.getSession().invalidate();
				return;
			}
			String userName = userInfo.getUserName();
			Map<String, Object> queryUserByName = userInfoService
					.queryUserByName(userName);
			if (queryUserByName.get("msg").equals("该用户不存在!")) {
				// if (queryUserByName.get("userInfo") == null ||
				// queryUserByName.get("userInfo").equals("")) {
				log.info("用户名不存在,可以注册!");
				Map<String, Object> addUser = userInfoService.addUser(userInfo);
				if (addUser.get("code").equals(ConstantsClass.REQUEST_SUCCESS)) {
					log.info("---------添加注册成功!----------");
					Map<String, Object> user = userInfoService
							.queryUserByName(userName);
					TUserInfo userTUserInfo = (TUserInfo) user.get("userInfo");
					Long id = userTUserInfo.getId();
					request.getSession().setAttribute("userId", id);
					request.getRequestDispatcher("/WEB-INF/jsp/reminder.jsp")
							.forward(request, response);
					request.getSession().invalidate();
					return;
				} else {
					log.info("---------添加用户失败!----------");
					request.getSession().setAttribute("error", "注册用户失败,请重新注册!");
					request.getRequestDispatcher("/register.jsp").forward(
							request, response);
					request.getSession().invalidate();
					return;
				}
			} else {
				log.info("---------用户名已存在!----------");
				request.getSession().setAttribute("userInfo", userInfo);
				request.getSession().setAttribute("error", "用户名已存在,请重新注册!");
				request.getRequestDispatcher("/register.jsp").forward(request,
						response);
				request.getSession().invalidate();
				return;
			}
		} catch (Exception e) {
			log.info("RegisterController.addUser  exception : " + e);
			log.info(e.getMessage(), e);
			response.sendRedirect("/error.jsp");
		}
	}
}
