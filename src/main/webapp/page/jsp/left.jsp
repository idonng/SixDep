<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}/page"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>left page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx}/css/fullcalendar.css" />
<link rel="stylesheet" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/css/media.css" />
<link href="${ctx}/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>
<link href="${ctx}/libs/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/libs/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx}/libs/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<style>
.reportDate {
	display: none
}

.detail {
	margin-left: 25%;
	padding: 5px !important;
}

input[type="Date"]{
	background: rgba(45, 45, 45, .15);
	border-radius: 10px;
	width: 120px;
	height: 20px;
	font-size:10px;
	color:#ffffff;
	text-align: center;
	margin: 0 10%
}
</style>
<script>
	$(function() {
		$("ul li").click(function() {
			$(this).addClass("active").siblings().removeClass("active");
			
		});
		//导航栏三级菜单光标设置
/* 		$(".report").click(function() {
			$(".report").removeClass("active");
			$(".reportDate").css("display", "none");
			$(this).next().slideToggle("fast");
		}); */
	});
</script>

</head>

<body>
	<!--sidebar-menu-->
	<div id="sidebar">
		<ul>
			<li class="active"><a href="${ctx}/jsp/main.jsp"
				target="framemain"><i class="icon icon-home"></i> <span>首页</span>
			</a>
			</li>
			<li><a href="${ctx}/jsp/option.jsp" target="framemain"><i
					class="icon icon-th"></i> <span>选项信息管理</span> </a></li>
			
			<li class="submenu"><a href="#"><i
					class="icon icon-th"></i> <span>报表信息管理</span> </a>
				<ul>
					<li class="report" ><a href="${ctx}/jsp/showOptionsAdmin.jsp"  target="framemain">编辑报表信息</a></li>
					<li class="report"><a href="${ctx}/jsp/exportOptionsAdmin.jsp"  target="framemain">查看报表信息</a></li>
			</ul>
			</li>
				
			<li class="submenu"><a href="#"><i class="icon icon-th"></i> <span>人员信息管理</span> </a>
				<ul>
					<li class="report"><a href="${ctx}/jsp/user.jsp" target="framemain">员工信息管理</a></li>
					<li class="report"><a href="${ctx}/jsp/pwd.jsp" target="framemain">个人密码设置</a></li>
			</ul></li>
		</ul>
	</div>
	<!--sidebar-menu-->
	<script src="${ctx}/js/excanvas.min.js"></script>
	<script src="${ctx}/js/jquery.ui.custom.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<script src="${ctx}/js/jquery.flot.min.js"></script>
	<script src="${ctx}/js/jquery.flot.resize.min.js"></script>
	<script src="${ctx}/js/jquery.peity.min.js"></script>
	<script src="${ctx}/js/fullcalendar.min.js"></script>
	<script src="${ctx}/js/matrix.js"></script>
	<script src="${ctx}/js/dashboard.js"></script>
	<script src="${ctx}/js/jquery.gritter.min.js"></script>
	<script src="${ctx}/js/interface.js"></script>
	<script src="${ctx}/js/chat.js"></script>
	<script src="${ctx}/js/jquery.validate.js"></script>
	<script src="${ctx}/js/form_validation.js"></script>
	<script src="${ctx}/js/jquery.wizard.js"></script>
	<script src="${ctx}/js/jquery.uniform.js"></script>
	<script src="${ctx}/js/select2.min.js"></script>
	<script src="${ctx}/js/popover.js"></script>
	<script src="${ctx}/js/jquery.dataTables.min.js"></script>
	<script src="${ctx}/js/tables.js"></script>

</body>
</html>