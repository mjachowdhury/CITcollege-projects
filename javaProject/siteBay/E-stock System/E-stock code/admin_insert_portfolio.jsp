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
function b3click()
{
f.action="admin_sellbuy2.htm";
f.method="post";
f.target="_top";
f.submit();
}

</script>
</head>
<body bgcolor="oldlace">
<%
/*Retrieving the values of Sell Shares form in variables. */
String cid,symbol,no_shares,b_rate,s_rate,cp,sp,prof_los;
cid=(String)session.getValue("cust_id");
symbol=request.getParameter("symbol");
b_rate=request.getParameter("buy_rate");
s_rate=request.getParameter("sale_rate");
no_shares=request.getParameter("no_shares");
cp=request.getParameter("purchase_value");
sp=request.getParameter("sale_value");
prof_los=request.getParameter("result");

 
/*if(cid !=null && symbol != null && b_rate != null && s_rate != null && no_shares != null && cp != null && sp != null && prof_los != null)
*/
if(cid != null){

int cust_id;float share_no,b_price,s_price,cos_price,sel_price, hlep;
cust_id=Integer.parseInt(cid);
share_no=Float.parseFloat(no_shares);
b_price=Float.parseFloat(b_rate);
s_price=Float.parseFloat(s_rate);
cos_price=Float.parseFloat(cp);
sel_price=Float.parseFloat(sp);
hlep=Float.parseFloat(prof_los);
/*Storing the values of the variables in the Seller table of the savv.mdb database. */
 Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
int y=0;
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");

try
{
stmt=conn.prepareStatement("insert into portfolio values(?,?,?,?,?,?,?,?)");
System.out.println("y is"+y);

stmt.setInt(1,cust_id);
stmt.setString(2,symbol);
stmt.setFloat(3,share_no);
stmt.setFloat(4,b_price);
stmt.setFloat(5,s_price);
stmt.setFloat(6,cos_price);
stmt.setFloat(7,sel_price);
stmt.setFloat(8,hlep);
y=stmt.executeUpdate();
System.out.println("y is"+y);
if(y>=1)
{
stmt=conn.prepareStatement("select * from portfolio where cid=?");
stmt.setInt(1,cust_id);
rs=stmt.executeQuery();
%>
<p align="center">&nbsp;</p>
<div align="center">
  <center>
<table border="1" width="616" cellpadding="0" cellspacing="0" 

bordercolorlight="#C0003B" bordercolordark="#FFFFFF" style="border-collapse: collapse; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" fpstyle="25,011111100" bordercolor="#111111" height="63">
  <tr bgcolor="#c0003b"> 
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="62" height="42">
    <font face="Arial, Helvetica, 
sans-serif" size="2">CustometID</font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="115" height="42"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Symbol</font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="75" height="42"><font color="#FFFFFF"><b>
	<font size="2" face="Arial, Helvetica, sans-serif">Shares_Traded </font></b></font></td>
	<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="72" height="42">
    <p align="center"><font color="#FFFFFF"><b>
	<font size="2" face="Arial, Helvetica, sans-serif">Buy_Rate</font></b></font></td>
	<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="71" height="42"><font color="#FFFFFF"><b>
	<font size="2" face="Arial, Helvetica, sans-serif">Sale_Rate</font></b></font></td>
	<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="71" height="42">
    <p align="center"><font color="#FFFFFF"><b>
	<font size="2" face="Arial, Helvetica, sans-serif">Purchase Value</font></b></font></td>
	<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="86" height="42">
    <p align="center"><font color="#FFFFFF"><b>
	<font size="2" face="Arial, Helvetica, sans-serif">Sale Value</font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="155" height="42"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Profit/Loss</font></b></font></td>
  </tr>
  <%
if(rs != null)
	{
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
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="110" height="17"><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font></td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="78" height="17"><font size="2" face="Arial, Helvetica, sans-serif"><%=b%></font></td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="109" height="17"><font size="2" face="Arial, Helvetica, sans-serif"><%=c%></font></td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="109" height="17"><font size="2" face="Arial, Helvetica, sans-serif"><%=d%></font></td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="108" height="17"><font size="2" face="Arial, Helvetica, sans-serif"><%=e%></font></td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="108" height="17"><font size="2" face="Arial, Helvetica, sans-serif"><%=f%></font></td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="123" height="17"><font size="2" face="Arial, Helvetica, sans-serif"><%=g%></font></td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="158" height="17"><font size="2" face="Arial, Helvetica, sans-serif"><%=h%></font></td>
	</tr>
  <%
}
	}
}
conn.close();
}
catch(SQLException ee)
{
    out.println("Error is :"+ee );
}

}
%> 
</table>
  </center>
</div>
<form name="f">
  <div align="center">
    <input type="button" name="b2" value="Proceed to Checkout" onclick="b2click()" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
    <input type="button" name="b3" value="Back" onClick="b3click()" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
  </div>
</form>
</body>
</html>