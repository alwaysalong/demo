<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="Public/js/jquery-easyui-1.3.1/jquery-1.8.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script type="text/javascript">
	
</script>

</head>
<body>
	<h2 align="center">欢迎你!</h2>
	<center>
		<form action="${pageContext.request.contextPath}/register/addUser" method="post">
			<tr>
				<td>登录名：</td>
				<td><input name="userName"></td>
			</tr>
			<br>
			<br>
			<tr>
				<td>密码：</td>
				<td><input name="passWord" type="password"></td>
			</tr>
			<br>
			<br>
			<tr align="center">
				<td colspan="2"><input type="submit" value="提交注册"></td>
			</tr>

		</form>


	</center>
</body>
</html>