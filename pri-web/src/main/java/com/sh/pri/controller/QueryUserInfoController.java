package com.sh.pri.controller;

import com.sh.pri.dto.UserInfoDto;
import com.sh.pri.service.IQueryUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * Created by admin on 2018/3/23.
 */
@Controller
@RequestMapping("/queryInfo")
public class QueryUserInfoController {

    private static Logger logger = Logger.getLogger(QueryUserInfoController.class);

    @Autowired
    private IQueryUserInfoService queryUserInfoService;

    /**
     * 查询用户信息页面跳转
     * @return
     */
    @RequestMapping("/toQueryJsp")
    public String toQueryUserInfoJsp(){
        return "queryUserInfo";
    }
    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public List<UserInfoDto> queryUserInfo(HttpServletRequest request, String userName){
        logger.info("QueryUserInfoController.queryUserInfo ... start...userName :" + userName);
        long start = System.currentTimeMillis();
//        String username = request.getParameter("username");
        List<UserInfoDto> userInfoDto = null;
        if (StringUtils.isBlank(userName)){
            return userInfoDto;
        }
        try {
            userInfoDto = queryUserInfoService.queryUserInfo(userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询用户信息异常.." + e.getMessage(), e);
        }
        logger.info("QueryUserInfoController.queryUserInfo ... end  cost :" + (System.currentTimeMillis() - start) + "ms");
        return userInfoDto;
    }
}
