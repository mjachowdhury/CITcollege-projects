<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
 <script language="javascript">
function b2click()
{
f.action="home.htm";
f.method="post";
f.submit();
}

function b3click()
{
f.action="admin_get_quote.jsp";
f.method="post";
f.submit();
}
</script>
<body bgcolor="#FFFFFF">
<% 
String symbol = (String) session.getAttribute("symbol");


Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt=conn.prepareStatement("SELECT * FROM Buyer WHERE COMPANY_NAME=?");
stmt.setString(1, symbol);
rs=stmt.executeQuery();
%> 
<iframe name="I1" width="1000" height="135" target="f2" scrolling="no" border="0" frameborder="0" src="admin_sellbuy.htm">
Your browser does not support inline frames or is currently configured not to display inline frames.</iframe> 
<br><br>
<div align="center">
  <center>
<table border="1" width="627" cellpadding="0" cellspacing="0" 

bordercolorlight="#C0003B" bordercolordark="#FFFFFF" style="border-collapse: collapse; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" fpstyle="25,011111100" bordercolor="#111111">
  <tr bgcolor="#c0003b">
  	<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="55">
    <font face="Arial, Helvetica, sans-serif" size="2">CustID</font></td> 
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="64">
    <font face="Arial, Helvetica, sans-serif" size="2">Symbol</font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="123">
	<font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">No_Of_Shares</font></b></font></td>
     <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="87"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Rate</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="221"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Validity</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="100"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Date</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="36">
    <font face="Arial, Helvetica, 
sans-serif" size="2">OrderType</font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="40">
    <font face="Arial, Helvetica, 
sans-serif" size="2">Status</font></td>
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
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="103"><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="112"><font size="2" face="Arial, Helvetica, sans-serif"><%=b%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="86"><font size="2" face="Arial, Helvetica, sans-serif"><%=c%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="87"><font size="2" face="Arial, Helvetica, sans-serif"><%=d%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="221"><font size="2" face="Arial, Helvetica, sans-serif"><%=e%></font>&nbsp;</td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="100"><font size="2" face="Arial, Helvetica, sans-serif"><%=f%></font>&nbsp;</td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="84"><font size="2" face="Arial, Helvetica, sans-serif"><%=g%></font>&nbsp;</td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="88"><font size="2" face="Arial, Helvetica, sans-serif"><%=h%></font>&nbsp;</td>
  </tr>
  <%
  session.putValue("rate_buy",d);
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
      <input type="button" name="b2" value="Proceed to Checkout" onclick="b2click()" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
	  <input type="button" name="b3" value="Back" onclick="b3click()" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">

  </div>
</form>
</body>
</html>