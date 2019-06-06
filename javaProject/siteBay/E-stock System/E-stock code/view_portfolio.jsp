<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
<html>
<head>
<script language="javascript">
function b2click()
{
f.action="home.htm";
f.method="post";
f.target="_top";
f.submit();
}
</script>
</head>
<body bgcolor="oldlace">
<div align="center">
  <%
String cid=(String)session.getAttribute("CUSTID");   //getValue("CUSTID");
Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt=conn.prepareStatement("SELECT cid,symbol,no_shares_traded,buy_rate,sale_rate,purchase_value,sale_value,profit FROM portfolio WHERE cid=?");
stmt.setString(1,cid);
rs=stmt.executeQuery();
%>
  <br>
  <font size="4">Dear</font> <font color="#0080FF" size="5" face="Georgia, Times New Roman, Times, serif"><strong><em><%=(String)session.getAttribute("user_name")%></em></strong></font>,<br>
</div>
<H1 align="left"> <font size="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  <font color="#FF0000">&nbsp;&nbsp;&nbsp; Your Portfolio as on </font></font></H1>
<H1 align="center"><font size="5"><font color="#FF0000"><font color="#8000FF" size="3"><%= new java.util.Date()%></font></font></font></H1>
<table border="1" align="center"  cellpadding="0" cellspacing="0"  width=90%

bordercolorlight="#c0003b" bordercolordark="#FFFFFF" style="border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" fpstyle="25,011111100">
  <tr > 
    <td width="77" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078">
    <b><font face="Arial, Helvetica, 
sans-serif" size="2" color="#FFFFFF">Symbol</font></b></td>
    <td width="114" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Shares Traded</font></b></font></td>
<td width="114" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Avg. Buy Rate</font></b></font></td>
<td width="114" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Avg. Sale Rate</font></b></font></td>  
    <td width="114" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Purchase Value</font></b></font></td>
<td width="114" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Sale Value</font></b></font></td>
    <td width="85" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Profit/Loss</font></b></font></td>
  </tr>
  <%
while(rs.next())
{
String a=rs.getString(2);
String b=rs.getString(3);
String c=rs.getString(4);
String d=rs.getString(5);
String e=rs.getString(6);
String f=rs.getString(7);
String g=rs.getString(8);
%><font color="#FFFFFF"> 
  <tr> 
 	<td width="114" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font>&nbsp;</td>   
    <td width="114" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=b%></font>&nbsp;</td>
    <td width="75" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=c%></font>&nbsp;</td>
    <td width="85" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=d%></font>&nbsp;</td>
    <td width="64" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=e%></font>&nbsp;</td>
	<td width="64" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=f%></font>&nbsp;</td>
	<td width="64" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=g%></font>&nbsp;</td>
	</tr>
  <%
}
conn.close();
}
catch(SQLException ee){out.println("Error is :"+ee );}
%> </font> 
</table>
<form name="f">
  <div align="center">
  </div>
</form>
</body>
</html>