<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<form>
    <table border="1">
        <tr>
            <th width="5%">&nbsp;</th>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
        </tr>
        <tr>
            <td><input type="checkbox" name="isPack"></td>
            <td>1</td>
            <td>张三</td>
            <td>11</td>
            <td>男</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="isPack"></td>
            <td>2</td>
            <td>杨幂</td>
            <td>18</td>
            <td>女</td>
        </tr>
    </table>
</form>
<input type="submit" value="查询" id="button" onclick="aa()">
</body>
<script type="text/javascript">
$(document).ready(function () {
});

function aa() {
    var result = new Array();
    var count = 0;
    $("input:checkbox:checked").each(function (index, domEle) {
        var d = $(this).val();
        alert("d :" + d); //d :on
        //赋值为当前操作元素父元素的第一个同胞元素的内容
        var d1 = $(this).parent().siblings("td:eq(1)").text();
        alert("d1 :" + d1); //d1 :张三
        result.push({id:d,value:d1});
        count ++;
    });
    for (var i = 0; i < count; i++) {
        alert(result[i].id + "----------" + result[i].value); //on----------张三
    }
}


</script>
</html>
