<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
%>

<html>
<body>
<p>&nbsp;</p>
<%
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("jdbc:odbc:power","power","power");
	String mno = request.getParameter("mno");
	st=con.createStatement();

	String jy = "delete from billing where mno="+mno;

	 st.executeUpdate(jy);
%>
<h3 align ="center" > Deleted </h3>
