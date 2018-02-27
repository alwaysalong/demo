<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>JavaScript菜单侧边展开(改良版)</title>
</head>
<body>
<ul id="navigation">
    <li><a href="#">系统管理</a>
        <ul>
            <li><a href="${pageContext.request.contextPath}/main">地区设置</a></li>
            <li><a href="#">分行设置</a></li>
            <li><a href="#">银行操作用户设置</a></li>
            <li><a href="#">密码修改</a></li>
        </ul>
    </li>
    <li><a href="#">学校管理</a>
        <ul>
            <li><a href="#">学校设置</a></li>
            <li><a href="#">学校查询</a></li>
        </ul>
    </li>
    <li><a href="#">基础信息管理</a></li>
    <li><a href="#">统计系统查询</a></li>
</ul>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script>
    var $j = jQuery.noConflict();
    $j(function(){
        var lis = document.getElementById("navigation").getElementsByTagName("li");
        for (var i=0; i<lis.length; i++) {
            lis[i].onmouseover=function() {
                var subMenu = this.getElementsByTagName("ul")[0];
                subMenu.style.display = "block";
                var subcolor = this.getElementsByTagName("a")[0];
                subcolor.style.background = "lightblue";
            }
            lis[i].onmouseout=function() {
                var subMenu = this.getElementsByTagName("ul")[0];
                subMenu.style.display = "none";
                var subcolor = this.getElementsByTagName("a")[0];
                subcolor.style.background = "";
            }
        }
    });
</script>
<script src="js/jquery-1.8.3.min.js"></script>
</body>
</html>