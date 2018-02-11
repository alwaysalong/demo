<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<title>Insert title here</title>  
</head>  
<script type="text/javascript">
	$(document).ready(function(){
 
 });
	
	function submit1() {
		var id = $("#id").val();
		 if(id == ""){
			 $("#userInfo").hide();
			 alert("请输入用户id");
			 
			 return false;
		 }
		 $("#theForm").submit();
	}
	
	
</script>
<body>  
<form action="${pageContext.request.contextPath}/LoginServlet" id="theForm" name="theForm" method="post">
		<input type="text" id="id" name="id">
		<input type="button" name="aa" value="查询" onclick="submit1()">
</form>
<div id="userInfo">
	<%-- <%request.getAttribute("userInfo"); %> --%>
	${sessionScope.userInfo }
	</div>
</body>  
</html>  