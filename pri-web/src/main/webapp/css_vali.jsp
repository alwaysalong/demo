<%-- <%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.demo.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>后台管理系统</title>
	
	<link rel="stylesheet" type="text/css" href="http://com/c/sys/100920/admin.css"/>
	<link rel="stylesheet" type="text/css" href="http://com/c/sys/110223/-admin.css"/>
	<link rel="stylesheet" type="text/css" href="http://com/100522/common.css"/>
	<link rel="stylesheet" type="text/css" href="http://om/100522/shengqing.css"/>
	
	<script type="text/javascript"
		src="<%=Constants.get(" ") %>/js/jquery.js"></script>
	<script type="text/javascript"
		src="<%=Constants.get(" ") %>/js/formValidator.js"></script>
	<script type="text/javascript"
		src="<%=Constants.get(" ") %>/js/formValidatorRegex.js"></script>
	<script type="text/javascript" language="javascript">
	
	function fmSubmit(){
		var am = $.trim($("#amount").val());
		var re = /^[0-9]*$/;
		if ($.trim($("#amount").val()) == '') {
			alert("优惠券金额不能为空！");
			return false;
		}
		if (!re.test(am)) {
			alert("优惠券金额只能是整数!");
			return false;
		}
		if ($.trim($("#status").val()) == '-1') {
			alert("请选择状态类型!");
			return false;
		}
		var gnl=confirm("确定要修改吗?");
		if (gnl == false) {
			return false;
		}
		document.dataForm.submit();
	}
	</script>
<style type="text/css" media="screen">
html {
	background: none repeat scroll 0 0 #FFFFFF;
	height: 100%;
}
</style>
<style type="text/css">
body{
background:no-repeat fixed top
}
</style>
</head>
<body>
	<div id="wrapper">
		<div>
			<div class="mainInner">
				<ul class="crumbs">
					<li><a class="score-0" href="<%=Constants.get("") %>/main.action">
					后台管理系统</a><span class="direct">&gt;</span></li>
					<li><a href="#">信息修改</a><span class="direct">&gt;</span></li>
				</ul>
				<div class="mainContent">
					<form name="dataForm" method="post"
						action="<%=Constants.get("") %>/couponCloudSearch/editInfo.action">
						<div class="search">
                <ul class="s_list">
                <li>
                <label class="search_lable">优惠券号：</label>
                <input type="text" id="couponCode" name="couponCode" value="${couponInfoCloudSearch.couponCode}"  readonly="readonly"/>
                </li>
                <li>
                <label class="search_lable">批次号：</label>
                <input type="text" id="batchId" name="batchId" value="${couponInfoCloudSearch.batchId}" readonly="readonly"/>
                </li>
                <li>
                <label class="search_lable">优惠券金额：</label>
                <input type="text" id="amount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value="${couponInfoCloudSearch.amount}" />
                </li>
                <li>
                <label class="search_lable">状态：</label>
               <select id="status" name="status" value="${couponInfoCloudSearch.status}">
               		<option value="-1">--请选择--</option>
               		<option value="0" <c:if test="${'0' eq couponInfoCloudSearch.status}">selected</c:if> >作废</option>
               		<option value="1" <c:if test="${'1' eq couponInfoCloudSearch.status}">selected</c:if> >已发放</option>
               		<option value="2" <c:if test="${'2' eq couponInfoCloudSearch.status}">selected</c:if> >已使用</option>
               		<option value="3" <c:if test="${'3' eq couponInfoCloudSearch.status}">selected</c:if> >发放中</option>
               </select>
                </li>
                </ul>
                <div class="clear"></div>
              </div>
              <div class="btn_area">
				<input type="button" class="s_btn" value="提交修改" onclick="fmSubmit()" />
			  </div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>


</html> --%>