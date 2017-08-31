package com.sh.demo.pojo;


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
