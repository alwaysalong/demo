<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<center>
		<h1 style="color: red">登录</h1>
		<font color="red">${sessionScope.error }</font>
		
		<form id="indexform" name="indexForm"
			action="${pageContext.request.contextPath}/login/loginIn"
			method="post">
			<table border="0">
				<tr>
					<td>账号：</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
			<br> <input type="submit" value="登录" style="color: #BC8F8F">
		</form>
		<form action="register.jsp">
			<input type="submit" value="注册" style="color: #BC8F8F">
		</form>
		</center>

</body>
</html>