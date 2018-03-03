package com.sh.pri.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.pri.commons.CookieUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	private static Log log = LogFactory.getLog(LoginInterceptor.class);
	//前置拦截,一般做登录拦截...
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String userName =String.valueOf(session.getAttribute("userName"));
		String id = String.valueOf(session.getAttribute("userId"));
		session.removeAttribute("error");
		if (null == userName || "".equals(userName) || null == id || "".equals(id) || "null" == id || "null" == userName) {
			log.info("-------------该用户没有登录!-------------");
//			response.sendRedirect("/WEB-INF/jsp/login.jsp");
//			response.sendRedirect(request.getContextPath() + "/login.jsp");
			//转发到登录页面
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
					.forward(request, response);
			return false;
		}
		session.setMaxInactiveInterval(5*60);
		return true;
//		String userName = CookieUtils.getCookieValueByName(request, "name");
//		if (null == userName || "".equals(userName)){
//			log.info("-------------该用户没有登录!-------------");
//			//转发到登陆页面
//			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
//			return false;
//		}

	}
	//后处理回调,在视图渲染之前...
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	//后处理回调,在视图渲染之后...整个执行完成之后
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
