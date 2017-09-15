package com.sh.pri.pojo;

import java.util.Date;


/**CREATE TABLE `t_user_info` (
  `id` bigint(10) unsigned NOT NULL COMMENT '用户ID',
  `username` char(16) NOT NULL COMMENT '用户名',
  `password` char(32) NOT NULL COMMENT '密码',
  `email` char(32) NOT NULL COMMENT '用户邮箱',
  `mobile` char(15) NOT NULL COMMENT '用户手机',
  `reg_time` date NOT NULL DEFAULT '0000-00-00' COMMENT '注册时间',
  `reg_ip` varchar(30) NOT NULL DEFAULT '0' COMMENT '注册IP',
  `last_login_time` date NOT NULL DEFAULT '0000-00-00' COMMENT '最后登录时间',
  `last_login_ip` varchar(30) NOT NULL DEFAULT '0' COMMENT '最后登录IP',
  `update_time` date NOT NULL DEFAULT '0000-00-00' COMMENT '更新时间',
  `sex` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '性别',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '用户状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';






 * 
 */
public class TUserInfo {

	private Long id;
	private String userName;
	private String passWord;
	private String email;
	private String mobile;
	private Date regTime;
	private String regIp;
	private Date lastLoginTime;
	private String lastLoginIp;
	private String updateTime;
	private int sex;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "TUserInfo [id=" + id + ", userName=" + userName + ", passWord="
				+ passWord + ", email=" + email + ", mobile=" + mobile
				+ ", regTime=" + regTime + ", regIp=" + regIp
				+ ", lastLoginTime=" + lastLoginTime + ", lastLoginIp="
				+ lastLoginIp + ", updateTime=" + updateTime + ", sex=" + sex
				+ ", status=" + status + "]";
	}
	
	
	
}
