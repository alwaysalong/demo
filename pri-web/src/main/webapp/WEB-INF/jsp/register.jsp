<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../../js/jquery.validate.extend.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<style>
label {
	display: inline-block;
	width: 100px;
	text-align: right;
}

.error {
	color: red;
	font-size: 1px
}

div {
	padding: 5px 0;
}
</style>

<!-- 让背景图片覆盖整个屏幕  -->
<style type="text/css">
body {
	background-image: url(../../image/backgr.jpg);
	background-size: cover;
}
</style>


<script>
	$(function() {
		// 在键盘按下并释放及提交后验证提交表单
		$("#regForm").validate({
			rules : {
				userName : {
					required : true,
					username : true
				},
				passWord : {
					required : true,
					pwd : true
				},
				passWord1 : {
					required : true,
					equalTo : "#passWord"
				},
				email : {
					required : true,
					email : true
				},
				mobile : {
					required : true,
					tele : true
				},
				inputCode : {
					required : true,
					code : true
				}
			},
			messages : {
				userName : {
					required : "请输入用户名"
				},
				passWord : {
					required : "请输入密码"
				},
				passWord1 : {
					required : "请输入密码",
					equalTo : "两次密码输入不一致"
				},
				email : {
					required : "请输入邮箱",
					email : "请输入有效的邮箱"
				},
				mobile : {
					required : "请输入手机号"
				},
				inputCode : {
					required : "请输入验证码"
				}
			}
		});
	});
</script>
<script type="text/javascript" src="/js/checkCode.js">
	
</script>
</head>
<body>
	<h3 align="center">欢迎你!</h3>
	<center>
		<form action="${pageContext.request.contextPath}/register/addUser"
			id="regForm" name="regForm" method="post">
			<div>
				<label>登录名：</label> <input type="text" name="userName" id="userName"
					placeholder="请输入账号" value="${userInfo.userName}" />
			</div>
			<div>
				<label>密码：</label> <input name="passWord" type="password"
					id="passWord" placeholder="请输入密码" />
			</div>
			<div>
				<label>确认密码：</label> <input name="passWord1" type="password"
					id="passWord1" placeholder="请再输入一次密码" />
			</div>
			<div>
				<label>性别:</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"
					id="sex" name="sex" checked="checked" value="1" />男&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="sex" name="sex" value="2" />女&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<div>
				<label>邮箱：</label> <input type="text" name="email" id="email"
					placeholder="请输入邮箱" value="${userInfo.email}" />
			</div>
			<div>
				<label>手机号：</label> <input type="text" name="mobile" id="mobile"
					placeholder="请输入手机号" value="${userInfo.mobile}" />
			</div>
			<div>
				<input type="button" id="code" onclick="createCode()" /> <a
					href="javascript:void(0)" onclick="createCode()"> <font
					color="blue" size="2px">换一张</font></a> <br> <label>验证码:</label> <input
					type="text" id="inputCode" name="inputCode" placeholder="请输入验证码" />
			</div>
			<div id="error_msg">
				<font color="red">${sessionScope.error}</font> <br>
			</div>
			<div align="center">
				<input type="submit" value="提交注册" class="btn Borange mb20">
				<!-- <input type="button" value="提交注册" class="btn Borange mb20" onclick="doSubmit()"/> -->
			</div>
		</form>
	</center>


	<script type="text/javascript">
		/*  function doSubmit(){
			  var sex = $("input[name='sex']:checked").val(); 
			var sex = $("#sex").val();
			var username = $("#userName").val().replace(/(^\s*)|(\s*$)/g, '');//去除空格
			var password = $("#passWord").val().replace(/(^\s*)|(\s*$)/g, '');
			var password1 = $("#passWord1").val().replace(/(^\s*)|(\s*$)/g, '');
			if(username == '' || username == undefined || username == null){
				alert("登录名不能为空!");
				return false;
			}
			if(password == '' || password == undefined || password == null){
				alert("密码不能为空!");
				return false;
			}
			if(password != password1){
				alert("两次输入的密码不一致!");
				return false;
			}
			$("#regForm").submit(); 
			$("#regForm").submit(); 
		 } */
	</script>
</body>
</html>