<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
<html>
<head>
<script language="javascript">
function b1click()
{
f.action="cust_request2.jsp";
f.method="post";
f.submit();
}
function b2click()
{
f.action="cust_request.jsp";
f.method="post";
f.target="_top";
f.submit();
}
function b3click()
{
f.action="home.htm";
f.method="post";
f.target="_top";
f.submit();
}
</script>
</head>
<body bgcolor="oldlace">
		
<p>
  <%
String symbol=request.getParameter("Symbol");
/*Storing the values of the variables in the Seller table of the savv.mdb database. */
 
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
PreparedStatement stmt=conn.prepareStatement("select * from Quote where Symbol=?");
stmt.setString(1,symbol);
ResultSet rs=stmt.executeQuery();
%>
</p>
<p align="center"> <font color="#FF0000" size="5"><strong>Current Time : </strong></font><%= new java.util.Date()%>
  <br>
</p>
<table border="1" align="center" width="619" cellpadding="0" cellspacing="0" 

bordercolorlight="#c0003b" bordercolordark="#FFFFFF" style="border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" fpstyle="25,011111100">
  <tr bgcolor="#c0003b"> 
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="71"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Symbol</font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="95"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">CurrentPrice</font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="61"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, sans-serif">Change</font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="68"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Volume</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="71"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Day High</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="72"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Day Low</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="73"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Day Open</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="108"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Previous Price</font></b></font></td>
  </tr>
  <%
while(rs.next())
{
String a=rs.getString(1);
String b=rs.getString(2);
String c=rs.getString(3);
String d=rs.getString(4);
String e=rs.getString(5);
String f=rs.getString(6);
String g=rs.getString(7);
String h=rs.getString(8);
%> 
  <tr> 
    <td style="border-style: none" width="71"><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="95"><font size="2" face="Arial, Helvetica, sans-serif"><%=b%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="61"><font size="2" face="Arial, Helvetica, sans-serif"><%=c%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="68"><font size="2" face="Arial, Helvetica, sans-serif"><%=d%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="71"><font size="2" face="Arial, Helvetica, sans-serif"><%=e%></font>&nbsp;</td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="72"><font size="2" face="Arial, Helvetica, sans-serif"><%=f%></font>&nbsp;</td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="73"><font size="2" face="Arial, Helvetica, sans-serif"><%=g%></font>&nbsp;</td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="108"><font size="2" face="Arial, Helvetica, sans-serif"><%=h%></font>&nbsp;</td>
  </tr>
  <%
}
conn.close();
}catch(SQLException ee){out.println("Error is :"+ee );}
%> 
</table>
<form name="f" action="login.jsp">
  <div align="center">
    &nbsp;
    <input type="button" name="b3" value="Proceed to Checkout" onclick="b3click()" style="border-style:outset; border-width:3px; font-family: Comic Sans MS; font-style: italic; font-weight: bold; color:#800080">
  </div>
</form>
</body>
</html>