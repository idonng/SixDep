// 发送验证码
function checkNum() {
	var phone = document.getElementById("phone").value;
	var re = /^1([38]\d|4[57]|5[0-35-9]|7[06-8]|8[89])\d{8}$/;
	if (phone == "请输入手机号码" || phone == "") {
		alert('请输入手机号码！');
	} else if (!re.test(phone)) {
		alert('手机号码有误，请重新输入！');
	} else {
		$.ajax({
			type : "POST",
			url : "../../MobileLoginController/GetVerification.do",
			data : "phone=" + phone,
			success : function(data) {
				msg = eval("(" + data + ")");
				if (msg.success){
					alert('已发送,请注意查看验证码！');
					// 计时开始
					timer = setInterval(djs, 1000);
				}else{
					alert(msg.message);
				}
			}
		});
	}
}

// 提交登录
function ti() {

	var phone = document.getElementById("phone").value;
	var check = document.getElementById("check").value;
	if (phone == null || phone == "" || phone == "请输入手机号") {
		alert("对不起，手机号不能为空！");
	} else if (check == null || check == "" || check == "请输入验证码") {
		alert("对不起，验证码不能为空！");
	} else {
		$.ajax({
			type : "POST",
			url : "../../MobileLoginController/UserLogin.do",
			data : "phone=" + phone + "&check=" + check,
			success : function(data) {
				msg = eval("(" + data + ")");
				if (msg.success){
					window.location.href = 'ShowOptions.jsp';
				}else{
					alert(msg.message);
				}
			}
		});
	}
}