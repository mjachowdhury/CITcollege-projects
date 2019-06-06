<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
<%
/*Verifying the first level login entered by the customer with the Customer_master table of the savv.mdb database. */
Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
try
{
String logn="", pswd="";
int i=0;
logn=request.getParameter("login");
pswd=request.getParameter("password");
System.out.println(pswd);
//to store the login name as session variable
session.putValue("user_name",logn);
%>
<form name="f" action="login.jsp" method="post">
<input type="hidden" name="login" value="<%=logn%>">
<input type="hidden" name="password" value="<%=pswd%>">
<%
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt=conn.prepareStatement("select * from customer_master where logname=? and Password=?");
stmt.setString(1,logn);
stmt.setString(2,pswd);
rs=stmt.executeQuery();
while(rs.next())
{
i++;
}
if(i>0)
{
%>
<script language="javascript">
document.f.submit();
</script>
<%
}
else
{
%>
<script language="javascript">
alert("Invalid Login name/password. Please Re-enter");
location.href="home.htm"
</script>
<%
}
conn.close();
}
catch(SQLException ee)
{
out.println(ee);
}
%>
