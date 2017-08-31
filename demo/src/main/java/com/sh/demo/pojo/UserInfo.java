package com.sh.demo.pojo;

/* sql
 * CREATE TABLE `userinfo` (
 *		  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
 *		  `username` varchar(16) NOT NULL COMMENT '用户名',
 *		  `password` varchar(200) NOT NULL COMMENT '密码',
 *		  `status` tinyint(4) DEFAULT '0' COMMENT '用户状态',
 *		  PRIMARY KEY (`id`)
 *		) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';
 *
 */
public class UserInfo {

	
	private Long id;
	private String userName;
	private String passWord;
	private Long status;
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userName=" + userName + ", passWord="
				+ passWord + ", status=" + status + "]";
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
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
}
