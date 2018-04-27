package com.example.demo.com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2018/4/27.
 */
@Controller
public class TestController {

    @RequestMapping("/index")
    public String index(){

        return "hello";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "jsp/welcome";
    }
}
