<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$("#select").submit();
</script>

</head>
<body>


	<form id="select" action="/user/queryUserById" method="post">
		id: <input type="text" name="id" id="id" value="" /> <br> <input
			type="submit" onclick="select()" value="查询" />


	</form>

	<br />
	<br />
	<br /> id : ${requestScope.user.id}
	<br />
	<br /> 名字 : ${requestScope.user.name}
	<br />
	<br /> 年龄 : ${requestScope.user.age}
	<br />


</body>
</html>