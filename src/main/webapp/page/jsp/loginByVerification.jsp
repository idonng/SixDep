﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="no-js">
<head>
<title>事业六部积分制平台</title>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<link href="../css/Mobilelogin.css" type="text/css" rel="stylesheet">
<script src="../js/jquery.min.js"></script>
<script src="../js/loginByVerification.js"></script>
</head>
<body>
	<img style="padding:30% 0 20% 7% " width=90% height=10%
		src="../images/limages/tubiao.png">
	<form method="post">
		<ul>
			<li><i class="un"><img src="../images/limages/user_name.png">
			</i><input class="username" id="phone" type="text" placeholder="请输入手机号" />
			</li>
			<li><i class="yz"><img src="../images/limages/msg.png">
			</i><input class="yzm" id="check" type="text" placeholder="请输入验证码" /><input
				type="button" id="send" onclick="checkNum();" value="获取验证码" /></li>
		</ul>
		<div class="reg_btn">
			<input class="submit" onclick="ti()" id="login" type="button"
				value="登录" />
		</div>
	</form>
	<div
		style="text-align:center;padding-top: 35%;font-weight: normal !important; ">
		<font color="#ccc" size="-1">Copyright © 2017 步长制药-大数据中心</font>
	</div>
</body>
</html>
<script type="text/javascript">
	var aInput = document.getElementsByTagName('input');
	var oName = aInput[0];
	var dx = aInput[1];

	var aP = document.getElementsByTagName('p');
	var name_msg = aP[0];
	var dx_msg = aP[0];
	var name_length = 0;
	var send = document.getElementById('send'),

	times = 60, timer = null;
	function djs() {
		send.value = times + "秒后重试";
		send.setAttribute('disabled', 'disabled');
		send.style.background = '#ccc';
		send.style.border = '1px solid #ccc';
		times--;
		if (times <= 0) {
			send.style.border = '1px solid #79b235';
			send.style.background = '#79b235';
			send.value = "发送验证码";
			send.removeAttribute('disabled');
			times = 60;
			clearInterval(timer);
		}
	}
</script>


