jQuery.validator.addMethod("username", function(value, element) {
		var userName = /^.{3,20}$/;
		return this.optional(element) || (userName.test(value));
}, '长度为3-20的所有字符');
	jQuery.validator.addMethod("tele", function(value, element) {
		var mobile = /^1[3|4|5|7|8][0-9]{9}$/;
		return this.optional(element) || (mobile.test(value));
}, '请输入正确的手机号码');
	jQuery.validator.addMethod("pwd", function(value, element) {
		var passWord = /^[a-zA-Z][\w]{4,16}$/;
		return this.optional(element) || (passWord.test(value));
}, '以字母开头，5-17位字母、数字、下划线');
	jQuery.validator.addMethod("code", function(value, element) {
		var inputCode = code;
		return inputCode == value;
	}, '输入正确的验证码');