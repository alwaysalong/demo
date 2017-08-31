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
	@ResponseBody
	public Map<String, Object> addUser(HttpServletRequest request,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		Map<String, Object> result = userInfoService.addUser(userName, passWord);
		return result;
	}
}
