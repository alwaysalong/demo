<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎页面</title>
<script type="text/javascript">
	
</script>

</head>
<body>
	<center>
		<font color="blue">欢迎您: ${sessionScope.userName} 
	</center>
	<c:set var="username" scope="session" value="${sessionScope.userName}"/>
	<c:if test="${username == 'admin'}">
		<center>
			<a href="${pageContext.request.contextPath}/poiExcle/jump"><font color="blue">查看所有用户信息</font></a>
		</center>
	</c:if>


	<br>
	<center>
		<a href="${pageContext.request.contextPath}/login/logout">[退出]</a>
	</center>

</body>
</html>