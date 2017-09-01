package com.sh.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sh.demo.commons.ConstantsClass;
import com.sh.demo.service.IUserInfoService;
import com.sh.demo.service.impl.UserInfoServiceImpl;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private static Log log = LogFactory.getLog(RegisterController.class);
	@Autowired
	private IUserInfoService userInfoService;

	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request){
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = userInfoService.addUser(userName, passWord);
		} catch (Exception e) {
			log.info("RegisterController.addUser   exception" + e);
			log.info(e.getMessage(),e);
			return "redirect:/error.jsp";
		}
		if (result.get("code").equals(ConstantsClass.PARAMETER_ERROR)) {
			request.getSession().setAttribute("error", "用户名或密码不能空!");
			return "redirect:/register.jsp";
		}else if (result.get("code").equals(ConstantsClass.REQUEST_FAIL)) {
			request.getSession().setAttribute("error", "用户名已存在!");
			return "redirect:/register.jsp";
		}else {
			request.getSession().setAttribute("success", "注册成功,请点击这里进行登录!");
		}
		return null;
	}
}
