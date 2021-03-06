package com.sh.pri.controller;

import com.sh.pri.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("/getProvince")
    public List<String> getProvince(){
        System.out.println("消费者开始调用生产者.............");
        return consumerService.getProvinde();
    }
}

