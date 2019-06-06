<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.omg.CORBA.PUBLIC_MEMBER"%>
<%
		String username=getServletContext().getInitParameter("Username");
		String password=getServletContext().getInitParameter("Password");
		System.out.println(username);
		
if(request.getParameter("username").equalsIgnoreCase("admin")&& request.getParameter("userpass").equalsIgnoreCase("admin"))
{
session.setAttribute("islogin","plz sign in first");
session.setAttribute("username","Administrator");
%>
<jsp:forward page="astatus.jsp"></jsp:forward>
<% 
}

else{
request.setAttribute("Error","Sorry! Username or Password Error.");
session.setAttribute("Loginmsg","plz sign in first");
%>
<jsp:forward page="admin.jsp"></jsp:forward>
<% 
}
%>

