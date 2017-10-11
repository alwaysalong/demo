<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>excel上传下载</title>
<style>
</style>
</head>
<body>
	<a href="${pageContext.request.contextPath}/poiExcel/download"
		id="download_id" style="text-decoration: none; color: blue;">用户信息下载↓↓↓</a>
		<br>
		<br>
	<form
		action="${pageContext.request.contextPath}/poiExcel/upload"
		id="uploadForm" name="uploadForm" method="post"
		enctype="multipart/form-data">
		<!-- <input type="text" id="upfile" name="upfile" readonly="true" placeholder="上传文件"> -->
		<input type="file" id="uploadFile" name="uploadFile">
		<br>
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="确认上传" onclick="doSubmit()">
		</form>
		<div>
			<c:set var="msg" scope="session" value="${sessionScope.msg }"/>
			<c:if test="${msg != null }">
			
				<font color="red">${sessionScope.msg }</font>
			</c:if>
		</div>
</body>
<script type="text/javascript">
function doSubmit(){
	 var uploadfile = $("#uploadFile").val();
	 alert(uploadfile);
	 var xlsFile = uploadfile.substring(uploadfile.length-4,uploadfile.length);
	 var xlsxFile = uploadfile.substring(uploadfile.length-5,uploadfile.length);
	 if(uploadfile == ""){
		 alert("请上传excle文件!");
		 return false;
	 }
	 if(xlsFile != ".xls" && xlsxFile != ".xlsx"){
		 alert("请上传正确的excle文件!")
		 return false;
	 }
	 $("#uploadForm").submit();
}
</script>
</html>