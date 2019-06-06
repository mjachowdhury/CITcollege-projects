<%@ page import "java.sql.*" errorPage="Errorpage.jsp" %>

<%

String s1 = request.getParameter("N1");
String s2 = request.getParameter("N2");

int res = Integer.parseInt(s1)/Integer.parseInt(s2);

out.println("Result = " + res);
%>