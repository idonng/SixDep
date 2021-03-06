<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}/page"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<title>事业六部积分填报编辑</title>
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
<!--图片弹框 -->
<link rel="stylesheet" href="${ctx}/css/jquery.lightbox-0.5.css">
<script src="${ctx}/js/jquery.lightbox-0.5.js"></script>
<style type="text/css">
table {
	border-collapse: collapse;
	width: 100%;
}

tr {
	border: 1px solid black;
}

form {
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

#opSection {
	display: none;
}
</style>
</head>
<body style='height: 1308px;'>
	<div style="text-align:center;">
		<h1></h1>
	</div>
	<form id='data' name='form1' method='post'
		onsubmit="return exportExcel();">
		<span>日期：</span> <select name=YYYY id='Year'
			onchange="changeYear(this.value)">
			<option value="">请选择 年</option>
		</select> <select name=MM id='Month' onchange="changeMonth(this.value)">
			<option value="">选择 月</option>
		</select> <select name=DD id='Day'>
			<option value="">选择 日</option>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		<span>职位：</span> <select id='depTypes'
			onchange="showUsers(this.value)">
			<option value="">---请选择---</option>
		</select> <span>姓名：</span> <select id='userInfos'>
			<option value="">---请选择---</option>
		</select> <input type="hidden" id="userId" name="userId" value="" /> <input
			type="hidden" id="month" name="month" value="" />
		<button type="button" class="btn btn-success modify"
			onclick="showOptions();" style='margin-left: 3%;'>查询</button>
	</form>

	<div>
		<hr style="border:1px solid #53657c; " />
	</div>
	<form id="subData" method='post' onsubmit="return add();"
		enctype="multipart/form-data">
		<section id='opSection' style='width: 70%; margin-left: 10%;'>
			<table class='option table table-bordered data-table'>
				<td> <span
					style="color:#17489d;font-size:10px;font-weight:none;">积分项-分值-数量</span>
				</td>
			</table>
			<div id="options" style=""></div>
			<div id="imgInfo"	style="margin-left: 40px;font-size: 16px;width:100%;min-width:1100px;min-height:80%;">
			</div>
			<input type="hidden" name="foruserid" id="foruserid" value="" /> <input
				type="hidden" name="realTime" id="realTime" value="" />
			<div id="imgUpload">
				<label onclick="addImg()"><img alt="点击上传图片"
					src="../images/limages/add.png"> <span>点击上传图片</span> </label> <label
					onclick="reduceImg()"><img alt="点击取消图片"
					src="../images/limages/jian.png"> <span>点击取消图片</span> </label> <img
					id="preview" width=-1 height=-1 style="diplay:none" />
			</div>
			<button type="submit" align="right" class="btn btn-success  modify"
				style=' float: right;'>提交</button>
			<br /> <br /> <br /> <input type="hidden" id="init" value="" />
		</section>
	</form>

</body>

<script src="${ctx}/js/jquery.ui.custom.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>

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
	function initBackground() {
		$("tr:even").css("background", "PaleTurquoise1");
		$("tr:odd").css("background", "LightGray");
	}
</script>
<script type="text/javascript">
	var depTypeId ;
	var realTime ;
	var foruserid ;
	var imgNum = 0;
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return (r[2]);
		return null;
	}

	function init() {
		foruserid = GetQueryString("foruserid");
		depTypeId = GetQueryString("depTypeId");
		realTime = GetQueryString("realTime");
		if (depTypeId != null && realTime != null && foruserid != null) {
			$("#Year").val(realTime.split("-")[0]);
			$("#Month").val(realTime.split("-")[1]);
			$("#Day").val(realTime.split("-")[2]);
			//	showOptions();
			return true;
		} else {
			return false;
		}
	}
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
				if (init()) {
					$("#depTypes").val(depTypeId);
					showUsers(depTypeId);
				}
			}
		});

	}
	function showUsers(id) {
		depTypeId = id;
		if (depTypeId == '') {
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
				if (init()) {
					$("#userInfos").val(foruserid);
					showOptions();
				}
			}
		});

	}
	function showOptions() {
		depTypeId = $("#depTypes").val();
		realTime = $("#Year").val() + "-" + $("#Month").val() + "-"
				+ $("#Day").val();
		foruserid = $("#userInfos").val();
		var userId = $("#userInfos").val();

		if (depTypeId==null||depTypeId.length==0||userId == null || userId.length == 0) {
			alert("请选择员工信息!");
			return;
		}
		if ($("#Year").val() == "" || $("#Month").val() == ""
				|| $("#Day").val() == "") {
			alert("请选择日期信息!");
			return;
		}
		document.getElementById('opSection').style.display = 'block';
		var date = $("#Year").val() + "-" + $("#Month").val() + "-"
				+ $("#Day").val();

		var urlPath = "${ctx}/queryOptionsScoreByOption.do";
		var optionObj;
		$
				.ajax({
					type : "POST",
					url : urlPath,
					data : {
						'userId' : userId,
						'date' : date
					},
					dataType : "json",
					async : false,
					success : function(msg) {
						var optionsHtml = '';
						var imgsHtml = '';
						$("#options").empty();
						$("#imgInfo").empty();
						var jsondata = eval(msg);
						optionObj = eval("[" + jsondata[0] + "]");
						picObj = eval("[" + jsondata[1] + "]");
						for (var i = 0; i < optionObj.length; i++) {
							var j = i + 1;
							var json = eval(optionObj[i]);
							optionsHtml += "<table class='option table table-bordered data-table'><tr>";
							if (json.lastDayOfMonth != 1 && json.frequency == 2) {
								optionsHtml += "<td align='left' style='width:95%'> "
										+ (j)
										+ "、"
										+ json.name
										+ " - "
										+ json.score + json.unit + "</td>";
								optionsHtml += "<td align='right' style='width:5%'>"
										+ "<select  class='optionselect'  name="+j+" id='"
										+ json.id + "' value="
										+ json.sumNums
										+" disabled='true'><option value='0'>0</option><option value='1'>1</option> <option value='2'>2</option> <option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>"
								optionsHtml += "</td></tr></table>";
							} else {
								optionsHtml += "<td align='left' style='width:95%'> "
										+ (j)
										+ "、"
										+ json.name
										+ " - "
										+ json.score + json.unit + "</td>";
								optionsHtml += "<td align='right' style='width:5%'>"
										+ "<select  class='optionselect'  name="+j+" id='"
										+ json.id + "' value="
										+ json.sumNums
										+"><option value='0'>0</option><option value='1'>1</option> <option value='2'>2</option> <option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>"
								optionsHtml += "</td></tr></table>";

							}
						}
						$("#options").append(optionsHtml);

						for (var i = 0; i < picObj.length; i++) {
							var json = eval(picObj[i]);
							imgsHtml += "<a href=../../"+json.imgpath+">"
							imgsHtml += "<img  calss='images'  style='cursor:pointer; width:200px ' src=../../"+json.imgpath+">";
							imgsHtml +="</a>";
							imgsHtml += "<img id="
									+ json.imgid
									+ " src='../img/del.png'  onClick='delImg(this.id)' >";
							
						}
						$("#imgInfo").append(imgsHtml);
  						$('#imgInfo a').lightBox();
					}
				});

		for (var i = 0; i < optionObj.length; i++) {
			var json = eval(optionObj[i]);
			$("#" + json.id).val(json.sumNums);
		}
		initBackground();
	}

	// 积分填报
	function add() {
		var optionsIds = "";
		var nums = "";
		foruserid = $("#userInfos").val();
		depTypeId = $("#depTypes").val();
		var checkt = /^\d$/;
		var optionselects = document.getElementsByClassName("optionselect");
		for (var i = 0; i < optionselects.length; i++) {
			optionsIds += optionselects[i].id + "##";
			nums += optionselects[i].value + "##";
		}
		realTime = $("#Year").val() + "-" + $("#Month").val() + "-"
				+ $("#Day").val();

		var urlPath = "${ctx}/insertOrUpdateOptions.do";
		$
				.ajax({
					type : 'POST',
					url : urlPath,
					async : false,
					data : {
						'realTime' : realTime,
						'foruserid' : foruserid,
						'optionsIds' : optionsIds,
						'nums' : nums
					},
					dataType : 'json',
					success : function(msg) {
						if (msg = '1') {
							if (imgNum > 0) {
								for (var i = 1; i <= imgNum; i++) {
									if ($('#file' + i).val() == 0) {
										alert("请上传第(" + i+ ")条图片!" );
										return;
									}
								}
								$("#foruserid").val(foruserid);
								$("#realTime").val(realTime);
								var url = "${ctx}/uploadImage.do";
								//$("#subData").attr("action", url);
								$("#subData")
										.ajaxSubmit(
												{
													dataType : "json",
													url : url,
													//async : false,
													success : function(result) {
														var json1 = eval(result);
														if (json1.success) {
															//全部上传完毕
															alert("提交成功");
															window.location.href = "${ctx}/jsp/showOptionsAdmin.jsp?foruserid="
																	+ foruserid
																	+ "&realTime="
																	+ realTime
																	+ "&depTypeId="
																	+ depTypeId;
														} else {
															alert(json1.message);
															window.location.href = "${ctx}/showOptionsAdmin.jsp?foruserid="
																	+ foruserid
																	+ "&realTime="
																	+ realTime
																	+ "&depTypeId="
																	+ depTypeId;
														}
													}

												});
							} else {
								alert("提交成功");
							}

							//  return false;  
						}
					}
				});
		return false;
	}

	//上传图片
	function addImg() {
		if (imgNum != 0 && $('#file' + imgNum).val() == "") {
			alert('请上传(图片' + imgNum + ')后再添加！');
		} else {
			imgNum = imgNum + 1;
			var html = "<div   id=\'div"+imgNum+"\'>"
					+ "图片"
					+ imgNum
					+ "：<input type=\'file\' name=\'files\' id=\'file"+imgNum+"\' /><br />"
					+ "</div>";
			$('#imgUpload').append(html);
		}
	}
	//删除图片
	function reduceImg() {

		if (imgNum == 0) {
			alert('对不起，目前没有可以删除的图片信息！');
		} else {
			var f = document.getElementById('div' + imgNum);
			var p = f.parentNode;
			p.removeChild(f);
			imgNum = imgNum - 1;
		}
	}
	//删除图片
	function delImg(id) {
		var urlPath = "${ctx}/deleteImage.do";
		$
				.ajax({
					type : "POST",
					url : urlPath,
					//async : false,
					data : {
						'imgId' : id
					},
					dataType : "json",
					success : function(data) {
						alert("删除成功");
						window.location.href = "${ctx}/jsp/showOptionsAdmin.jsp?foruserid="
								+ foruserid
								+ "&realTime="
								+ realTime
								+ "&depTypeId=" + depTypeId;
					}
				});
	}
</script>
</html>
