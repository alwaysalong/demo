package com.sh.pri.controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sh.pri.pojo.UserInfo;

public class LoginJDBC {

	private static final String DRIVENAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/demo_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	private Connection conn = null;
	private Statement st = null;
	private PreparedStatement ppst = null;
	private ResultSet rs = null;

	public UserInfo queryUserInfo(Long id) throws SQLException {
		try {
			Class.forName(DRIVENAME);// 加载数据库驱动
			// 获取数据库连接
			conn = (Connection) DriverManager
					.getConnection(URL, USER, PASSWORD);
			String sql = "select * from userinfo where id = ?";
			ppst = (PreparedStatement) conn.prepareStatement(sql);
			ppst.setLong(1, id);// 设置条件id
			rs = ppst.executeQuery();
			while (rs.next()) {
				long userId = rs.getLong("id");
				String userName = rs.getString("userName");
				String sex = rs.getString("sex");
				long status = rs.getLong("status");
				System.out.println("id:" + userId + ",userName:" + userName
						+ ",sex:" + sex + ",status:" + status);
				UserInfo userInfo = new UserInfo();
				long id1 = rs.getLong("id");
				userInfo.setId(id1);
				userInfo.setUserName(rs.getString("userName"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setStatus(rs.getLong("status"));
				return userInfo;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ppst.close();
			conn.close();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			Class.forName(DRIVENAME);// 加载数据库驱动
			// 获取数据库连接
			Connection conn = (Connection) DriverManager.getConnection(URL,
					USER, PASSWORD);
			String sql = "select * from userinfo where id = ?";
			PreparedStatement ppst = (PreparedStatement) conn
					.prepareStatement(sql);
			ppst.setLong(1, 2);// 设置条件id
			ResultSet rs = ppst.executeQuery();
			while (rs.next()) {
				long userId = rs.getLong("id");
				String userName = rs.getString("userName");
				String sex = rs.getString("sex");
				long status = rs.getLong("status");
				System.out.println("id:" + userId + ",userName:" + userName
						+ ",sex:" + sex + ",status:" + status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
