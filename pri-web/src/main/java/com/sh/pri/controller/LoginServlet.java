package com.sh.pri.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.pri.pojo.UserInfo;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LoginJDBC loginJDBC = new LoginJDBC();
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		long userId = Long.parseLong(id);
		try {
			UserInfo userInfo = loginJDBC.queryUserInfo(userId);
//			out.print(userInfo);
			request.getSession().setAttribute("userInfo", userInfo);
//			request.setAttribute("userInfo", userInfo);
			request.getRequestDispatcher("/servlet.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
		request.getSession().removeAttribute("userInfo");
	}

}
