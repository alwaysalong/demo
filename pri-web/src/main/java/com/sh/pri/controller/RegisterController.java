package com.sh.pri.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.pri.commons.MsgCode;
import com.sh.pri.commons.Tools;
import com.sh.pri.service.ILoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sh.pri.commons.ConstantsClass;
import com.sh.pri.pojo.TUserInfo;
import com.sh.pri.service.IUserInfoService;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private static Log log = LogFactory.getLog(RegisterController.class);
	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private ILoginService loginService;

	@RequestMapping("/addUser")
	public void addUser(TUserInfo userInfo, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
            HttpSession session = request.getSession();
            if (userInfo == null || userInfo.equals("")) {
				log.error("RegisterController . addUser   的参数不能为空!!");
                session.setAttribute("error", "请填写完整信息!");
				request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request,
						response);
                session.invalidate();
				return;
			}
			if (StringUtils.isBlank(userInfo.getUserName())
					|| StringUtils.isBlank(userInfo.getPassWord())
					|| StringUtils.isBlank(userInfo.getEmail())
					|| StringUtils.isBlank(userInfo.getMobile())) {
				log.error("RegisterController . addUser   的参数不完整!!");
                session.setAttribute("error", "请填写完整信息!");
				request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request,
						response);
                session.invalidate();
				return;
			}
			String userName = userInfo.getUserName();
			Map<String, Object> queryUserByName = userInfoService
					.queryUserByName(userName);
			if (queryUserByName.get("msg").equals("该用户不存在!")) {
				// if (queryUserByName.get("userInfo") == null ||
				// queryUserByName.get("userInfo").equals("")) {
				log.info("用户名不存在,可以注册!");
				Map<String, Object> addUser = userInfoService.addUser(userInfo);
				if (addUser.get("code").equals(ConstantsClass.REQUEST_SUCCESS)) {
					log.info("---------添加注册成功!----------");
					Map<String, Object> user = userInfoService
							.queryUserByName(userName);
					TUserInfo userTUserInfo = (TUserInfo) user.get("userInfo");
					Long id = userTUserInfo.getId();
                    session.setAttribute("userId", id);
					request.getRequestDispatcher("/WEB-INF/jsp/reminder.jsp")
							.forward(request, response);
                    session.invalidate();
					return;
				} else {
					log.info("---------添加用户失败!----------");
                    session.setAttribute("error", "注册用户失败,请重新注册!");
					request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(
							request, response);
                    session.invalidate();
					return;
				}
			} else {
				log.info("---------用户名已存在!----------");
                session.setAttribute("userInfo", userInfo);
                session.setAttribute("error", "用户名已存在,请重新注册!");
				request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request,
						response);
                session.invalidate();
				return;
			}
		} catch (Exception e) {
			log.info("RegisterController.addUser  exception : " + e);
			log.info(e.getMessage(), e);
			response.sendRedirect("/error.jsp");
		}
	}

	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("/toRegister")
	public String toRegister(HttpServletRequest request){
		request.getSession().invalidate();
	    return "register";
	}

	/**
	 * 跳转到重置密码页面
	 * @return
	 */
	@RequestMapping("/resetPWD")
	public String toResetPWD(HttpServletRequest request){
        request.getSession().invalidate();
		return "resetPWD";
	}

	@RequestMapping(value = "/updatePWD", method = RequestMethod.POST)
    public String resetPWD(HttpServletRequest request){
	    log.info("RegisterController.resetPWD  .... started  ");
        String userName = request.getParameter("userName");
        String newPassWord = request.getParameter("newPassWord");
        String passWord = request.getParameter("passWord");
        HttpSession session = request.getSession();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(newPassWord) || StringUtils.isBlank(passWord)){
            session.setAttribute("error", "参数不能为空!");
            return "resetPWD";
        }
//        Map<String, Object> resultMap = loginService.loginIn(userName, passWord);
//        if (!Tools.ts(resultMap.get("code")).equals(ConstantsClass.REQUEST_SUCCESS)){
//            session.setAttribute("error", "原始密码错误!");
//            return "resetPWD";
//        }
        Map<String, Object> result = userInfoService.updatePWD(userName, newPassWord, passWord);
        if (ConstantsClass.PARAMETER_ERROR.equals(Tools.ts(result.get("code")))){
            session.setAttribute("error", "原始密码错误!");
            return "resetPWD";
        }else if (ConstantsClass.REQUEST_SUCCESS.equals(Tools.ts(result.get("code")))){
            log.info("RegisterController.resetPWD   ...修改密码成功!");
            session.invalidate();
            return "jspJump";
        }
        return "error";
    }

    /**
     * 跳转到忘记密码页面
     * @param request
     * @return
     */
    @RequestMapping("/forgetPWD")
    public String forgetPWD(HttpServletRequest request){
        request.getSession().invalidate();
        return "forgetPWD";
    }
}
