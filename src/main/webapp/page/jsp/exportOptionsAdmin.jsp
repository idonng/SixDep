<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}/page"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<title>事业六部积分填报导出</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx}/css/fullcalendar.css" />
<link rel="stylesheet" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>
<script src="${ctx}/js/jquery.min.js"></script>
<style type="text/css">
table {
	border-collapse: collapse
}

tr {
	border: 1px solid black
}

td {
	border-right: 1px solid black;
}
form{
	 width: 100%;
    margin-left: 5%;
        margin-top: 2%;
}
select {
	vertical-align: baseline;
}
span {
	padding: 10px;
}
#myExcelHtml{
width:90%;
margin-left: 5%;

}
</style>
</head>
<body>
	<div style="text-align:center;">
		<h1></h1>
	</div>
	
		<form name="form1" method="post" action="${ctx}/exportExcel.do"
				onsubmit="return exportExcel();">
			<span>日期：</span>
			<select name=YYYY id='Year' onchange="changeYear(this.value)">
				<option value="">请选择 年</option>
			</select> <select name=MM id='Month' onchange="changeMonth(this.value)">
				<option value="">选择 月</option>
			</select><select name=DD id='Day' style='display:none'>
			<option value="">选择 日</option>
		</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<span>职位：</span>
			 <select id='depTypes' onchange="showUsers(this.value)">
			 	<option value="">---请选择---</option>
			 </select>
			 <span>姓名：</span>
			 <select id='userInfos'>
			 	<option value="">---请选择---</option>
			 </select>
			 
			 <input type="hidden" id="userId" name="userId" value="" /> 
			 <input type="hidden" id="month" name="month" value="" />
			 <button type="button" class="btn btn-success modify" onclick="showExcel();" style='margin-left: 3%;'>查询</button>
			 <button class="btn btn-success  modify" type="submit" style='margin-left: 1%;'>导出</button>
		</form>
		
		<%-- <div align="center" style='float: left; margin-left: 1%;'>
			<span style='font-size: 14px;'>职位：</span> <select id='depTypes'
				onchange="showUsers(this.value)">
				<option value="">---请选择---</option>
			</select> <span style=' font-size: 14px;    padding-left: 10px;'>姓名：</span> <select id='userInfos'>
				<option value="">---请选择---</option>
			</select>

			<form method="post" action="${ctx}/exportExcel.do"
				onsubmit="return exportExcel();">
				<input type="hidden" id="userId" name="userId" value="" /> <input
					type="hidden" id="month" name="month" value="" />
				<button type="button" class="btn btn-success  modify"
					style=' width: 5%; margin-right: 7%;' onclick="showExcel();">查询</button>
				<button class="btn btn-success  modify" style=' width: 5%;'
					type="submit">导出</button>
			</form>
		</div> --%>
	
	<hr style="border:1px solid #53657c; " />
	<div id="myExcelHtml"></div>
</body>
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/jquery.ui.custom.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script> 
<script type="text/javascript">
	showDepTypes();
	function showDepTypes() {
		var urlPath = "${ctx}/selectDepType.do";
		$.ajax({
			type : "POST",
			url : urlPath,
			dataType : "json",
			success : function(data) {
				var jsonObj = eval("(" + data + ")");
				for (var i = 0; i < jsonObj.length; i++) {
					$("#depTypes").append(
							"<option value="+ jsonObj[i].id+">"
									+ jsonObj[i].name + "</option>");
				}
			}
		});
	}
	function showUsers(id) {
		var depTypeId = id;
		if (depTypeId == "") {
			$("#userInfos").empty();
			$("#userInfos").append("<option value=''>---请选择---</option>");
		}
		var urlPath = "${ctx}/selectUsersByDepType.do";
		$.ajax({
			type : "POST",
			url : urlPath,
			data : {
				'depTypeId' : depTypeId
			},
			dataType : "json",
			success : function(data) {
				$("#userInfos").empty();
				$("#userInfos").append("<option value=''>---请选择---</option>");
				var jsonObj = eval("(" + data + ")");
				for (var i = 0; i < jsonObj.length; i++) {
					$("#userInfos").append(
							"<option value="+ jsonObj[i].id+">"
									+ jsonObj[i].name + "</option>");
				}
			}
		});
	}
	function exportExcel() {
		var userId = $("#userInfos").val();
		if (userId == null || userId.length == 0) {
			alert("请选择员工信息!");
			return;
		}
		if (userId.length == 0) {
			alert("请选择员工信息!");
			return;
		}
		if ($("#Year").val() == "" || $("#Month").val() == "") {
			alert("请选择月份信息!");
			return;
		}
		var month = $("#Year").val() + "-" + $("#Month").val();
		//var month = "2017-02";
		$("#userId").val(userId);
		$("#month").val(month);
		return true;
	}
	function showExcel() {
		var userId = $("#userInfos").val();
		if (userId == null || userId.length == 0) {
			alert("请选择员工信息!");
			return;
		}
		//var month = "2017-02";
		if ($("#Year").val() == "" || $("#Month").val() == "") {
			alert("请选择月份信息!");
			return;
		}
		var month = $("#Year").val() + "-" + $("#Month").val();
		var urlPath = "${ctx}/showExcel.do";
		$.ajax({
			type : "POST",
			url : urlPath,
			data : {
				'userId' : userId,
				'month' : month
			},
			dataType : "json",
			async : false,
			success : function(data) {
				$("#myExcelHtml").empty();
				$("#myExcelHtml").append(data);
			}
		});
		initBackground();
	}
	function initBackground() {
		$("tr:even").css("background", "white");
		$("tr:odd").css("background", "LightGray");
	}
</script>
<script type="text/javascript">
	function YYYYMMDDinit() {
		MonHead = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

		//先给年下拉框赋内容 
		var y = new Date().getFullYear();
		for (var i = (y - 30); i < (y + 30); i++)
			//以今年为准，前30年，后30年 
			document.form1.YYYY.options.add(new Option(" " + i + " 年", i));

		//赋月份的下拉框 
		for (var i = 1; i < 13; i++)
			document.form1.MM.options.add(new Option(" " + i + " 月", i));

		document.form1.YYYY.value = y;
		document.form1.MM.value = new Date().getMonth() + 1;
		var n = MonHead[new Date().getMonth()];
		if (new Date().getMonth() == 1 && IsPinYear(document.form1.YYYY.value))
			n++;
		writeDay(n); //赋日期下拉框Author:meizz 
		document.form1.DD.value = new Date().getDate();
	}
	if (document.attachEvent)
		window.attachEvent("onload", YYYYMMDDinit);
	else
		window.addEventListener('load', YYYYMMDDinit, false);
	function changeYear(str) //年发生变化时日期发生变化(主要是判断闰平年) 
	{
		var MMvalue = document.form1.MM.options[document.form1.MM.selectedIndex].value;
		if (MMvalue == "") {
			var e = document.form1.DD;
			optionsClear(e);
			return;
		}
		var n = MonHead[MMvalue - 1];
		if (MMvalue == 2 && IsPinYear(str))
			n++;
		writeDay(n)
	}
	function changeMonth(str) //月发生变化时日期联动 
	{
		var YYYYvalue = document.form1.YYYY.options[document.form1.YYYY.selectedIndex].value;
		if (YYYYvalue == "") {
			var e = document.form1.DD;
			optionsClear(e);
			return;
		}
		var n = MonHead[str - 1];
		if (str == 2 && IsPinYear(YYYYvalue))
			n++;
		writeDay(n)
	}
	function writeDay(n) //据条件写日期的下拉框 
	{
		var e = document.form1.DD;
		optionsClear(e);
		for (var i = 1; i < (n + 1); i++)
			e.options.add(new Option(" " + i + " 日", i));
	}
	function IsPinYear(year)//判断是否闰平年 
	{
		return (0 == year % 4 && (year % 100 != 0 || year % 400 == 0));
	}
	function optionsClear(e) {
		e.options.length = 1;
	}
</script>

</html>
