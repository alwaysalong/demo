package com.sh.pri.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by admin on 2018/2/12.
 */
@Controller
@RequestMapping("/test")
public class jspGetTrTest {
    private Logger log = Logger.getLogger(jspGetTrTest.class);

    @RequestMapping("/getTr")
    public String toJsp(){
        return "getTableElement";
    }

}
