<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
<html>
<head>
<script language="javascript">
function b1click()
{
f.action="cust_request.jsp";
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
stmt=conn.prepareStatement("SELECT CID,company_name,No_of_shares,Rate_per_share,Date_S,validity,date_s FROM Seller WHERE cid=?");
stmt.setString(1,cid);
rs=stmt.executeQuery();
%> 
<br><br>
 <br>
  <font size="4">Dear</font> <font color="#0080FF" size="5" face="Georgia, Times New Roman, Times, serif"><strong><em><%=(String)session.getValue("user_name")%></em></strong></font>
  </br>

<H1> <font size="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font color="#FF0000">&nbsp;&nbsp;&nbsp; SELLING ACTIVITIES</font></font></H1>
<table border="1" align="center" width="427" cellpadding="0" cellspacing="0" 

bordercolorlight="#c0003b" bordercolordark="#FFFFFF" style="border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" fpstyle="25,011111100">
  <tr> 
    <td width="77" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078">
    <b><font face="Arial, Helvetica, 
sans-serif" size="2" color="#FFFFFF">Symbol</font></b></td>
    <td width="114" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">No_Of_Shares</font></b></font></td>
    <td width="75" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, sans-serif">Rate    </font></b></font></td>
    <td width="85" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Date</font></b></font></td>
<td width="64" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
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
%><font color="#FFFFFF"> 
  <tr> 
    <td width="77" style="border-style: none"><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font>&nbsp;</td>
    <td width="114" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=b%></font>&nbsp;</td>
    <td width="75" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=c%></font>&nbsp;</td>
    <td width="85" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=d%></font>&nbsp;</td>
    <td width="64" style="font-weight: normal; color: black; border-style: none; background-color: white"><font size="2" face="Arial, Helvetica, sans-serif"><%=e%></font>&nbsp;</td>
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
    <input type="button" name="b1" value="More to sell..." onclick="b1click()" style="color: #800080; font-family: Bookman Old Style; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
  </div>
</form>
</body>
</html>