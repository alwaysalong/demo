<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="js/jquery-1.8.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<style>
label {
	display: inline-block;
	width: 100px;
	text-align: right;
}

div {
	padding: 5px 0;
}
</style>

</head>
<body>
	<%-- <h1 align="center">欢迎你!</h1>
	<center>
		<form action="${pageContext.request.contextPath}/register/addUser"
			id="regForm" name="regForm" method="post">
			<div>
				<label>登录名：</label> <input type="text" name="userName" id="userName" placeholder="请输入账号"/>
			</div>
			<div>
				<label>密码：</label> <input name="passWord" type="password" id="passWord" placeholder="请输入密码"/>
			</div>
			<div>
				<label>密码：</label> <input name="passWord" type="password" id="passWord1" placeholder="请再输入一次密码"/>
			</div>
			<div>
				<label>性别:</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="sex" name="sex" checked="checked" value="1"/>男&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" id="sex" name="sex" value="2"/>女&nbsp;&nbsp;&nbsp;&nbsp;
				 
			</div>
			<div id="error_msg">
				 <font color="red">${sessionScope.error}</font> <br>
			</div>
			<div align="center">
				<!-- <input type="submit" value="提交注册"> -->
				<input type="button" value="提交注册" class="btn Borange mb20" onclick="doSubmit()"/>
			</div>
					<br> <font color="blue">${sessionScope.success}</font> <br>
		</form>

	</center> --%>
	<font color="red" size="10px">暂未完善...</font>
	
<script type="text/javascript">
	/* function doSubmit(){
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
	} */
</script>
</body>
</html>