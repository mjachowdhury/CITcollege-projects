<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'navigation.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body background="<%=path%>/images/shade1.gif">
 <UL>
<li><em><font size="4" color="#017e7e"><strong><a href="welcome.jsp" target="main">home </a></strong></font></em></li>
 <li><em><font size="4" color="#017e7e"><strong><a href="AdminLogin.jsp" target="main">Admin</a></strong></font></em></li>
  <li><em><font size="4" color="#017e7e"><strong><a href="visitor.jsp" target="main">Visitor </a></strong></font></em></li>
 <li><em><font size="4" color="#017e7e"><strong><a href="PoliceLogin.jsp" target="main">VerificationTeam </a></strong></font></em></li>
 </UL>
 </body>
</html>
