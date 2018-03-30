package com.sh.pri.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */
@RestController
@RequestMapping("/province")
public class ProvinceServerController {

    @RequestMapping("/provinceServer")
    public List<String> provinceServer(){

        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        return list;
//        return "这是生产者服务......";
    }
}
