package com.controller;

import com.pojo.Student;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by admin on 2018/2/11.
 */
@RestController
@RequestMapping("/con")
public class SpringColudTestController {

    private static Log log = LogFactory.getLog(SpringColudTestController.class);
    private static final  String name = "%s";
    private static final  String age = "%d";

    @RequestMapping("/getInfo")
    public Student getStudentInfo(@RequestParam(value = "id", defaultValue = "10") String id){
        log.info("id:" + id);
        log.info("name:" + name);
        log.info("age:" + age);
        return new Student(Long.valueOf(id), String.format(name, "job"), Integer.parseInt(String.format(age, 18)));
    }
}
