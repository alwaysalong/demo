package com.sh.pri.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sh.pri.pojo.TUserInfo;
import com.sh.pri.service.IUserInfoService;

@Controller
@RequestMapping("/pageHelper")
public class pageHelperDemoController {

	@Autowired
	private IUserInfoService userInfoService;
	
	@RequestMapping("/userList")
	public String selectUserAll(@RequestParam(required=true,defaultValue="1") Integer page,@RequestParam(required=true,defaultValue="3") Integer pageSize,HttpServletRequest request,Model model){
		//开始分页  page是页码     pageSize是每页条数  默认第一页 每页3条
		PageHelper.startPage(page, pageSize);
		List<TUserInfo> userList = userInfoService.selectUserAll();
		//具体参数属性翻看PageInfo类源码
		PageInfo<TUserInfo> p = new PageInfo<TUserInfo>(userList);
		model.addAttribute("page", p);
		model.addAttribute("userList", userList);
		return "pageUser";
		
	}
}
