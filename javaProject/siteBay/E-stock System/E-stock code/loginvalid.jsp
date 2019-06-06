<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
<%
/*Verifying the second level login entered by the customer with Customer_Master table of the savv.mdb database. */
Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
String cid1="";
try
{
String cid, pswd;
cid="";
pswd="";
int i=0;
cid=request.getParameter("login");
System.out.println(cid);
pswd=request.getParameter("password");
System.out.println(pswd);
//to store session variable CID
session.putValue("LogInID",cid);
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt=conn.prepareStatement("select * from customer_master where cid = ? and Password = ? ");
stmt.setString(1,cid);
stmt.setString(2,pswd);
rs=stmt.executeQuery();
if(rs.next())
{
i++;
out.println(i);
//System.out.println(i);
 cid1=rs.getString(1);
System.out.println("loginvalid"+cid1);
cid = rs.getString(2);

}
if(i>0)
{
/*Creating a session variable to keep track of the CustomerID in the whole application. */
session.putValue("CUSTID",cid1);
session.setAttribute("CUSTID",cid1);
session.setAttribute("user_name", cid);
System.out.println("welcom 11..............");
%>
<jsp:forward page="sellbuy2.htm" />
<%
	System.out.println("welcom 22 ..............");
out.println("Welcome to Share Business Portal"); 
%>
<%
}
else
{
%>
<script language="javascript">
alert("Invalid CustomerID/password. Please Re-enter");
location.href="login.jsp"
</script>
<%
}
conn.close();
}
catch(SQLException ee){out.println(ee);}
%>
