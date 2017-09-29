<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页查询</title>

<style>
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<center>
		<!-- table标签 是表格  -->
		<table width="200" border="1">
			<tr bgcolor="blue">
				<th scope="col">ID</th>
				<th scope="col">用户名</th>
				<th scope="col">email</th>
				<th scope="col">mobile</th>
				<th scope="col">sex</th>
				<th scope="col">是否活跃</th>
			</tr>
			<c:forEach begin="0" step="1" items="${userList}" var="list"
				varStatus="userlist">


				<tr>
				<c:if test="${userlist.count%2==0}">
					<tr bgcolor="yellow">
				</c:if>
				<c:if test="${userlist.count%2!=0}">
				</c:if>
					<td>${list.id}</td>
					<td>${list.userName}</td>
					<td>${list.email}</td>
					<td>${list.mobile}</td>
					<td>${list.sex}</td>
					<c:if test="${list.status==0}">
						<td>活跃</td>
					</c:if>
					<c:if test="${list.status==1}">
						<td>沉寂</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<p>一共${page.pages}页</p>
		<a href="userList?page=${page.firstPage}">第一页</a> <a
			href="userList?page=${page.nextPage}">下一页</a> <a
			href="userList?page=${page.prePage}">上一页</a> <a
			href="userList?page=${page.lastPage}">最后页</a>
	</center>

</body>
</html>