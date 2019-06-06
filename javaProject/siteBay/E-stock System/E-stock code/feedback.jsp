<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
<%
String name="", email="", comments="";
Connection conn=null;
PreparedStatement stmt;
int rr=0;
//Retrieving the values of Feedback form in variables.
name=request.getParameter("name");
email=request.getParameter("email");
comments=request.getParameter("comments");
//Storing the values of the variables in the Feedback table of the savv.mdb database.
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt=conn.prepareStatement("insert into feedback (name, email, comments) values(?,?,?)");
stmt.setString(1,name);
stmt.setString(2,email);
stmt.setString(3,comments);
rr=stmt.executeUpdate();
conn.close();
}
catch(Exception e){}
%>
<jsp:forward page="home.htm" />
