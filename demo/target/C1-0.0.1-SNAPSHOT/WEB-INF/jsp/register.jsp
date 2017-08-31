<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="Public/js/jquery-easyui-1.3.1/jquery-1.8.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
    //按钮单击时执行
    $("#testAjax").click(function(){
          
          //Ajax调用处理
        $.ajax({
           type: "POST",
           url: "http://127.0.0.1:8080/user/ajaxDemo",
           data: {"id":1},
           success: function(data){
                    $("#myDiv").html('<h2>'+data+'</h2>');
              }
        });
        
     });
});

</script>

</head>
<body>
<div id="myDiv"><h2>通过 AJAX 改变文本</h2></div>
        <button id="testAjax" type="button">Ajax改变内容</button>

	<form id="select" action="/user/ajaxDemo" method="post">
		id: <input type="text" name="id" id="id" value="" /> <br> <input
			type="submit" value="查询" />


	</form>
<div id="div">



</div>


</body>
</html>