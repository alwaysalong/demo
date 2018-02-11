<%--  <%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.xxx.web.util.*"%> --%>
<%--
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理平台</title>
    <link rel="stylesheet" type="text/css" href="<%=Constants.GLOBAL_IMAGE_SERVER_SSL%>/tomshopping/c/sys/100920/admin.css"/>
	<link rel="stylesheet" type="text/css" href="<%=Constants.GLOBAL_IMAGE_SERVER_SSL%>/tomshopping/c/sys/110223/xxx-card-admin.css"/>
    <script type="text/javascript" src="<%=Constants.GLOBAL_IMAGE_SERVER_SSL%>/j/lib/jquery.js"></script>
    <script type="text/javascript" src="<%=Constants.GLOBAL_IMAGE_SERVER_SSL%>/j/jend/jend.js"></script>
    <script type="text/javascript" src="<%=Constants.GLOBAL_xxxCARD_SERVER%>/js/formvalidator/jquery.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
	//分页插件的查询参数
	function handleQueryParam(url, paramName) {
		var startIndex = url.indexOf(paramName);
		if (startIndex > 0) {
			var headUrl = url.substring(0, startIndex);
			var tailUrl = url.substr(startIndex);
			
			if (tailUrl.indexOf('&') < 0) {
				tailUrl = "";
			} else {
				tailUrl = tailUrl.substr(tailUrl.indexOf("&") + 1);
			}
			
			url = headUrl + tailUrl;
		}
		return url;
	}
	
	//分页插件的点页码跳转
	function gotoPage(cpage) {
		var formName = "queryform";
		var currentUrl = window.location.href;
		currentUrl = handleQueryParam(currentUrl, "pagination.perpage");
		currentUrl = handleQueryParam(currentUrl, "pagination.cpage");
	
		var lastChar = currentUrl.charAt(currentUrl.length - 1);
		if (lastChar == '?' || lastChar == '&') {
			currentUrl += "pagination.cpage=" + cpage;
		} else if (currentUrl.indexOf('=') > 0) {
			currentUrl += "&pagination.cpage=" + cpage;
		} else {
			currentUrl += "?pagination.cpage=" + cpage;
		}
		
	    document.getElementById(formName).action = currentUrl;
	    document.getElementById(formName).method = "post";
	    document.getElementById(formName).submit(); 
	}
	
	$(document).ready(function() {
		//时间控件 至少可下载31天的数据
		$("#datefrom").click(function() {
			WdatePicker({el:'createStartTime'});
		});
		$("#dateto").click(function() {
			WdatePicker({el:'createEndTime'});
		});
    });
	
	function fmSubmit() {
		var startDate=new Date($("#createStartTime").val());
		var endDate=new Date($("#createEndTime").val());
		if($.trim($("#createStartTime").val())=='' ^ $.trim($("#createEndTime").val())==''){
			JEND.page.alert("请输入完整的时间区间! ");
			return false;
		}
		if(endDate-startDate<0){
			JEND.page.alert("开始日期要小于结束日期！");
			return false;
		}
		document.queryform.action = "${pagecontext.request.getcontextpath}/xxxWalletWeb/accountOrder/queryAccountOrderList.action";
		document.queryform.submit();
	}
	
	//下载账户日统计列表 
	function downLoadOnclick() {
		var startDate=new Date($("#createStartTime").val());
		var endDate=new Date($("#createEndTime").val());
		if($.trim($("#createStartTime").val())=='' ^ $.trim($("#createEndTime").val())==''){
			JEND.page.alert("请输入完整的时间区间! ");
			return false;
		}
		if(endDate-startDate<0){
			JEND.page.alert("开始日期要小于结束日期！");
			return false;
		}
// 		var dates=parseInt((endDate-startDate)/1000/60/60/24);
		
// 		if(dates>31){
// 			JEND.page.alert("日期区间最多为31天！");
// 			return false;
// 		}
			document.queryform.target = "_self";
		    document.queryform.action = "${pageContext.request.contextPath}/accountOrder/downLoadAccountOrderInfo.action";
		    document.queryform.submit();
	}
	</script>
<style type="text/css" media="screen">
	html {background:none repeat scroll 0 0 #FFFFFF;height:100%;}
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
		    <li><a href="#" class="score-0">系统管理</a><span class="direct">&gt;</span></li>
		    <li><a href="#">订单查询</a></li>
		  </ul>
		  <form id="queryform" name="queryform" method="post" >
		  
		  	<input type="hidden" name="pagination.perpage" value="10"/>
		  	
              <div class="Dgw mb10">
                  <s:fielderror />
                  <ul class="colunm">
                  	 <li class="mr30">
                      <label>订单号：</label>
                      <label>
                          <input type="text" id="orderId" name="dto.orderId" value="${dto.orderId}" class="Bgray h20 w200 pl5"/>
                      </label>
                  </li>
                  <li class="mr30">
                      <label>请求流水号：</label>
                      <label>
                          <input type="text" id="reqNo"  name="dto.reqNo" value="${dto.reqNo}"  class="Bgray h20 w200 pl5"/>
                      </label>
                  </li>
                  <li class="mr30">
                      <label>用户ID：</label>
                      <label>
                          <input type="text" id="userOnlyId"  name="dto.userOnlyId" value="${dto.userOnlyId}"  class="Bgray h20 w200 pl5"/>
                      </label>
                  </li>
                   </ul>
                    <ul class="colunm">
                  <li class="mr30">
                  <!-- 回显下拉框状态 -->
                       <label class="search_lable">状态：</label>
                <select id="status" name="dto.status" value="${dto.status}">
               		<option value=""  <c:if test="${'' eq dto.status}">selected</c:if>>--请选择--</option>
               		<option value="0" <c:if test="${'0' == dto.status}">selected</c:if>>待充值</option>
               		<option value="1" <c:if test="${'1' == dto.status}">selected</c:if>>充值中</option>
               		<option value="2" <c:if test="${'2' == dto.status}">selected</c:if>>充值成功</option>
               		<option value="3" <c:if test="${'3' == dto.status}">selected</c:if>>充值失败</option>
               </select>
                  </li>
                   <li class="mr30">
                      <label>支付流水号：</label>
                      <label>
                          <input type="text" id="payId"  name="dto.payId" value="${dto.payId}"  class="Bgray h20 w200 pl5"/>
                      </label>
                  </li>
                   <li class="mr30">
                      <label>支付方式：</label>
                      <label>
                          <input type="text" id="payType"  name="dto.payType" value="${dto.payType}"  class="Bgray h20 w200 pl5"/>
                      </label>
                  </li>
                  <li class="mr30">
                  <!-- 时间插件  时间区间 -->
                          <label>统计日期：<em class="cg mh5"></em>
                              <s:textfield id="createStartTime" name="dto.createStartTime" readonly="true" cssClass="Bgray w90 h20 tac"/>
                              <span id="datefrom" class="mh5 datefrom"></span></label>
                          <label><em class="cg mh5">至</em>
                              <s:textfield id="createEndTime" name="dto.createEndTime" readonly="true" cssClass="Bgray w90 h20 tac"/>
                              <span id="dateto" class="mh5 dateto"></span></label>
                      </li>
                  </ul>
              </div>
		    <input type="button" value="  查 询  " class="btn Borange mb20" onclick="fmSubmit()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <input type="button"  name=downLoad  value="  下 载   " class="btn Borange mb20" onclick="downLoadOnclick();" />
		  </form>
		  <p class="result mb10">当前查询结果：共有<em class="cdr fw7"> <s:property value="pagination.total"/> </em> 条记录，
		  			分<em class="cdr fw7"> <s:property value="total"/> </em>页显示。 
		  			</p>
		  <table class="tab mb10">
		    <thead>
		       <tr >
		       		<td class="tit">用户ID</td>
                	<td class="tit">订单号</td>
                	<td class="tit">请求流水号</td>
                	<td class="tit">支付流水号</td>
                	<td class="tit">状态</td>
                	<td class="tit">订单金额</td>
                	<td class="tit">订单创建时间</td>
                	<td class="tit">交易完成时间</td>
                	<td class="tit">支付方式</td>
                </tr>
		    </thead>
		    <tbody>
		    	<c:if test="${empty pagination.list}">
						<tr style="background-color: rgb(255, 255, 255);">
							<td colspan='11'> 对不起，没有找到相关的数据！</td>
						</tr>
					</c:if>
					<c:if test="${!empty pagination.list}">
		      <s:iterator value="pagination.list" id="info" status="u">
                <s:set name="cp" value="%{pagination.cpage}"/>
                <s:set name="ps" value="%{pagination.perpage}"/>
                 <tr>
                 	<td><s:property value="#info.userOnlyId"/></td>
                	<td><s:property value="#info.orderId"/></td>
                	<td><s:property value="#info.reqNo"/></td>
                	<td><s:property value="#info.payId"/></td>
                	<td><s:property value="#info.status"/></td>
                	<td><s:property value="#info.orderMoney"/></td>
                	<td><s:property value="#info.orderCreateTime"/></td>
                	<td><s:property value="#info.payTime"/></td>
                	<td><s:property value="#info.payType"/></td>
                </tr>
              </s:iterator>
               </c:if>
		    </tbody>
		  </table>
		  <!-- 页码 -->
		  <div align="right"><p:pages cpage="%{pagination.cpage}" total="%{total}" perpage="%{pagination.perpage}" url="%{url}" barType="all" /></div>
		</div>
	</div>
</div>
</body>
</html> --%>