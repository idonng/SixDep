<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}/page"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx}/css/fullcalendar.css" />
<link rel="stylesheet" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/css/media.css" />
<link href="${ctx}/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="${ctx}/libs/ligerUI/skins/Aqua/css/ligerui-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/libs/ligerUI/skins/Gray2014/css/all.css" />
</head>
<style>

#tab1,#tab2,#tab3{height: 300px;max-width: 100%;}
.more{background: none !important;}
.morecon{float:right !important;padding:9px 25px 6px 6px;font-weight:bold;color:#999;}

img {
    height: 0%;
}
#content {
     padding-bottom: 385px;
}
</style>
        <title>JSP Page</title>
    </head>
    <body style='height: 50%; min-height: 50%;'  >
    	<!--main-container-part-->
		<div id="content">
		<img src="${ctx}/img/bg1.png" >

</div>
 <!--Footer-part-->
<div class="row-fluid">
  <div id="footer" class="span12"> 2017 &copy; 步长制药. Copyright 电商部  </div>
</div>
<!--end-Footer-part-->

</body>
</html>
