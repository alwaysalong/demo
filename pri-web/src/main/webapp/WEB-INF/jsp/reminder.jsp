<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面跳转</title>
<script type="text/javascript">
	var i = 3;
	var intervalid;
	intervalid = setInterval("fun()", 1000);
	function fun() {
		if (i == 0) {
			window.location.href = "${pageContext.request.contextPath}/main";
			clearInterval(intervalid);
		}
		document.getElementById("mes").innerHTML = i;
		i--;
	}
</script>

</head>
<body>

	<center>
		<p><font>恭喜,您已成为第<font color="red" size="6"> ${sessionScope.userId}</font>位会员,<span id="mes">3</span>秒钟后将自动跳转到登录页面...</font></p>
		<p><font>如未自动跳转,请点击</font><a><font color="blue">这里</font></a>...</p>
	</center>

</body>
</html>