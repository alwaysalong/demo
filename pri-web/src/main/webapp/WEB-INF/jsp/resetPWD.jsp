<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/easyUI/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../css/easyUI/icon.css">
    <script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate.extend.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>修改密码</title>
    <style>
        label {
            display: inline-block;
            width: 100px;
            text-align: right;
        }

        .error {
            color: red;
            font-size: 1px
        }

        div {
            padding: 5px 0;
        }
    </style>
    <!-- 让背景图片覆盖整个屏幕  -->
    <style type="text/css">
        body {
            background-image: url(../../image/backgr.jpg);
            background-size: cover;
        }
    </style>

    <script>
        $(function() {
            // 在键盘按下并释放及提交后验证提交表单
            $("#resetForm").validate({
                rules : {
                    userName : {
                        required : true,
                        username : true
                    },
                    passWord : {
                        required : true,
                        pwd : true
                    },
                    newPassWord:{
                        required : true,
                        pwd : true
                    },
                    newPassWord1 : {
                        required : true,
                        equalTo : "#newPassWord"
                    },
                    inputCode : {
                        required : true,
                        code : true
                    }
                },
                messages : {
                    userName : {
                        required : "请输入用户名"
                    },
                    passWord : {
                        required : "请输入原密码"
                    },
                    newPassWord:{
                        required : "请输入新密码"
                    },
                    passWord1 : {
                        required : "请输入新密码",
                        equalTo : "两次新密码输入不一致"
                    },
                    inputCode : {
                        required : "请输入验证码"
                    }
                }
            });
        });
        <%--$('#resetForm').form({--%>
            <%--url: '${pageContext.request.contextPath}/register/updatePWD',--%>
            <%--onSubmit: function () {--%>
                <%--return $(this).form('validate');--%>
            <%--},--%>
            <%--success: function (data) {--%>
                <%--$.messager.alert('Info', data, 'info');--%>
            <%--}--%>
        <%--});--%>
        <%--$.extend($.fn.validatebox.defaults.rules, {--%>
            <%--/*必须和某个字段相等*/--%>
            <%--equalTo: {--%>
                <%--validator:function(value,param){--%>
                    <%--return $(param[0]).val() == value;--%>
                <%--},--%>
                <%--message:'字段不匹配'--%>
            <%--},--%>
            <%--valiPWD:{--%>
                <%--validator:function (value,param) {--%>
                    <%--var mobile = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;--%>
                    <%--return mobile.test(value);--%>
                <%--},--%>
                <%--message:'请输入正确的手机号码'--%>

            <%--}--%>
        <%--});--%>
    </script>
    <script type="text/javascript" src="../../js/checkCode.js"></script>

</head>
<body>
<h3 align="center">修改密码</h3>
<center>
    <form action="${pageContext.request.contextPath}/register/updatePWD"
          id="resetForm" name="resetForm" method="post">
        <div>
            <label>登录名：</label> <input type="text"  name="userName"
                                       id="userName"
                                       placeholder="请输入账号"/>
        </div>
        <div>
            <label>原密码：</label> <input name="passWord" type="password"
                                       id="passWord" placeholder="请输入原密码"/>
        </div>
        <div>
            <label>新密码：</label> <input name="newPassWord" type="password"
                                       id="newPassWord" placeholder="请输入新密码"/>
        </div>
        <div>
            <label>确认新密码：</label> <input name="newPassWord" type="password"
                                         id="newPassWord1" placeholder="请再输入一次新密码"/>
        </div>
        <div>
            <input type="button" id="code" onclick="createCode()"/> <a
                href="javascript:void(0)" onclick="createCode()"> <font
                color="blue" size="2px">换一张</font></a> <br> <label>验证码:</label> <input
                type="text" id="inputCode" name="inputCode" placeholder="请输入验证码"/>
        </div>
        <div id="error_msg">
            <font color="red">${sessionScope.error}</font> <br>
        </div>
        <div align="center">
            <input type="submit" value="确认修改" class="btn Borange mb20">
            <!-- <input type="button" value="提交注册" class="btn Borange mb20" onclick="doSubmit()"/> -->
        </div>
    </form>
</center>

<script type="text/javascript">
</script>
</body>
</html>