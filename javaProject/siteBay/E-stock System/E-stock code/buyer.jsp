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
<%!

String cid = "", company = "", no_shares = "", validity = "", order_type = "", rate = "";
int cust_id = 0, share_no = 0;
float share_rate = 0.0f;
Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
int y=0;
%>

<%

try
{
cid=(String)session.getValue("CUSTID");
company=(String)session.getValue("name_company");
no_shares=(String)session.getValue("no_share");
validity=(String)session.getValue("validity");
order_type=(String)session.getValue("order_type");
rate=(String)session.getValue("rate");
cust_id=Integer.parseInt(cid);
share_no=Integer.parseInt(no_shares);
share_rate=Float.parseFloat(rate);
}
catch(Exception e)
{
    out.print(e);
}
/*Storing the values of the variables in the Seller table of the savv.mdb database. */
 
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
stmt=conn.prepareStatement("insert into buyer(CID,COMPANY_NAME,NO_OF_SHARES,RATE_PER_SHARE, VALIDITY,DATE_B,ORDER_TYPE, commission) values (?,?,?,?,?,?,?, ?)");
java.util.Date now = new java.util.Date();
DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
String s1 = df1.format(now);
stmt.setInt(1,cust_id);
stmt.setString(2,company);
stmt.setInt(3,share_no);
stmt.setFloat(4,share_rate);
stmt.setString(5,validity);
stmt.setString(6,s1);
stmt.setString(7,order_type);
//retrieving commisiion from session object

double commission = Double.parseDouble((String)session.getAttribute("commission"));
stmt.setDouble(8, commission);
y=stmt.executeUpdate();


if(y>=1)
{
stmt=conn.prepareStatement("select * from buyer where cid=?");
stmt.setString(1,cid);
rs=stmt.executeQuery();
%> 
<br><br>
<table border="1" align="center" width="531" cellpadding="0" cellspacing="0" 

bordercolorlight="#c0003b" bordercolordark="#FFFFFF" style="border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" fpstyle="25,011111100">
  <tr bgcolor="#c0003b"> 
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="59"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Symbol</font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="96"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">No_Of_Shares</font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="62"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, sans-serif">Rate</font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="103"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Validity</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="119"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Date Of Purchase</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="92"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Order Type</font></b></font></td>
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
%> 
  <tr> 
    <td style="border-style: none" width="59"><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="96"><font size="2" face="Arial, Helvetica, sans-serif"><%=b%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="62"><font size="2" face="Arial, Helvetica, sans-serif"><%=c%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="103"><font size="2" face="Arial, Helvetica, sans-serif"><%=d%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="119"><font size="2" face="Arial, Helvetica, sans-serif"><%=e%></font>&nbsp;</td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="92"><font size="2" face="Arial, Helvetica, sans-serif"><%=f%></font>&nbsp;</td>
  </tr>
  <%
}
}
conn.close();
}
catch(SQLException ee){out.println("Error is :"+ee );}
%> 
</table>
<form name="f">
  <div align="center">
    <input type="button" name="b1" value="More to buy..." onclick="b1click()" style="color: #800080; font-style: italic; font-weight: bold; border: 3px outset #800080; ">
    <input type="button" name="b2" value="Proceed to Checkout" onclick="b2click()" style="color: #800080; font-style: italic; font-weight: bold; border: 3px inset #FF0000">
  </div>
</form>
</body>
</html>