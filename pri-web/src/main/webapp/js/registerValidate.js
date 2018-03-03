
$(function() {
	// jQuery Validate表单验证
	/**
	 * 添加验证方法: ok 以字母开头，5-17 位字母、数字、下划线"_"
	 * 长度为3-20的所有字符
	 */
	jQuery.validator.addMethod("ok", function(value, element) {
	//	var userName = /^[a-zA-Z][\w]{4,16}$/;
		var userName = /^.{3,20}$/;
		return this.optional(element) || (userName.test(value));
}, '长度为3-20的所有字符');
	jQuery.validator.addMethod("ok1", function(value, element) {
		var mobile = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
		return this.optional(element) || (mobile.test(value));
	}, '正确的手机号码');
	jQuery.validator.addMethod("ok2", function(value, element) {
		var passWord = /^[a-zA-Z][\w]{4,16}$/;
		return this.optional(element) || (passWord.test(value));
	}, '以字母开头，5-17 位字母、数字、下划线"_"');
$('#regForm').validate({
	debug : false, //false表示验证通过后不要自动提交表单
	onkeyup : false, //表示关闭按键松开时候监听验证
				// 验证规则
				rules : {
					userName : { // form表单中input[name='userName']元素的验证规则
						required : true, // 必须字段，不能为空
						ok : true // 启用自定义验证规则'ok'
						/*remote : { // 异步验证
							url : checkAccountUrl, // 后台处理程序
							type : "post", // 数据发送方式
							dataType : "json", // 接受数据格式
							data : { // 要传递的数据
								account : function() {
									return$("#account").val().trim();
								}
							}
						}*/
					},
					passWord : {
						required : true,
						ok2 : true,
					},
					passWord1 : {
						required : true,
						equalTo : '#passWord'
					},
					email : {
						required : true,
						email: true
						/*remote : {
							url : checkUnameUrl,
							type : "post",
							dataType : "json",
							data : {
								uname : function() {
									return$("#uname").val().trim();
								}
							}
						}*/
					},
					mobile : {
						required : true,
						ok1 : true 
						/*remote : {
							url : checkVerifyUrl,
							type : 'post',
							dataType : 'json',
							data : {
								verify : function() {
									return$('#verify').val().trim();
								}
							}
						}*/
					}
				},
				// 提示消息
				messages : {
					userName : { // form表单中input[name='userName']元素的提示信息
						required : '账号不能为空', // 输入为空时提示
						/*remote : '账号已存在'// 异步获得json数据为'false'时提示，也就是后台验证失败*/					},
					passWord : {
						required : '密码不能为空',
					},
					passWord1 : {
						required : '请确认密码',
						equalTo : '两次密码不一致'
					},
					email : {
						required : '请填写您的邮箱',
					},
					mobile : {
						required : '请输入手机号'
					}
				},
				/*// 错误标签元素
				errorElement : 'span',
				// 错误提示位置
				errorPlacement : function(error, element) { // error是错误信息的提示元素，element是当前input域
					error.appendTo(element.parent()).prev('.success').remove(); // 追加提示元素，去掉之前的成功元素
				},
				// 验证通过
				success : function(span) { // span 是错误的提示元素
					span.removeClass('error').addClass('success').siblings(
							'.success').remove(); // 追加成功样式，去掉错误样式和重复的成功样式
				}*/
			});
})