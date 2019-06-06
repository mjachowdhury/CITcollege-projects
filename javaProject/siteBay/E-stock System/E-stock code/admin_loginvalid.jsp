<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
<%
/*Verifying the second level login entered by the customer with Customer_Master table of the savv.mdb database. */
Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
try
{
String name,password;
name="";
password="";
int i=0;
name=request.getParameter("login");
System.out.println(name);
password=request.getParameter("password");
System.out.println(password);
//to store session variable CID
//session.putValue("CUSTID",name);
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt=conn.prepareStatement("select * from admin where name=? and password=?");
stmt.setString(1,name);
stmt.setString(2,password);
rs=stmt.executeQuery();

if(rs.next())
{
i++;
out.println(i);
System.out.println(i);
System.out.println("fdfd");
}
if(i>0)
{
/*Creating a session variable to keep track of the CustomerID in the whole application. */
//session.putValue("CUSTID",cid);
%>
<jsp:forward page="admin_workshop.htm" />
<%
out.println("Welcome to Share Business Portal");
%>
<%
}else{
%>
<h1 align="center">Sorry! Ur username or password is invalid.</h1>
<CENTER><input type="button" value="Back" onclick="history.back()" />  </CENTER>
<%
}
conn.close();
}
catch(SQLException ee){out.println(ee);}
%>
