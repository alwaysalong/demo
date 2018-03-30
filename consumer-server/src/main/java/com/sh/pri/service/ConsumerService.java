package com.sh.pri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * rest方式调用
 * Created by Administrator on 2018/3/28.
 */
@Service
public class ConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    public List<String> getProvinde(){
        List<String> object = restTemplate.getForObject("http://province-server/province/provinceServer", List.class);
        return object;
    }
}
