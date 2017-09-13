<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
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
<style type="text/css">
#code {
	font-family: Arial;
	font-style: italic;
	font-weight: bold;
	border: 0;
	letter-spacing: 2px;
	color: blue;
}
/* 登录样式 */
#button1 {
    width: 100px;
    height: 30px;
    font-size: 18px;
    font-family: 微软雅黑;
    letter-spacing: 8px;
    padding-left: 12px;
    border-radius: 5px;
    background: -webkit-linear-gradient(top, #66B5E6, #2e88c0);
    background: -moz-linear-gradient(top, #66B5E6, #2e88c0);
    background: linear-gradient(top, #66B5E6, #2e88c0);
    background: -ms-linear-gradient(top, #66B5E6, #2e88c0);
    border: 1px solid #2576A8;
    box-shadow: 0 1px 2px #B8DCF1 inset, 0 -1px 0 #316F96 inset;
    color: #fff;
    text-shadow: 1px 1px 0.5px #22629B;
}
#button1:hover {
    background: -webkit-linear-gradient(top, #8DC9EF, #4E9FD1);
    background: -moz-linear-gradient(top, #8DC9EF, #4E9FD1);
    background: linear-gradient(top, #8DC9EF, #4E9FD1);
    background: -ms-linear-gradient(top, #8DC9EF, #4E9FD1);
}
</style>
<script type="text/javascript" src="/js/checkCode.js">
</script>
<script type="text/javascript">
/*  function doSubmit() {
	var code1 = $("#input").val();
	var username = $("#username").val().replace(/(^\s*)|(\s*$)/g, '');//去空格
	var password = $("#password").val().replace(/(^\s*)|(\s*$)/g, '');
	if(username == '' || username == undefined || username == null){
		alert("请输入有效的账号!");
		return false;
	}
	if(password == '' || password == undefined || password == null){
		alert("密码不能为空!");
		return false;
	}
	if(code != code1){
		alert("验证码错误!");
		return false;
	} 
	$("#indexform").submit();
}  */
	/* // 刷新图片  
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
	} */
	
	//先加载完全页面
	$(document).ready(function() {
		$('#hint').hide();
		$('#hint1').hide();
		$('#hint2').hide();
//		document.getElementByIdx("button1").disabled=true;
	});
	
	function aa() {
		var username = $("#username").val().replace(/(^\s*)|(\s*$)/g, '');//去空格
		var password = $("#password").val().replace(/(^\s*)|(\s*$)/g, '');
		var code1 = $("#input").val();
		if(username == '' || username == undefined || username == null){
			$("#hint").show();
			return false;
		}
		$("#hint").hide();
		if(username != "" && password != "" && code == code1){
			$('#button1').removeAttr("disabled");
		}
	}
	function aa1() {
		var username = $("#username").val().replace(/(^\s*)|(\s*$)/g, '');//去空格
		var password = $("#password").val().replace(/(^\s*)|(\s*$)/g, '');
		var code1 = $("#input").val();
		if(password == '' || password == undefined || password == null){
			$("#hint1").show();
			return false;
		}
		$("#hint1").hide();
		if(username != "" && password != "" && code == code1){
			$('#button1').removeAttr("disabled");
		}
	}
	function aa2() {
		var username = $("#username").val().replace(/(^\s*)|(\s*$)/g, '');//去空格
		var password = $("#password").val().replace(/(^\s*)|(\s*$)/g, '');
		var code1 = $("#input").val();
		if(code != code1){
			$("#hint2").show();
			return false;
		}
		$("#hint2").hide();
		if(username != "" && password != "" && code == code1){
			$('#button1').removeAttr("disabled");
		}
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
					<label for="username">账号：</label> <input type="text" placeholder="请输入账号"
						name="username" id="username" onblur="aa()"><div id="hint" style="display:inline;"><font color="red" size="1px">*账号不能为空</font></div>
				</div>
				<div>
					<label for="password">密码：</label> <input type="password" placeholder="请输入密码"
						name="password" id="password" onblur="aa1()"><div id="hint1" style="display:inline;"><font color="red" size="1px">*密码不能为空</font></div>
				</div>
				<div>
					<input type="button" id="code" onclick="createCode()" /> <a href="javascript:void(0)" onclick="createCode()"><font color="blue" size="2px">换一张</font></a>
					<br>
					<label>验证码:</label>
					<input type="text" id="input" placeholder="请输入验证码" onblur="aa2()"/><div id="hint2" style="display:inline;"><font color="red" size="1px">*验证码不正确</font></div> 
					<!-- <input type="button" value="验证" onclick="validate()" /> -->
				</div>
			</table>
			<!-- <div align="center">
				<input type="button" id="button1" value="登录" class="btn Borange mb20" onclick="doSubmit()"/>
			</div> -->
			<br> <input type="submit" value="登录" id="button1" class="btn Borange mb20" disabled="true"/>
		</form>
		<a href="resetPWD.jsp"><font color="blue" size="2px">忘记密码</font></a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="register.jsp"><font color="blue" size="2px">注册账号</font></a>
	</center>

</body>
</html>