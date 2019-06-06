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
f.action="home.htm";
f.method="post";
f.target="_top";
f.submit();
}
</script>
</head>
<body bgcolor="oldlace">
<%
/*Retrieving the values of Sell Shares form in variables. */
String cid,company,no_shares,validity,order_type,rate;
cid=(String)session.getValue("CUSTID");
 

Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt=conn.prepareStatement("SELECT CID,company_name,No_of_shares,Rate_per_share,Date_B,VALIDITY FROM Buyer WHERE cid=?");
stmt.setString(1,cid);
rs=stmt.executeQuery();
%> 
<br><br>
 <br>
  <font size="4">Dear</font> <font color="#0080FF" size="5" face="Georgia, Times New Roman, Times, serif"><strong><em><%=(String)session.getValue("user_name")%></em></strong></font>
  </br>

<H1><font size="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font color="#FF0000">PURCHASE ACTIVITIES</font></font></H1>
<div align="center">
  <center>
<table border="1"	cellpadding="0" cellspacing="0" 

bordercolorlight="#008080" bordercolordark="#FFFFFF" fpstyle="25,011111100" style="border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray">
  <tr bgcolor="#c0003b"> 
    <td width="79" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078">
    <p align="left"><b>
    <font face="Arial, Helvetica, 
sans-serif" size="2" color="#FFFFFF">Symbol</font></b></td>
    <td width="96" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078">
    <p align="left"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">No_Of_Shares</font></b></font></td>
    <td width="81" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078">
    <p align="left"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, sans-serif">Rate    </font></b></font></td>
    <td width="59" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078">
    <p align="left"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Date</font></b></font></td>
<td width="49" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078">
<p align="left"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Status</font></b></font></td>
  </tr>
  <%
while(rs.next())
{
String a=rs.getString(2);
String b=rs.getString(3);
String c=rs.getString(4);
String d=rs.getString(5);
String e=rs.getString(6);
%> 
  <tr> 
    <td width="79" style="border-style: none"><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font>&nbsp;</td>
    <td width="96" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=b%></font>&nbsp;</td>
    <td width="81" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=c%></font>&nbsp;</td>
    <td width="59" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=d%></font>&nbsp;</td>
    <td width="49" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=e%></font>&nbsp;</td>
	</tr>
  <%
}
conn.close();
}
catch(SQLException ee){out.println("Error is :"+ee );}
%> 
</table>
  </center>
</div>
<form name="f">
  <div align="center">
    <input type="button" name="b1" value="More to Buy..." onclick="b1click()" style="color: #800080; font-style: italic; font-weight: bold; border: 3px inset #FF0000">
  </div>
</form>
</body>
</html>