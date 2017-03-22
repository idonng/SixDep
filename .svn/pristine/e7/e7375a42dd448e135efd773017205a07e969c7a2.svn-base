<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1">
<meta name="description" content="" />
<meta name="author" content="" />
<title>事业六部积分填报</title>
<link href="../css/showOption.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/ligerui-dialog.css" />
<script src="../js/jquery.min.js"></script>
<script src="../js/ShowOptions.js"></script>
<script type="text/javascript" src="../js/ligerui.all.js"></script>
</head>
<body>
	<div style="height:5%;color:#fff;text-align:center;">
		<h1>事业六部积分填报</h1>
	</div>
	<div style="height:3%;margin-top:-7%;color:#999;text-align:center;">
		<h4>${sessionScope.user.realTime}</h4>
	</div>
	<div
		style="padding:0% 0 5% 0;color:#f34541;font-weight:bold;font-size:15px;width:100%;text-align:center;">
		${sessionScope.user.deptname}-${sessionScope.user.name},欢迎您！</div>
	<hr style="border:1px solid #53657c;margin-top:-2%" />
	<span style="color:#fff;">日期：</span>
	<input type="Date" id="realTime" value="" />
	<input type="button" value="查询" onclick="selectByDate();">
	<hr style="border:1px solid #53657c;" />
	<div style="margin-top:2%;color:#fff;font-weight:bold;">一、积分填报：</div>

	<section>
		<div id="options"></div>
		<hr style="border:1px solid #53657c;" />
		<div style="margin-top:2%;color:#fff;font-weight:bold;">二、活动图片秀：</div>
		<div id="imgShow"
			style="margin-left:5%;margin-top: 2%;color:#fff;text-align: center;"></div>
		<hr style="border:1px solid #53657c;" />
		<div>
			<label onclick="addImg()"><img
				src="../images/limages/add.png" alt="点击上传图片"
				style="margin-left:5%;width:25px;" /><span style="color:#fff;">点击上传图片</span>
			</label> <label onclick="reduceImg()"><img
				src="../images/limages/jian.png" alt="取消图片"
				style="margin-left:15%;width:30px;" /><span style="color:#fff;">点击取消图片</span>
			</label>
		</div>
		<form action="" method="post" name="form1"
			enctype="multipart/form-data">
			<div id="imgInfo"
				style="margin-left:5%;margin-top: 2%;color:#fff;text-align: center;">
				<input type="hidden" name="qualityinfoid" id="qualityinfoid"
					value="" />
			</div>
		<form>
		<div align="center">
			<input type="button" value="填报" class="button" onclick="add();">
		</div>
		<input type="hidden" id="userInfos" value="${sessionScope.user.id}" />
	</section>
	<div style="text-align:center;margin:10% 0;">
		<font color="white" size="-1">Copyright © 2017 步长制药-大数据中心</font> <br />
	</div>
</body>
</html>

