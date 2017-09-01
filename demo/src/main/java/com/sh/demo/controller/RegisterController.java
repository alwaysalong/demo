package com.sh.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sh.demo.service.IUserInfoService;
import com.sh.demo.service.impl.UserInfoServiceImpl;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private IUserInfoService userInfoService;

	@RequestMapping("/addUser")
	public void addUser(HttpServletRequest request){
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		Map<String, Object> result = new HashMap<String, Object>();
		result = userInfoService.addUser(userName, passWord);
	}
}
