<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="com.dts.es.dto.*" %>
<%@page import="com.dts.em.dao.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'newPatientAction.jsp' starting page</title>
    
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
  
 <%  PatientDTO pdto = new PatientDTO();
  pdto.setFirstname(request.getParameter("firstname"));
  pdto.setLastname(request.getParameter("lastname"));
  pdto.setDob(request.getParameter("dob"));
  pdto.setLocation(request.getParameter("location"));
  pdto.setCity(request.getParameter("city"));
  pdto.setState(request.getParameter("state"));
  pdto.setAddress(request.getParameter("address"));
  pdto.setPhone(request.getParameter("phone"));
  pdto.setHeight(request.getParameter("height"));
  pdto.setWeight(request.getParameter("weight"));
  pdto.setJdate(request.getParameter("jdate"));
  pdto.setType(request.getParameter("type"));
  boolean flag = new PatientDAO().registerPatients(pdto);
        String target = "userhome.jsp?status=Patient Registration Failed";
         if(flag)
            target = "userhome.jsp?status=Patient Registration Success";
         else  
            target = "userhome.jsp?status=Patient Registration Failed"; 
        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response); 
  %>
  
  
  </body>
</html>
