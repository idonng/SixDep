<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}/page"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
	<meta charset="UTF-8"/>
	<meta http-equiv=“X-UA-Compatible” content=“chrome=1″/>
    <title>ZBT | 事业六部积分制平台</title>
</head>
<frameset rows="7%,*" frameborder="no" border="0"  framespacing="0" >
    <frame name="frametop" src="${ctx}/jsp/top.jsp" border="0px" target="_self" noresize  scrolling="no"/>
    <frameset name="framebottom" cols="12%,*" frameborder="no" border="0" framespacing="0">
        <frame name="frameleft" src="${ctx}/jsp/left.jsp" border="0px" scrolling="no"  target="_self" noresize />
        <frame name="framemain" src="${ctx}/jsp/main.jsp" border="0px" scrolling="yes" target="_self" noresize />
    </frameset>
</frameset>
</html>
