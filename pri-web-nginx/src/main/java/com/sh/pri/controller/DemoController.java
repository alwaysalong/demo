package com.sh.pri.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/demo")
	@ResponseBody
	public Map<String, Object> demo(@RequestBody String username){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sex", "男");
		map.put("mobile", "111111111");
		map.put("email", "111111@");
		map.put("address", "上海");
		return  map;
	} 
}
