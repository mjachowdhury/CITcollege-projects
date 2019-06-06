<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
<html>
<head>
  
</head>
<body bgcolor="oldlace">
		
<div align="center">
<h1>E-Stock System</h1>
  <font size="6" color="#008080"><marquee style="font-family: Century Gothic; color: #800080; font-weight: bold; font-size: 15" bgcolor="blue" width="735">Your Online Bussiness Partner</marquee></font></i></span><%
String symbol=request.getParameter("Symbol");
/*Storing the values of the variables in the Seller table of the savv.mdb database. */
 
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
PreparedStatement stmt=conn.prepareStatement("select customer_master.name,customer_master.address,buyer.no_of_shares,customer_master.emailid from buyer,customer_master,symbol where Symbol=? and symbol.symbol=buyer.company_name and buyer.cid = customer_master.cid");
stmt.setString(1,symbol);
ResultSet rs=stmt.executeQuery();
%> <font color="#FF0000" size="5"><strong>Current Time : </strong></font><%= new java.util.Date()%>
  <br>
</p>
<table border="1" align="center" width="619" cellpadding="0" cellspacing="0" 

bordercolorlight="#c0003b" bordercolordark="#FFFFFF" style="border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" fpstyle="25,011111100">
   <h3>Company:<%="   "+symbol%></h3>
<!--   <tr> 
    <td style="border-style: none" width="15"><font size="4" face="Arial, Helvetica, sans-serif"><%=symbol%></font>&nbsp;</td>
  </tr> -->
  
  <br>

 <!-- <tr bgcolor="#c0003b"> 
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
  </tr> -->

  <tr bgcolor="#c0003b"> 
    <td width="221" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">CustomerName</font></b></font></td>
    <td width="73" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Address</font></b></font></td>
    <td width="73" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Email</font></b></font></td>
    <td width="73" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">No.of.shares</font></b></font></td>

  </tr>

  <%
while(rs.next())
{
String a=rs.getString(1);
String b=rs.getString(2);
String c=rs.getString(3);
String d=rs.getString(4);
/*String e=rs.getString(5);
String f=rs.getString(6);
String g=rs.getString(7);
String h=rs.getString(8);*/
%> 

  
  <tr> 
    <td style="border-style: none" width="71"><font size="4" face="Arial, Helvetica, sans-serif"><%=a%></font>&nbsp;</td>
  

     
    <td style="border-style: none" width="71"><font size="4" face="Arial, Helvetica, sans-serif"><%=b%></font>&nbsp;</td>
  
    <td style="border-style: none" width="71"><font size="4" face="Arial, Helvetica, sans-serif"><%=d%></font>&nbsp;</td>

  
    <td style="border-style: none" width="71"><font size="4" face="Arial, Helvetica, sans-serif"><%=c%></font>&nbsp;</td>
  </tr>

 
  <%
}
conn.close();
}catch(SQLException ee){out.println("Error is :"+ee );}
%> 
</table>
<!--<form name="f">
  <div align="center">
    <input type="button" name="b1" value="ViewBuyTransaction.." onclick="b1click()" style="font-family: Century Gothic; font-style: italic; font-weight: bold; border: 3px inset #FF0000">
	<input type="button" name="b2" value="ViewSellTransaction." onclick="b2click()" style="font-family: Comic Sans MS; font-style: italic; font-weight: bold; border: 3px inset #FF0000">
    <input type="button" name="b3" value="Proceed to Checkout" onclick="b3click()" style="font-family: Comic Sans MS; font-style: italic; font-weight: bold; border: 3px outset #FF0000">
  </div>
</form>-->
</body>
</html>