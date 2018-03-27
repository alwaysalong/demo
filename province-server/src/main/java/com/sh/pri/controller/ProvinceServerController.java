package com.sh.pri.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/3/27.
 */
@RestController
public class ProvinceServerController {

    @RequestMapping("/provinceServer")
    public String provinceServer(){

        return "这是生产者服务......";
    }
}
