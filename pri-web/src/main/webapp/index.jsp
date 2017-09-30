<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>jQuery 3D 垂直多级菜单 可筛选菜单项DEMO演示</title>

<link href="css/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />
<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />

<style type="text/css">
*{box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;}
body{background:#f0f0f0;}
.content{width:260px;margin:20px auto;}
.filterinput{
	background-color:rgba(249, 244, 244, 0);
	border-radius:15px;
	width:90%;
	height:30px;
	border:thin solid #FFF;
	text-indent:0.5em;
	font-weight:bold;
	color:#FFF;
}
#demo-list a{
	overflow:hidden;
	text-overflow:ellipsis;
	-o-text-overflow:ellipsis;
	white-space:nowrap;
	width:100%;
}

  <style type="text/css">
        .div1 {
            position: absolute;/*绝对定位，将DIV1外面的大框用绝对定位进行div的固定*/
            margin-left: 15%;/*距左15%的距离*/
            margin-top: 20px;/*距上20px的距离*/
            width: 1000px;/*宽设定1000px*/
            height: 400px;/*高设定400px*/
            background-color: yellow;
        }
        .div2 {
           position: relative;
            float: left;
            height: 400px;
            width: 300px;
            background-color: red;
        }
        .div3 {
           position: relative;
            float: left;
            height: 200px;
            width: 10px;
            background-color: blue;
        }
       .div4 {
            position: relative;
            float: left;
            height: 200px;
            width: 10px;
           background-color: pink;
        }
    </style>
</style>

<script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="js/jquery-accordion-menu.js" type="text/javascript"></script>
<script type="text/javascript">

$(function(){	
	//顶部导航切换
	$("#demo-list li").click(function(){
		$("#demo-list li.active").removeClass("active")
		$(this).addClass("active");
	})	
})	
</script>
</head>
<body>
<div style="text-align:left;clear:both">这是头.......................................................
<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
<script src="/follow.js" type="text/javascript"></script>
</div>
<div class="content" >

	<div id="jquery-accordion-menu" class="jquery-accordion-menu red" >
		<div class="jquery-accordion-menu-header" id="form"></div>
		<ul id="demo-list">
		 
		   <li class="active"><a href="#"><i class="fa fa-home"></i>Home </a></li>
			<li><a href="#"><i class="fa fa-glass"></i>Events </a></li>
			<li><a href="#"><i class="fa fa-file-image-o"></i>Gallery </a><span class="jquery-accordion-menu-label">
				12 </span></li>
			<li><a href="#"><i class="fa fa-cog"></i>Services </a>
				<ul class="submenu">
					<li><a href="#">Web Design </a></li>
					<li><a href="#">Hosting </a></li>
					<li><a href="#">Design </a>
						<ul class="submenu">
							<li><a href="#">Graphics </a></li>
							<li><a href="#">Vectors </a></li>
							<li><a href="#">Photoshop </a></li>
							<li><a href="#">Fonts </a></li>
						</ul>
					</li>
					<li><a href="#">Consulting </a></li>
				</ul>
			</li>
			<li><a href="#"><i class="fa fa-home"></i>系统管理 </a></li>
			<li><a href="#"><i class="fa fa-suitcase"></i>Portfolio </a>
				<ul class="submenu">
					<li><a href="#">Web Design </a></li>
					<li><a href="#">Graphics </a><span class="jquery-accordion-menu-label">10 </span>
					</li>
					<li><a href="#">Photoshop </a></li>
					<li><a href="#">Programming </a></li>
				</ul>
			</li>
			<li><a href="#"><i class="fa fa-user"></i>About </a></li>
			<li><a href="#"><i class="fa fa-envelope"></i>Contact </a></li>
		   
		</ul>
		<div class="jquery-accordion-menu-footer">
			Footer
		</div>
	</div>
	 <div class="div3">3</div>
        <div class="div4">4</div>
</div>

<script type="text/javascript">
(function($) {
$.expr[":"].Contains = function(a, i, m) {
	return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
};
function filterList(header, list) {
	//@header 头部元素
	//@list 无需列表
	//创建一个搜素表单
	var form = $("<form>").attr({
		"class":"filterform",
		action:"#"
	}), input = $("<input>").attr({
		"class":"filterinput",
		type:"text"
	});
	$(form).append(input).appendTo(header);
	$(input).change(function() {
		var filter = $(this).val();
		if (filter) {
			$matches = $(list).find("a:Contains(" + filter + ")").parent();
			$("li", list).not($matches).slideUp();
			$matches.slideDown();
		} else {
			$(list).find("li").slideDown();
		}
		return false;
	}).keyup(function() {
		$(this).change();
	});
}
$(function() {
	filterList($("#form"), $("#demo-list"));
});
})(jQuery);	
</script>

<script type="text/javascript">

	jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
	
</script>

</body>
</html>
