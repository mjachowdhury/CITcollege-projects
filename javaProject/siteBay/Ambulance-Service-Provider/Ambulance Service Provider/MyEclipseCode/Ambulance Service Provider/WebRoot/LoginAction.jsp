<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="com.dts.dae.dao.ProfileDAO,com.dts.dae.dto.RegistrationBean,com.dts.dae.dao.SecurityDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'RegisterAction.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<br>
		<% 
		 RegistrationBean  rb=new RegistrationBean();
		 String username = request.getParameter("username");
         rb.setLoginID(username);
         rb.setPassword(request.getParameter("password"));
         
         String role=new SecurityDAO().loginCheck(rb);
         String target = "index.jsp?status=Invalid username and password";
         if(role.equals("admin"))
         { 
            target = "adminhome.jsp?status=Welcome "+username;
            session.setAttribute("user",username);
            session.setAttribute("role",role);
         }else if(role.equals("user"))
         {
         	target = "userhome.jsp?status=Welcome "+username;
            session.setAttribute("user",username);
            session.setAttribute("role",role);
         } 
         else  
            target = "LoginForm.jsp?status=Invalid username and password";
             
        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response);    
    %>
	</body>
</html>
