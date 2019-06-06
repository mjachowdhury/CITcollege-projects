<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.es.dto.*" %>
<%@page import="com.dts.em.dao.*" %>
<%@page import="com.dts.em.fun.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<%
 String temp="";
 boolean flag=false;
 temp = request.getParameter("id");
 AmbulanceStatus as = new AmbulanceStatus();
 flag=as.getAmbulanceStatus(temp);
  
 if(flag==true)
 {
	RequestDispatcher rd = request.getRequestDispatcher("sentAmbulance.jsp?id="+temp);
    rd.forward(request,response);     
 }else
 {
 	RequestDispatcher rd = request.getRequestDispatcher("sendingAmbulance.jsp?id="+temp);
    rd.forward(request,response);
 }
 %>

</body>
</html>
