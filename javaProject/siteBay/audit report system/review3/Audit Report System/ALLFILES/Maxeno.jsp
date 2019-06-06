<%@ page import="java.sql.*" errorPage="ErrorPage.jsp" %>

<%
System.out.println("Got the request");
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("Jdbc:Odbc:DSN","scott","tiger");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("SELECT MAX(ENO)FROM JEMP");

if(rs.next())
out.println(rs.getInt(1)+1+"");
con.close();
%>