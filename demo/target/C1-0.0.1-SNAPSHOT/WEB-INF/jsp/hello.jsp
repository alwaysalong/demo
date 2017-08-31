<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function select() {
		var id = document.getElementById("id");
	}
</script>
</head>
<body>
	要查询的学生的学号:<input type="text" name="id" id="id" value="">
	<br>
	<br>
	<input type="submit" name="查询" onclick="select()">
</body>
</html>