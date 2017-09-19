package com.sh.pri.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sh.pri.commons.ConstantsClass;
import com.sh.pri.service.ILoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	private ILoginService loginService;

	@RequestMapping(value = "/loginIn", method = RequestMethod.POST)
	public String loglinIn(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		try {
			result = loginService.loginIn(userName, passWord);
		} catch (Exception e) {
			log.info("LoginController.loginIn  exception : " + e);
			log.info(e.getMessage(), e);
			return "redirect:/error.jsp";
		}
		if (result.get("code").equals(ConstantsClass.REQUEST_FAIL)) {
			request.getSession().setAttribute("error", "用户名或密码错误!");
			return "redirect:/login.jsp";
		} else {
			request.getSession().setAttribute("userId", result.get("id"));
			request.getSession().setAttribute("userName", userName);
			request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(
					request, response);
			return null;
		}

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login.jsp";
	}
}
