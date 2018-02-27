package com.sh.pri.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView query(@RequestParam("key") String key, @RequestParam(value = "page", defaultValue = "1") Integer page){
//        ModelAndView mv = new ModelAndView("query");
//
//        try {
//            key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
//
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//		return mv;
//    }
}
