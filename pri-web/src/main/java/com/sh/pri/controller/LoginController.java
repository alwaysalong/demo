package com.sh.pri.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.qos.logback.core.net.SyslogOutputStream;
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
//		quest.setAttribute("error", "错误信息！");
		HttpSession session = request.getSession();
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		session.removeAttribute("error");
		if (null == userName || "".equals(userName) || null == passWord
				|| "".equals(passWord)) {
			log.error("LoginController . loglinIn 的参数不能为空! ");
			session.setAttribute("error", "请输入正确的账号密码!");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,
					response);
			session.invalidate();
			return null;
		}
		try {
			result = loginService.loginIn(userName, passWord);
			if (result.get("code").equals(ConstantsClass.REQUEST_FAIL)) {
				session.setAttribute("error", "用户名或密码错误!");
//				response.sendRedirect("/login.jsp");
//				session.setMaxInactiveInterval(1);
				return "login";
			} else {
				session.setAttribute("userId", result.get("id"));
				session.setAttribute("userName", userName);
//				session.setMaxInactiveInterval(5*60);
				Cookie cookie = new Cookie("name", userName);
				cookie.setPath("/");
				//过期时间60s
				cookie.setMaxAge(5*60);
//				cookie.setDomain("");//cookie的作用域
				response.addCookie(cookie);//添加cookie
//				request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp")
//						.forward(request, response);
				return "main";
			}
		} catch (Exception e) {
			log.info("LoginController.loginIn  exception : " + e);
			log.info(e.getMessage(), e);
//			response.sendRedirect("/error.jsp");
//			return null;
			return "error";
		}
//		return null;
	}

	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";

	}

	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}
}
