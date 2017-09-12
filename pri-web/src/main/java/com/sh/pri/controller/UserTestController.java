package com.sh.pri.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sh.pri.pojo.User;
import com.sh.pri.service.IUserTestService;

@Controller
@RequestMapping("/user")
public class UserTestController {

	private String Id;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@Autowired
	private IUserTestService userTestService;

	@RequestMapping("/queryUser")
	public String queryUser() {
		// //校验参数
		// if ("".equals(id)) {
		// throw new RuntimeException();
		// }
		// try {
		// User response = userTestService.queryUser(id);
		// PrintWriter printWriter = null;
		// printWriter.write(response.toString());
		// } catch (Exception e) {
		// e.printStackTrace();
		System.out.println("1--------------------------1");
		return "work";
		// }
	}

	// @RequestMapping(value="/queryUserById",method=RequestMethod.POST)
	// public Map<String, Object> queryUserById(HttpServletRequest request){
	// String id = request.getParameter("id");
	// Long id1 = new Long(id);
	// User user = userTestService.queryUser(id1);
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("id", user.getId());
	// result.put("name", user.getName());
	// result.put("age", user.getAge());
	// return result;
	// }
	@RequestMapping(value = "/queryUserById", method = RequestMethod.POST)
	public String queryUserById(@RequestParam("id") String id, Model model) {
		// String id = request.getParameter("id");
		Long id1 = new Long(id);
		// Long id1 = new Long(id);
		User user1 = userTestService.queryUser(id1);
		// Map<String, Object> result = new HashMap<String, Object>();
		// result.put("id", user1.getId());
		// result.put("name", user1.getName());
		// result.put("age", user1.getAge());
		model.addAttribute("user", user1);
		return "work";
	}

	@RequestMapping("ajaxDemo")
	@ResponseBody
	public Map<String, Object> ajaxDemo(HttpServletRequest request,Model model){
		String id = request.getParameter("id");
		Long id1 = new Long(id);
		User user = userTestService.queryUser(id1);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id1);
		map.put("name", user.getName());
		map.put("age", user.getAge());
		return map;
	}
}
