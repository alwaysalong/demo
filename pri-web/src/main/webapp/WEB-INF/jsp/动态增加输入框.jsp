<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/provinceTubeCoupon/com/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>**************</title>
	<link rel="stylesheet" href="https://i0.**.com/**/c/sys/csslib/110119/APE-admin.css" />
	<link rel="stylesheet" href="https://i0.**.com/**/c/sys/130111/template-admin.css" />
	<link rel="stylesheet" href="https://i0.**.com/**/c/sys/2013/0725/adminsales.css">
	<style type="text/css">
		.w90 {width: 90px;}
		.line_1{height: 2px; background: #f3f3f3; border-top: 1px solid #CCCCCC;}
		.button_div{margin: 10px 0 10px 80px;}
	</style>
	<script src="https://i0.**.com/j/lib/jquery.js"></script>
	<script type="text/javascript"src="https://i0.**.com/j/jend/jend.js?v=1.6.17"></script>
	<script src="../../js/datepicker/WdatePicker.js"></script>
<style>
input[type="text"]{height: 25px;width:153px;}
.s_select{height: 25px;}
.tab thead th{text-align: center;font-weight:normal;}
</style>
<script type="text/javascript" language="javascript">
	$(function(){
		$("#cardCodeUl").hide();
		$("#cardCodeText").hide();
		$("#carType").css("color","#888");
		$("#cardCode1").css("color","#888");
		 //��ȡ����
		$("#carType").focus(function(){
			$("#carType").css("color","#000");
	      	  var curValue=$(this).val();
			  if(curValue==this.defaultValue){
	             	$(this).val("");	  	
			  }
		});
		
		//ʧȥ����
		$("#carType").blur(function(){
	       var curValue=$(this).val();
		   if($.trim(curValue)==""){
			   $("#carType").css("color","#888");
		   	 $(this).val(this.defaultValue);
		   }
		});
		//�޸ģ�**��ȡ����
		$("#cardCode1").focus(function(){
			$("#cardCode1").css("color","#000");
			var curValue = $(this).val();
			if(curValue==this.defaultValue){
				$(this).val("");
			}
		});
		//�޸ģ�ʧȥ����
		$("#cardCode1").blur(function(){
			var curValue = $(this).val();
			if($.trim(curValue)==""){
				$("#cardCode1").css("color","#888");
				$(this).val(this.defaultValue);
			}
		});
		//���**��
		$('#newAdd').bind('click',function(){
			var length = $(document).find('[name^="cardStageUlSub"]').length;
			if(length>=10){
				JEND.page.alert('********!');
				$(this).attr('disabled',true);
				return ;
			}
			addCardStage();
		});
	});
	function addCardStage(){
		var ulHtml = '<ul class="colunm" name="cardStageUlSub">'+
	       '<li class="mr30">'+
           '<label class="label">********�� '+
             '<input type="text" name="cardStageStart" class="s_textbox"  maxlength="16"/>'+
             '&nbsp;&nbsp; ��������&nbsp;&nbsp; '+
             '<input type="text" name="cardStageEnd" class="s_textbox"  maxlength="16"/>'+
              '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
              '<input type="button" value=" ɾ�� " onclick="deleteElement(this)"/>'+
           '</label>'+
	        '</li>'+
     '</ul>';
     $("#cardStageUl").append(ulHtml);
		
	}
	//�ͷ�������ť
	function addBtnDisabled(){
		var length = $(document).find('[name^="cardStageUlSub"]').length;
		if(length<=10){
			$("#newAdd").attr('disabled',"");
		}
	}
	//ɾ�������Ŷ�
	function deleteElement(elemnt){
		JEND.page.confirm('ȷ��ɾ��********��',function(){
		$(elemnt).parent().parent().parent().remove();
		//�ͷ�������ť
		addBtnDisabled();
		});
	}
	//��ʾ**����ҳ��
	function toShowCardCode(){
		$('ul[name^="cardStageUlSub"]').remove();
		addCardStage();
		$("#cardStageStartId").val("");
		$("#cardStageEndId").val("");
		$("#cardStageUl").hide();
		$("#cardStageBtn").hide();
		$("#cardCodeUl").show();
		$("#cardCodeText").show();
		 var curValue=$("#cardCode1").val();
		   if($.trim(curValue)==""){
			   $("#cardCode1").css("color","#888");
			   $("#cardCode1").val("********");
		   }
	}
	//��ʾ��������ҳ��
	function toShowCardStage(){
		//�ͷ�������ť
		addBtnDisabled();
		$("#cardCode1").val("");
		$("#cardCode2").val("");
		$("#cardCodeUl").hide();
		$("#cardCodeText").hide();
		$("#cardStageUl").show();
		$("#cardStageBtn").show();
		
	}
	//��ת����ѯҳ��
	function personalityCardPage(){
		window.location.href='${pagecontext.request.getcontextpath}/**WalletCpWeb/provinceTubeCoupon/personalityCard/personalityCardQuery.action';
	}

	//**У�鲢�ύ
	function checkCardCodeLength(elmentId){
		var flag= true;
		var str=$("#"+elmentId).val(); //����һ�ַ��� 
		if($.trim(str)=="********"||$.trim(str)==""){
			JEND.page.alert("********��");
			return;
		}
		str=str.replace(/��/g,',');
		$("#"+elmentId).val(str);
		var strs= new Array(); //����һ���� 
        strs=str.split(","); //�ַ��ָ� 
        for(var i=0;i<strs.length;i++){
    		if(!(/^[0-9]*$/.test(strs[i]))){
    			JEND.page.alert("********����");
            	flag=false;
            	return;
    		}
    	}
        if(flag){//У���ύ����
        	var cardTypeName = $.trim($("#carType").val());
        	JEND.page.confirm('<div align="left"><h3>ȷ��Ҫ������?</h3></div>',function(){
				JEND.util.dialog.showLoading({loadingText:'���ڼ������ݣ����Ժ�'});
	        	$.ajax({
					url:'${pagecontext.request.getcontextpath}/**WalletCpWeb/provinceTubeCoupon/personalityCardJson/editPersonalityCard.action',
					data:{"cardCodes":str,"bingdingType":"1","cardTypeName":cardTypeName},
					type:'POST',
					success:function(data){
						var obj = eval('(' + data + ')'); 
						if(obj.flag=="T"){
							JEND.page.alert("����ɹ���");
							setTimeout("personalityCardPage()",1000);
						}else{
							JEND.page.alert(obj.msg);
							var arr = obj.errorMsg;
							if(arr!=null){
								addInfo(arr);
							}
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						JEND.page.alert("�ܱ�Ǹ�����緱æ�����Ժ����ԣ�");
					}
				});
        	});
        	/* document.searchForm.target = "_self";
    	    document.searchForm.action = "${pageContext.request.contextPath}/personalityCard/editPersonalityCard.action";
    	    document.searchForm.submit(); */	
        }
        
	}
	
	 //�Ŷ�У�鲢�ύ
	function checkCardStageAndSubmit(){
		var flag = true;
		var cardStageCount = document.getElementsByName("cardStageUlSub");
		if(cardStageCount.length<1){
			flag=false;
			JEND.page.alert("����ӺŶΣ�");
			return;
		}
		$('input[name^="cardStage"]').each(function(index,val){
			if($(this).val()==null||$(this).val()==''){
				flag=false;
				JEND.page.alert("�Ŷβ����п�ֵ��");
				return;
			}else if(!(/^[0-9]*$/.test($(this).val()))){
				flag=false;
				JEND.page.alert("�Ŷα���ȫ�����֣�");
				return;
			}else if(!($(this).val().length==16)){
				flag=false;
				JEND.page.alert("�Ŷ�"+$(this).val()+"���ȱ�����16λ��");
				return;
			}
		});
		if(flag){
			var start = new Array();
			var end = new Array();
			$('input[name^="cardStageStart"]').each(function(index,val){
				 start.push($(this).val());
			});
			$('input[name^="cardStageEnd"]').each(function(index,val){
				 end.push($(this).val());
			});
			var cardTypeName = $.trim($("#carType").val());
			JEND.page.confirm('<div align="left"><h3>ȷ��Ҫ������</h3></div>',function(){
				JEND.util.dialog.showLoading({loadingText:'���ڼ������ݣ����Ժ�'});
				$.ajax({
					url:'${pagecontext.request.getcontextpath}/**WalletCpWeb/provinceTubeCoupon/personalityCardJson/editPersonalityCard.action',
					data:{"cardStageStart":start,"cardStageEnd":end,"bingdingType":"2","cardTypeName":cardTypeName},
					type:'POST',
					success:function(data){
						var obj = eval('(' + data + ')'); 
						if(obj.flag=="T"){
							JEND.page.alert("����ɹ���");
							setTimeout("personalityCardPage()",1000);
						}else{
							JEND.page.alert(obj.msg);
							var arr = obj.errorMsg;
							if(arr!=null){
								addInfo(arr);
							}
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						JEND.page.alert("�ܱ�Ǹ�����緱æ�����Ժ����ԣ�");
					}
				});
			});
		}
	}
	//����������
	function submitAddBtn(){
		 var carType = $.trim($("#carType").val());
		 var pattern=/^[\u4E00-\uFA29a-zA-Z0-9]*$/;
		if(carType=="�����������Ŀ�������"||carType==""){
			JEND.page.alert("�����������Ŀ���������");
			return;
		}
		//��������ֻ�����뺺�֡��ַ�������
		if(!pattern.test(carType)){
			JEND.page.alert("���������Ʋ����������ַ���");
			return;
		}
		if(carType.length>51){
			JEND.page.alert("������������������50���ַ���");
			return;
		}
		if($("#cardInput2").attr("checked")){//ѡ���ǺŶ�
			//�Ŷ�У�鲢�ύ
			checkCardStageAndSubmit();
		}else{
			//**У�鲢�ύ
		    checkCardCodeLength("cardCode2");
		}
	}
	//ƴ����ʾ��Ϣ
	function addInfo(msgArr){
		//�������errorMsg
		$('ul[name^="errorMsg"]').remove();
		var htmlHead=
    '<ul class="colunm" name="errorMsg">'+
	        '<li class="mr30">'+
             '<label class="label">'+
             '<span style="color:red">';
	var  htmlAfter ='</span>'+
             '</label>'+
	        '</li>'+
       '</ul>';
       var htmlContent='';
		for(var i=0;i<msgArr.length;i++){
			htmlContent=htmlContent+msgArr[i]+'</br>';
		}
		$("#contentId").append(htmlHead+htmlContent+htmlAfter);
	}
</script>

<style type="css/text">
.label{width: 120px;}
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
			<div class="crumbs">
			    <li><a target="_parent" href="javascript:void(0)">*********</a><span class="direct">&gt;</span></li>
				<li>********<span class="direct">&gt;</span></li>
				<li>********</li>
			</div>
			<div class="mainContent">
			<s:form id="searchForm" method="post" name="searchForm">
              <div class="Dgw mb10" id="contentId">
	                <ul class="colunm">
				       <li class="mr30">
			              <label class="label">********��
			                <input type="text" id="carType"   name="cardTypeName" class="s_textbox"  value="******** "/>
			               <span style="color:red"> *********</span>
			              </label>
				        </li>
	                </ul>
	                <ul class="colunm">
				       <li class="mr30">
			              <label class="label">
			                <input type="radio" id="cardInput1" name="bingdingType"  onclick="toShowCardCode()" maxlength="13"/>**********
			              </label>
				        </li>
				         <li class="mr30">
			              <label class="label">
			                <input type="radio" id="cardInput2"  name="bingdingType" class="s_textbox"   checked="checked" onclick="toShowCardStage()" maxlength="13"/>**������(�ʺ�����**)
			              </label>
				        </li>
	                </ul>
	                <ul class="colunm" id="cardCodeUl">
				       <li class="mr30">
			              <label class="label">********��
			                <input type="text" id="cardCode2"  name="cardCodes" class="s_textbox"  style="width:350px" />
			              </label>
				        </li>
	                </ul>
	                <ul class="colunm" id="cardCodeText">
				        <li class="mr30">
			              <label class="label">
			               <span>
			                                                                ********��</br>
			                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1��********</br>
						           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2��********
						</span>
			              </label>
				        </li>
	                </ul>
	                <ul class="colunm" id="cardStageUl">
			           <ul class="colunm" name="cardStageUlSub">
			            <li class="mr30">
			              <label class="label">********��
			                <input type="text" id="cardStageStartId"  name="cardStageStart" class="s_textbox"  maxlength="16"/>
			                &nbsp;&nbsp;��������&nbsp;&nbsp;
			                <input type="text" id="cardStageEndId"  name="cardStageEnd" class="s_textbox"  maxlength="16"/>
			                 &nbsp;&nbsp;&nbsp;&nbsp;
			                 <input type="button" value=" ******** " onclick="deleteElement(this)"/>
			              </label>
				        </li>
				       </ul>
	                </ul>
	                <ul class="colunm" id="cardStageBtn">
				        <li class="mr30">
			              <label class="label">
			              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			               <input type="button" value=" ����******** " id="newAdd"/>
			              </label>
				        </li>
	                </ul>
	                <br></br>
	                <ul class="colunm">
				        <li class="mr30">
				          <label class="label">
				            <input type="button" class="btn Borange fw7 mb20" value="********  "  onclick="personalityCardPage()"/>
			              </label>
				        </li>
				        <li class="mr30">
				          <label class="label">
			                <input type="button"  id="saveBtn" class="btn Borange fw7 mb20" value="******** "  onclick="submitAddBtn()"/>
			              </label>
				        </li>
	                </ul>
              </div>
              </s:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>