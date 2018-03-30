package com.sh.pri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Administrator on 2018/3/27.
 */
@SpringBootApplication
@EnableEurekaClient
public class ProvinceServerApp {

    public static void main(String[] args) {
        SpringApplication.run(ProvinceServerApp.class, args);
    }

}
