<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}/page"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>个人密码设置 | sixDepScore</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx}/css/fullcalendar.css" />
<link rel="stylesheet" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/css/media.css" />
<link href="${ctx}/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>
<style type="text/css">
	body{ font-family: helvetica, "微软雅黑"; font-size:12px; color:#666;}
	h1{padding: 20px;}
#content {
	width: 100%;
	float: right;
}

html,body {
	min-height: 100% !important;
}

.modify {
	width: 20% !important;
	margin: auto !important;
}
</style>
</head>
<body>
<!--main-container-part-->
<div id="content">
		<!--theme-table-->

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span6">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-key"></i> </span>
							<h5>修改密码</h5>
						</div>
						<div class="widget-content nopadding">
							<form action="#" method="get" class="form-horizontal">
								<div class="control-group">
									<label class="control-label">姓名 :</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="请输入您的用户名"
											required="required" value="${user.name}" readonly="ture"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">原始密码 :</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="请输入您的原始密码"
											required="required" id="userpwd" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">新密码 :</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="请输入您的新密码"
											required="required" id="pwd" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">确认密码 :</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="请再次输入您的密码"
											required="required" id="rpwd" />
									</div>
								</div>
								<div class="form-actions">
								<!-- <label id="warn" align="center" style="padding-bottom:0.5cm;"></label> -->
									<button type="submit" class="btn btn-success btn-block modify"
										onclick="updatepwd();">修改</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--Footer-part-->
	<div class="row-fluid">
		  <div id="footer" class="span12"> 2017 &copy; 步长制药. Copyright 电商部  </div>
	</div>

	<!--end-Footer-part-->

	<script src="${ctx}/js/jquery.min.js"></script>
	<script src="${ctx}/js/jquery.ui.custom.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<script src="${ctx}/js/calendar.js"></script>
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

	<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}

</script>


	<script type="text/javascript">
		function updatepwd() {
			var userid = "${user.id}";
			var userpwd = document.getElementById('userpwd').value;
			var pwd = document.getElementById('pwd').value;
			var rpwd = document.getElementById('rpwd').value;
			if(userpwd.replace(/\s/g, "").length==0||pwd.replace(/\s/g, "").length==0||rpwd.replace(/\s/g, "").length==0)
			{
			return;
			}
			else if (pwd != rpwd) {
			alert("两次新密码不一致！");
			} 
			else {
				var urlPath = "${ctx}/updatepwd.do?t=" + new Date().getTime();
				$.ajax({
					type : "POST",
					url : urlPath,
					data : {
						'uid' : userid,
						'userpwd' : userpwd,
						'newpwd' : pwd
					},
					dataType : "json",
					success : function(data) {
						if (data == "0") {
							alert("原始密码不正确！");
							//location.reload();
						} else if (data == "1") {
							alert("已修改成功，请重新登录。");
							goPage("../login.jsp");
						} else {
							alert("修改失败!");
							location.reload();
						}
					}
				});
				sleep(500);
			}
		}

		function sleep(numberMillis) {
			var now = new Date();
			var exitTime = now.getTime() + numberMillis;
			while (true) {
				now = new Date();
				if (now.getTime() > exitTime)
					return;
			}
		}
	</script>
</body>
</html>
