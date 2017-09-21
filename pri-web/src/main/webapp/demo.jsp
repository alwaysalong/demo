<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax</title>

<script type="text/javascript">
$(document).ready(function(){
});
function aa() {
	$.ajax({
		url : "${pageContext.request.contextPath}/demo/demo",
		type : "POST",
		data : {username:$("#username").val()},
		dataType : "json",
		success:function(data){
			$("#sex").val(data.sex);
			$("#mobile").val(data.mobile);
			$("#email").val(data.email);
			$("#address").val(data.address);
		},
		error: function(){  
			   alert('error');  
		}  
	});
}
</script>
</head>
<body>
	<div>username:<input type="text" id="username" name="username" onblur="aa()"></div>
	<div>sex:<input type="text" id="sex" name="sex"></div>
	<div>mobile:<input type="text" id="mobile" name="mobile"></div>
	<div>email:<input type="text" id="email" name="email"></div>
	<div>address:<input type="text" id="address" name="address"></div>
</body>
</html>