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
<body bgcolor="#FFFFFF">
<%
//retrive LogInID
String cid=(String)session.getValue("LogInID");
 
Connection conn=null;
PreparedStatement stmt1;
PreparedStatement stmt2;
String buy_query="SELECT CID,company_name,No_of_shares,Rate_per_share,Date_B,Status FROM Buyer WHERE cid=?";
String sell_query="SELECT CID,company_name,No_of_shares,Rate_per_share,Date_B,Status FROM Seller WHERE cid=?";
ResultSet rs1=null;
ResultSet rs2=null;

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt1=conn.prepareStatement(buy_query);
stmt1.setString(1,cid);
rs1=stmt1.executeQuery();
%> 
<br><br>
<H1>PURCHASE ACTIVITIES.......</H1>
<table border="1" align="center" width="75%" cellpadding="0" cellspacing="0" 

bordercolorlight="#c0003b" bordercolordark="#FFFFFF">
  <tr bgcolor="#c0003b"> 
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Symbol</font></b></font></td>
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">No_Of_Shares</font></b></font></td>
     <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Rate</font></b></font></td>
<td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Date</font></b></font></td>
<td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Status</font></b></font></td>
  </tr>
<%  
while(rs1.next())
{
String a=rs1.getString(2);
String b=rs1.getString(3);
String c=rs1.getString(4);
String d=rs1.getString(5);
String e=rs1.getString(6);
%> 
   <tr> 
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=b%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=c%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=d%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=e%></font></td>
	
  </tr></table>
 } 
  <%
  	stmt2=conn.prepareStatement(sell_query);
	stmt2.setString(1,cid);
	rs2=stmt2.executeQuery();
	%>
	<br><br>
<H1>SELLING ACTIVITIES.......</H1>
<table border="1" align="center" width="75%" cellpadding="0" cellspacing="0" 

bordercolorlight="#c0003b" bordercolordark="#FFFFFF">
  <tr bgcolor="#c0003b"> 
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Symbol</font></b></font></td>
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">No_Of_Shares</font></b></font></td>
     <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Rate</font></b></font></td>
<td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Date</font></b></font></td>
<td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Status</font></b></font></td>
  </tr>
<%  
while(rs2.next())
{
String f=rs2.getString(2);
String g=rs2.getString(3);
String h=rs2.getString(4);
String i=rs2.getString(5);
String j=rs2.getString(6);

//String f=rs.getString(8);
//String g=rs.getString(9);
//String h=rs.getString(10);
//String i=rs.getString(11);
//String j=rs.getString(12);
%> 
   <tr> 
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=f%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=g%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=h%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=i%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=j%></font></td>
	</tr></table>
  
}
<%
conn.close();
}catch(SQLException ee)
{out.println("Error is :"+ee );}
%> 

<form name="f">
  <div align="center">
    <input type="button" name="b1" value="More to buy..." onclick="b1click()">
    <input type="button" name="b2" value="Proceed to Checkout" onclick="b2click()">
  </div>
</form>
</body>
</html>
