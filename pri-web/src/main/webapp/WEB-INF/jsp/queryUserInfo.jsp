<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/3/23
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>查询用户信息</title>
</head>
<body>
<div id="wrapper">
    <div>
        <div class="mainInner">
            <%--<ul >--%>
                <%--<li><a href="#" class="score-0">base</a><span class="direct">&gt;</span></li>--%>
                <%--<li><a href="#">查询用户信息</a></li>--%>
            <%--</ul>--%>
                <div >
                    <ul>
                        <li >
                            <label>用户名：</label>
                            <label>
                                <input type="text" id="username" name="username" value="${userInfoDto.userNmae}"/>
                            </label>
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="查询" class="btn Borange mb20" onclick="doSubmit()"/>
                        </li>
                    </ul>
                </div>
            <div>
                <table  border="px1"  width="400">
                    <thead>
                    <tr>
                        <td>用户名</td>
                        <td>手机号</td>
                        <td>邮箱</td>
                        <td>性别</td>
                        <td>上次登录地址</td>
                        <td>上次登录时间</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userInfoDto}" var="userInfo" varStatus="status">
                        <tr>
                            <td>${userInfo.userNmae}</td>
                            <td>${userInfo.moblie}</td>
                            <td>${userInfo.email}</td>
                            <td>${userInfo.sex}</td>
                            <td>${userInfo.lastLoginIp}</td>
                            <td>${userInfo.lastLoginTime}</td>
                            <%--<td>--%>
                                <%--<c:if test="${item.flag eq '0'}">--%>
                                    <%--已实名制--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${item.flag eq '1'}">--%>
                                    <%--未实名制--%>
                                <%--</c:if>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--<c:if test="${item.flag eq '0'}">--%>
                                    <%--<input type="button" onclick="certificationCancel('${item.usrOnlyid}')" value="注    销"></input>--%>
                                <%--</c:if>--%>
                            <%--</td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    function doSubmit() {
        var userName = $.trim($("#username").val());
        if (userName == ''){
            alert("用户名不能为空!");
            return false;
        }
        alert(userName);
        $.ajax({
            url : "${pageContext.request.contextPath}/queryInfo/user",
            type : "POST",
            data : {username:userName},
            dataType : "json",
            success : function (data) {
                alert(data);
            },
            error : function () {
                alert("${pageContext.request.contextPath}/queryInfo/user");
                alert("系统异常,请稍后再试!");
            }
        })
    }


</script>
</html>
