<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>java 计数器程序</title>
</head>
<body>
	<% 
 int n = 0; 
  Date timeInit=null;
 String counter = (String)application.getAttribute("counter"); 
 String lasttime = (String)application.getAttribute("lasttime"); 
 if(counter != null){ 
  n = Integer.parseInt(counter); 
 } 
 if(session.isNew()) {
   timeInit=new Date();
  	++n; 
   lasttime=String.valueOf(timeInit);
   
   }
  counter = String.valueOf(n); 
   
  application.setAttribute("counter", counter); 
  application.setAttribute("lasttime", lasttime);
 %>
	<center>
		这是累计第<%out.print(n);%>个访问者，最后一次访问时间<%out.print(lasttime);%>
	</center>
	<% 
  
   %>
   <% response.setHeader("Refresh","0;URL='https://www.baidu.com'");%>
</body>
</html>
<script type="text/javascript">
var caution=false
function setCookie(name,value,expires,path,domain,secure) 
{
 var curCookie=name+"="+escape(value) +
 ((expires)?";expires="+expires.toGMTString() : "") +
 ((path)?"; path=" + path : "") +
 ((domain)? "; domain=" + domain : "") +
 ((secure)?";secure" : "")
 if(!caution||(name + "=" + escape(value)).length <= 4000)
 {
 document.cookie = curCookie
 }
 else if(confirm("Cookie exceeds 4KB and will be cut!"))
 {
 document.cookie = curCookie
 }
}
function getCookie(name) 
{
 var prefix = name + "="
 var cookieStartIndex = document.cookie.indexOf(prefix)
 if (cookieStartIndex == -1)
 {
 return null
 }    
 var cookieEndIndex=document.cookie.indexOf(";",cookieStartIndex+prefix.length)
 if(cookieEndIndex == -1)
 {
 cookieEndIndex = document.cookie.length
 }
 return unescape(document.cookie.substring(cookieStartIndex+prefix.length,cookieEndIndex))
}
function deleteCookie(name, path, domain) 
{
 if(getCookie(name)) 
 {
 document.cookie = name + "=" + 
 ((path) ? "; path=" + path : "") +
 ((domain) ? "; domain=" + domain : "") +
 "; expires=Thu, 01-Jan-70 00:00:01 GMT"
 }
}
function fixDate(date) 
{
 var base=new Date(0)
 var skew=base.getTime()
 if(skew>0)
 {
 date.setTime(date.getTime()-skew)
 }    
}
var now=new Date()
fixDate(now)
now.setTime(now.getTime()+365 * 24 * 60 * 60 * 1000)
var visits = getCookie("counter")
if(!visits)
{
 visits=1;
}  
else
{
 visits=parseInt(visits)+1;
}  
setCookie("counter", visits, now)
document.write("您是到访的第" + visits + "位用户！")
</script>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script type="text/javascript">  
document.write(returnCitySN["cip"]+','+returnCitySN["cname"])  
</script>