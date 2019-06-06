<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.lang.Integer,java.util.*,java.text.*" %>
<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
<%!
/*Verifying the first level login entered by the customer with the Customer_master table of the savv.mdb database. */
Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
String opt = "";
%>

<html>
<head>
<script language="javascript">

function ModifyOrder()
{
	preview.action="cust_request.jsp";
	preview.method="post";
	preview.submit();
}

function ConfirmOrder()
{
	alert("Dear Customer You are about to place Order");
}
</script>

</head>
<body bgcolor="#FFFFFF">
<h1 align="center"><font color="#400080"><i><b><font color="#0000FF" size="3" face="Garamond">Dear</font></b></i></font><i><b><font color="#0000FF" face="Garamond" size="3"> 
  <font color="#800040" size="5"><%=(String)session.getValue("user_name")%></font></font></b></i></h1>
<h1 align="center"><strong><font color="#008000" face="Bookman Old Style" size="4">Here 
  is your order.....</font></strong></h1> 
<%
/*Retrieving the values of Sell Shares form in variables. */
String cid,company,no_shares,validity,rate = "",Order_Type;
double shr_rate,shr_num,value,commission,total;

company=request.getParameter("company");
no_shares=request.getParameter("no_shares");
validity=request.getParameter("validity");
Order_Type=request.getParameter("Order_Type");
//rate=request.getParameter("rate");
//for calculation




try
{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
String q = "select CURRENT_PRICE from quote where SYMBOL = '"+company+"'";
stmt=conn.prepareStatement(q);
rs = stmt.executeQuery();

if(rs.next())
    rate = rs.getString(1);
}
catch(Exception e)
{
    out.print(e);
}

 shr_rate=Double.parseDouble(rate);
shr_num=Double.parseDouble(no_shares);
value=shr_rate*shr_num;
commission=value*0.02;
total=value+commission;
//set the values into a session variables
session.setAttribute("name_company",company);
session.setAttribute("no_share",no_shares);
session.setAttribute("validity",validity);
session.setAttribute("order_type",Order_Type);
session.setAttribute("rate",rate);
/*Storing the values of the variables in the Seller table of the savv.mdb database. */
 

%>
                <div align="center">
                  <center>
<table width="497" border="1" fpstyle="25,011111100" style="border-collapse: collapse; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" bordercolor="#111111" cellpadding="0" cellspacing="0">
  <tr> 
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="72">
    <div align="left"><strong><em>Symbol</em></strong></div></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="132">
    <div align="left"><strong><em>Number Of Shares</em></strong></div></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="121">
    <div align="left"><strong><em>Validity Of Order</em></strong></div></td>
	<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="81">
    <div align="left"><strong><em>Order Type</em></strong></div></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="69">
    <div align="left"><strong><em>Rate</em></strong></div></td>
  </tr>
  <tr> 
    <td style="border-style: none" width="72"><%=company%>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="132"><%=no_shares%>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="121"><%=validity%>&nbsp;</td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="81"><%=Order_Type%>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="69"><%=rate%>&nbsp;</td>
  </tr>
</table>
                  </center>
                </div>
<pre>&nbsp; </pre>
<div align="center">
<pre>
		<b><font color="#800000" face="Georgia">At the rate of Rs <%=rate%> per share,
		the cost of the shares will be Rs <%=value%>,plus a commission of 2% i.e.,
		Rs <%=commission%> amounting the total transaction to Rs <%=total%></font></b></pre>

		<% session.setAttribute("commission", ""+commission); 
		session.setAttribute("shareValue", ""+value); 
		%>
</div>
<form name="preview" action="seller.jsp" method="post" >
  <div align="center"> 
    <input type="submit" name="b1" value="Submit Order" onSubmit="ConfirmOrder()" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
    <input type="button" name="b2" value="Modify Order" onClick="ModifyOrder()" style="font-style: italic; font-weight: bold; color: #800080; border-style: outset; border-width: 3">
  </div>
</form>
</body>
</html>