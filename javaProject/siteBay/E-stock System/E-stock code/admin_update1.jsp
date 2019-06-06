<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
<html>
<head>
<script language="javascript">
function b1click()
{
f.action="admin_calculate.jsp";
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
String cid=(String)session.getValue("CUSTID");
int cust_id=Integer.parseInt(cid);
Connection conn=null;
PreparedStatement stmt_sell,stmt_buy;
//ResultSet rs=null;
if(cid!=null)
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt_sell=conn.prepareStatement("Update Seller SET Status='Done' Where CID=? and Order_Type='Market'");
stmt_buy=conn.prepareStatement("Update Buyer SET Status='Done' Where CID=? and Order_Type='Market'");
stmt_sell.setInt(1,cust_id);
stmt_buy.setInt(1,cust_id);
int x=stmt_sell.executeUpdate();
int y=stmt_buy.executeUpdate();
conn.close();
}catch(SQLException ee){out.println("Error is :"+ee );}
}
%>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
<p align="center"><b><font size="4" face="Garamond">The Market Order has been 
processed.....<br></font></b></p>
<form name="f">
  <div align="center">
    <input type="button" name="b1" value="Calculate" onclick="b1click()" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
    <input type="button" name="b2" value="Proceed to Checkout" onclick="b2click()" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
  </div>
</form>
</body>
</html>