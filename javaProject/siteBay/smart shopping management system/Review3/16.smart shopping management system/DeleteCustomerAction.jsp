<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="com.dts.core.util.CoreHash"%>
<%@page import="com.dts.dae.model.CustomerProfile"%>
<%@page import="java.util.*"%>
<%@page import="com.dts.dae.dao.CustomerDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DeleteCustomerAction.jsp' starting page</title>
    
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
  <% boolean flag=false; 
  String check[] = request.getParameterValues("check");
  for(int i=0;i<check.length;i++)
  {
  		String temp =check[i];
        flag = new CustomerDAO().deleteCustomer(temp);
  } 
   		
         String target = "ListCustomer.jsp?status=Customer Deletion Failed";
         if(flag)
            target = "AdminHome.jsp?status=Customer Deletion Success";
         else  
            target = "ListCustomer.jsp?status=Customer Deletion Failed"; 
        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response); 
  %>
  
  
  </body>
</html>
