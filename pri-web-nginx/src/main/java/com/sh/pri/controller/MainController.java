package com.sh.pri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping("/main")
	public String enter(){
		return "redirect:/login.jsp";
	}
}
