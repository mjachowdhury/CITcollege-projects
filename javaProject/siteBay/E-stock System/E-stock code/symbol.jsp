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
<%
String cid=(String)session.getValue("CUSTID");

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from Symbol");
%> 
 <br>
  <font size="4">Dear</font> <font color="#0080FF" size="5" face="Georgia, Times New Roman, Times, serif"><strong><em><%=(String)session.getValue("user_name")%></em></strong></font>,<br>
<br><br>
<table border="1" align="center" width="300" cellpadding="0" cellspacing="0" 

bordercolorlight="#c0003b" bordercolordark="#FFFFFF" style="border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" fpstyle="25,011111100">
  <tr> 
    <td width="221" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">CompanyName</font></b></font></td>
    <td width="73" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Symbol</font></b></font></td>
  </tr>
  <%
while(rs.next())
{
String a=rs.getString(1);
String b=rs.getString(2);
%> 
  <tr> 
    <td width="221" style="border-style: none" align="center"><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font>&nbsp;</td>
    <td width="73" style="font-weight: normal; color: black; border-style: none; background-color: lightblue"  align="center"><font size="2" face="Arial, Helvetica, sans-serif" ><%=b%></font>&nbsp;</td>
  </tr>
 <%
}
conn.close();
stmt.close();
}catch(SQLException ee){out.println("Error is :"+ee );}
%> 
</table>
<div align="center">
<pre><b><font color="#0000FF" face="Century Gothic">Use the following Search tool for getting 
Quotes of the Symbol...</font></b></pre>
</div>
<form name="quote" action="Get_Quote.jsp" method="post">
<p align="center">
<b><font color="#FF00FF" face="Century Gothic">Enter Symbol</font></b>:<input type="text" value="" name="Symbol" size="20">
<input type="submit" value="GetQuote" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
</p>
</form>

<form name="f">
  <div align="center">
    &nbsp;
    <input type="button" name="b3" value="Proceed to Checkout" onclick="b3click()" style="font-family: Comic Sans MS (cursive); font-style: italic; font-weight: bold; border: 3px outset #FF0000">
  </div>
</form>
</body>
</html>