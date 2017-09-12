package com.sh.pri.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sh.pri.commons.ConstantsClass;
import com.sh.pri.service.IUserInfoService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private static Log log = LogFactory.getLog(RegisterController.class);
	@Autowired
	private IUserInfoService userInfoService;

	@RequestMapping("/addUser")
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = userInfoService.addUser(userName, passWord);
		} catch (Exception e) {
			log.info("RegisterController.addUser   exception" + e);
			log.info(e.getMessage(),e);
//			return "redirect:/error.jsp";
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		if (result.get("code").equals(ConstantsClass.PARAMETER_ERROR)) {
			request.getSession().setAttribute("error", "用户名或密码不能空!");
//			return "redirect:/register.jsp";
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}else if (result.get("code").equals(ConstantsClass.REQUEST_FAIL)) {
			request.getSession().setAttribute("error", "用户名已存在!");
//			return "redirect:/register.jsp";
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("success", "注册成功,请点击这里进行登录...");
		request.getRequestDispatcher("/WEB-INF/jsp/reminder.jsp").forward(request, response);
		return;
		
	}
}
