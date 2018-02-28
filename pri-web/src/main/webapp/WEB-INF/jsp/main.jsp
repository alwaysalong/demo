<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Private management space</title>
    <link rel="stylesheet" type="text/css" href="../../css/easyUI/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../css/easyUI/icon.css">
    <script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>
    <style type="text/css">
        body {
            font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
            padding: 0;
            margin: 0;
        }
        a:link {
            text-decoration: none;
        }
        a:visited {
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        a:active {
            text-decoration: none;
        }
        .cs-north {
            height:60px;background:#B3DFDA;
        }
        .cs-north-bg {
            width: 100%;
            height: 100%;
            background: url(themes/gray/images/header_bg.png) repeat-x;
        }
        .cs-north-logo {
            height: 40px;
            padding: 15px 0px 0px 5px;
            color:#fff;font-size:22px;font-weight:bold;text-decoration:none
        }
        .cs-west {
            width:200px;padding:0px;border-left:1px solid #99BBE8;
        }
        .cs-south {
            height:25px;background:url('themes/gray/images/panel_title.gif') repeat-x;padding:0px;text-align:center;
        }
        .cs-navi-tab {
            padding: 5px;
        }
        .cs-tab-menu {
            width:120px;
        }
        .cs-home-remark {
            padding: 10px;
        }
    </style>
    <script type="text/javascript">
        function addTab(title, url){
            if ($('#tabs').tabs('exists', title)){
                $('#tabs').tabs('select', title);//选中并刷新
                var currTab = $('#tabs').tabs('getSelected');
                var url = $(currTab.panel('options').content).attr('src');
                if(url != undefined && currTab.panel('options').title != 'Home') {
                    $('#tabs').tabs('update',{
                        tab:currTab,
                        options:{
                            content:createFrame(url)
                        }
                    })
                }
            } else {
                var content = createFrame(url);
                $('#tabs').tabs('add',{
                    title:title,
                    content:content,
                    closable:true
                });
            }
            tabClose();
        }
        function createFrame(url) {
            var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
            return s;
        }

        function tabClose() {
            /*双击关闭TAB选项卡*/
            $(".tabs-inner").dblclick(function(){
                var subtitle = $(this).children(".tabs-closable").text();
                $('#tabs').tabs('close',subtitle);
            })
            /*为选项卡绑定右键*/
            $(".tabs-inner").bind('contextmenu',function(e){
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });

                var subtitle =$(this).children(".tabs-closable").text();

                $('#mm').data("currtab",subtitle);
                $('#tabs').tabs('select',subtitle);
                return false;
            });
        }
        //绑定右键菜单事件
        function tabCloseEven() {
            //刷新
            $('#mm-tabupdate').click(function(){
                var currTab = $('#tabs').tabs('getSelected');
                var url = $(currTab.panel('options').content).attr('src');
                if(url != undefined && currTab.panel('options').title != 'Home') {
                    $('#tabs').tabs('update',{
                        tab:currTab,
                        options:{
                            content:createFrame(url)
                        }
                    })
                }
            })
            //关闭当前
            $('#mm-tabclose').click(function(){
                var currtab_title = $('#mm').data("currtab");
                $('#tabs').tabs('close',currtab_title);
            })
            //全部关闭
            $('#mm-tabcloseall').click(function(){
                $('.tabs-inner span').each(function(i,n){
                    var t = $(n).text();
                    if(t != 'Home') {
                        $('#tabs').tabs('close',t);
                    }
                });
            });
            //关闭除当前之外的TAB
            $('#mm-tabcloseother').click(function(){
                var prevall = $('.tabs-selected').prevAll();
                var nextall = $('.tabs-selected').nextAll();
                if(prevall.length>0){
                    prevall.each(function(i,n){
                        var t=$('a:eq(0) span',$(n)).text();
                        if(t != 'Home') {
                            $('#tabs').tabs('close',t);
                        }
                    });
                }
                if(nextall.length>0) {
                    nextall.each(function(i,n){
                        var t=$('a:eq(0) span',$(n)).text();
                        if(t != 'Home') {
                            $('#tabs').tabs('close',t);
                        }
                    });
                }
                return false;
            });
            //关闭当前右侧的TAB
            $('#mm-tabcloseright').click(function(){
                var nextall = $('.tabs-selected').nextAll();
                if(nextall.length==0){
                    //msgShow('系统提示','后边没有啦~~','error');
                    alert('后边没有啦~~');
                    return false;
                }
                nextall.each(function(i,n){
                    var t=$('a:eq(0) span',$(n)).text();
                    $('#tabs').tabs('close',t);
                });
                return false;
            });
            //关闭当前左侧的TAB
            $('#mm-tabcloseleft').click(function(){
                var prevall = $('.tabs-selected').prevAll();
                if(prevall.length==0){
                    alert('到头了，前边没有啦~~');
                    return false;
                }
                prevall.each(function(i,n){
                    var t=$('a:eq(0) span',$(n)).text();
                    $('#tabs').tabs('close',t);
                });
                return false;
            });

            //退出
            $("#mm-exit").click(function(){
                $('#mm').menu('hide');
            })
        }

        $(function() {
            tabCloseEven();
            $('.cs-navi-tab').click(function() {
                var $this = $(this);
                var href = $this.attr('src');
                var title = $this.text();
                addTab(title, href);
            });
        });
    </script>
</head>


<span style="display:none;">
<script src="http://s94.cnzz.com/stat.php?id=4106941&web_id=4106941" language="JavaScript"></script>
</span><body class="easyui-layout">
<div region="north" border="true" class="cs-north" >
    <div class="cs-north-bg"><div><font size="5" color="blue">Private management space</font><p align="right" style="font-size:16px;">欢迎您: <font color="blue">${sessionScope.userName}</font>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/login/logout"><font color="red">[退出]</font></a></p></div></div>
    <%--<div class="cs-north-bg"><div class="cs-north-logo">Private management space</div></div>--%>
</div>
<div region="west" border="true" split="true" title="Navigation" class="cs-west">
    <div class="easyui-accordion" fit="true" border="false">
        <div title="Base">
            <p><a href="javascript:void(0);" src="${pageContext.request.contextPath}/poiExcel/jump" class="cs-navi-tab">信息上传下载</a></p>
            <p><a href="javascript:void(0);" src="${pageContext.request.contextPath}/test/getTr" class="cs-navi-tab">获取td元素</a></p>
            <p><a href="javascript:void(0);" src="${pageContext.request.contextPath}/pageHelper/userList" class="cs-navi-tab">会员信息</a></p>
            <p><a href="javascript:void(0);" src="demo/droppable1.html" class="cs-navi-tab">droppable1</a></p>
            <p><a href="javascript:void(0);" src="demo/droppable2.html" class="cs-navi-tab">droppable2</a></p>
            <p><a href="javascript:void(0);" src="demo/resizable.html" class="cs-navi-tab">resizable</a></p>
            <p><a href="javascript:void(0);" src="demo/pagination.html" class="cs-navi-tab">pagination</a></p>
            <p><a href="javascript:void(0);" src="demo/searchbox.html" class="cs-navi-tab">searchbox</a></p>
            <p><a href="javascript:void(0);" src="demo/progressbar.html" class="cs-navi-tab">progressbar</a></p>
        </div>
        <div title="Layout">
            <p><a href="javascript:void(0);" src="demo/accordion.html" class="cs-navi-tab">accordion</a></p>
            <p><a href="javascript:void(0);" src="demo/layout.html" class="cs-navi-tab">layout</a></p>
            <p><a href="javascript:void(0);" src="demo/layout1.html" class="cs-navi-tab">layout1</a></p>
            <p><a href="javascript:void(0);" src="demo/layout2.html" class="cs-navi-tab">layout2</a></p>
            <p><a href="javascript:void(0);" src="demo/panel.html" class="cs-navi-tab">panel</a></p>
            <p><a href="javascript:void(0);" src="demo/panel2.html" class="cs-navi-tab">panel1</a></p>
            <p><a href="javascript:void(0);" src="demo/tabs.html" class="cs-navi-tab">tabs</a></p>
        </div>
        <div title="Menu and Button">
            <p><a href="javascript:void(0);" src="demo/menu.html" class="cs-navi-tab">menu</a></p>
            <p><a href="javascript:void(0);" src="demo/menubutton.html" class="cs-navi-tab">menubutton</a></p>
            <p><a href="javascript:void(0);" src="demo/linkbutton.html" class="cs-navi-tab">linkbutton</a></p>
            <p><a href="javascript:void(0);" src="demo/splitbutton.html" class="cs-navi-tab">splitbutton</a></p>
        </div>
        <div title="Form">
            <p><a href="javascript:void(0);" src="demo/form.html" class="cs-navi-tab">form</a></p>
            <p><a href="javascript:void(0);" src="demo/validatebox.html" class="cs-navi-tab">validatebox</a></p>
            <p><a href="javascript:void(0);" src="demo/combo.html" class="cs-navi-tab">combo</a></p>
            <p><a href="javascript:void(0);" src="demo/combobox.html" class="cs-navi-tab">combobox</a></p>
            <p><a href="javascript:void(0);" src="demo/combotree.html" class="cs-navi-tab">combotree</a></p>
            <p><a href="javascript:void(0);" src="demo/combogrid.html" class="cs-navi-tab">combogrid</a></p>
            <p><a href="javascript:void(0);" src="demo/numberbox.html" class="cs-navi-tab">numberbox</a></p>
            <p><a href="javascript:void(0);" src="demo/numberbox2.html" class="cs-navi-tab">numberbox1</a></p>
            <p><a href="javascript:void(0);" src="demo/datebox.html" class="cs-navi-tab">datebox</a></p>
            <p><a href="javascript:void(0);" src="demo/datetimebox.html" class="cs-navi-tab">datetimebox</a></p>
            <p><a href="javascript:void(0);" src="demo/calendar.html" class="cs-navi-tab">calendar</a></p>
            <p><a href="javascript:void(0);" src="demo/timespinner.html" class="cs-navi-tab">timespinner</a></p>
            <p><a href="javascript:void(0);" src="demo/numberspinner.html" class="cs-navi-tab">numberspinner</a></p>
            <p><a href="javascript:void(0);" src="demo/slider.html" class="cs-navi-tab">slider</a></p>
        </div>
        <div title="Window">
            <p><a href="javascript:void(0);" src="demo/window.html" class="cs-navi-tab">window</a></p>
            <p><a href="javascript:void(0);" src="demo/dialog.html" class="cs-navi-tab">dialog</a></p>
            <p><a href="javascript:void(0);" src="demo/messager.html" class="cs-navi-tab">messager</a></p>
        </div>
        <div title="DataGrid and Tree">
            <p><a href="javascript:void(0);" src="demo/datagrid.html" class="cs-navi-tab">datagrid</a></p>
            <p><a href="javascript:void(0);" src="demo/propertygrid.html" class="cs-navi-tab">propertygrid</a></p>
            <p><a href="javascript:void(0);" src="demo/tree.html" class="cs-navi-tab">tree</a></p>
            <p><a href="javascript:void(0);" src="demo/tree2.html" class="cs-navi-tab">tree1</a></p>
            <p><a href="javascript:void(0);" src="demo/treegrid.html" class="cs-navi-tab">treegrid</a></p>
            <p><a href="javascript:void(0);" src="demo/treegrid2.html" class="cs-navi-tab">treegrid1</a></p>
            <p><a href="javascript:void(0);" src="demo/treegrid3.html" class="cs-navi-tab">treegrid2</a></p>
        </div>
    </div>
</div>
<div id="mainPanle" region="center" border="true" border="false">
    <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
        <div title="Home">
            <div class="cs-home-remark">
                <h1>jQuery EasyUI 1.2.6 Demo</h1> <br>

            </div>
        </div>
    </div>
</div>

<div region="south" border="false" class="cs-south">Private management space</div>

<div id="mm" class="easyui-menu cs-tab-menu">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseother">关闭其他</div>
    <div id="mm-tabcloseall">关闭全部</div>
</div>
</body>
</html>