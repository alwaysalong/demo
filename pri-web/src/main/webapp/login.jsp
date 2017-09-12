<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="js/jquery-1.8.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<style>
        label{
            display: inline-block;
            width: 100px;
            text-align: right;
        }
        div{
            padding: 5px 0;
        }
</style>
<script type="text/javascript">
	// 刷新图片  
	function changeImg() {
		var imgSrc = $("#imgObj");
		var src = imgSrc.attr("src");
		imgSrc.attr("src", changeUrl(src));
	}
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳  
	function changeUrl(url) {
		var timestamp = (new Date()).valueOf();
		var index = url.indexOf("?", url);
		if (index > 0) {
			url = url.substring(0, url.indexOf(url, "?"));
		}
		if ((url.indexOf("&") >= 0)) {
			url = url + "×tamp=" + timestamp;
		} else {
			url = url + "?timestamp=" + timestamp;
		}
		return url;
	}
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
			<div>
					<label for="username">账号：</label>
					<input type="text" name="username" id="username">
			</div>
			<div>
					<label for="password">密码：</label>
					<input type="password" name="password" id="password">
			</div>
					<div>
					<label for="code">验证码：</label>
					<input type="text" id="code" name="code" style="width: 250px;" /> 
					<br/>
					<img id="imgObj" alt="验证码"
							src="/article/validateCode" onclick="changeImg()" /> <a href="#"
							onclick="changeImg()">换一张</a>
					</div>
			</table>
			<br> <input type="submit" value="登录" style="color: #BC8F8F">
		</form>
		<form action="register.jsp">
			<input type="submit" value="注册" style="color: #BC8F8F">
		</form>
	 </center> 

</body>
</html>