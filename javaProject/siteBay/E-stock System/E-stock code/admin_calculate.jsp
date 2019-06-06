<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
<h1 align='center'>Money Collected in the form of commission: </h1>
<%!
Connection conn=null;
Statement stmt = null;
ResultSet rs=null;
int y=0;
double sales=0.0, buy=0.0, tot = 0.0; 
%>

<%


try
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
	stmt = conn.createStatement();

	String qry = "select sum(COMMISSION) from seller";
	rs = stmt.executeQuery(qry);
	if(rs.next())
		sales = rs.getDouble(1);


 qry = "select sum(COMMISSION) from buyer";
	rs = stmt.executeQuery(qry);
	if(rs.next())
		buy = rs.getDouble(1);

 tot = sales + buy;
}
catch(Exception e)
{
	out.println(e.getMessage());
}
%>


<table align ='center' border=1>
<tr><th></th><th></th></tr>
<tr><td>Commission from sales done by customers:</td><td><%=sales%></td></tr>
<tr><td>Commission from purchases done by customers:</td><td><%=buy%></td></tr>
<tr><td>Total Commission is:</td><td><%=tot%></td></tr>

</table>
<font size=1 color="#000000"><a  href="ViewSales.jsp" align='center'><font size=5 color="#000000" align='center'>back</font></a></font>

<%-- <html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">

<head>
<script language="javascript">
function fucn_calculate()
{
	var str_symbol=document.frm_calc.symbol.value;
	var str_buy_rate=document.frm_calc.buy_rate.value;
	var str_sell_rate=document.frm_calc.sale_rate.value;
	var str_no_shares=document.frm_calc.no_shares.value;
		
	
	var int_symbol=parseInt(str_symbol);
	var int_buy_rate=parseInt(str_buy_rate);
	var int_sell_rate=parseInt(str_sell_rate);
	var int_no_shares=parseInt(str_no_shares);
	var profit_loss=(int_no_shares*(int_sell_rate - int_buy_rate))-(int_no_shares*int_buy_rate*0.02);
	
	document.frm_calc.result.value=profit_loss;
	document.frm_calc.purchase_value.value= (int_no_shares)*(int_buy_rate);
	document.frm_calc.sale_value.value= (int_no_shares)*(int_sell_rate);
	document.frm_calc.commission.value= (int_no_shares*int_buy_rate)*0.02;



}
</script>
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<link rel="File-List" href="admin_calculate_files/filelist.xml">

<title>New Page 0</title>
<!--[if !mso]>
<style>
v\:*         { behavior: url(#default#VML) }
o\:*         { behavior: url(#default#VML) }
.shape       { behavior: url(#default#VML) }
</style>
<![endif]--><!--[if gte mso 9]>
<xml><o:shapedefaults v:ext="edit" spidmax="1027"/>
</xml><![endif]-->
</head>

<body bgcolor="oldlace">

<p align="center"><b><font face="Bookman Old Style" size="4" color="#0000FF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
Sale Informations are...</font></b></p>

<p align="left">&nbsp;</p>

<div align="center">
  <center>
  <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
    <tr>
      <td width="421" height="322">
      <form name="frm_calc" method="POST" action="admin_insert_portfolio.jsp">
         <p><b><font face="Garamond" color="#008000">&nbsp;Symbol </font>
         <font face="Garamond" color="#00FF00">&nbsp;</font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input type="text" name="symbol" value="<%=session.getValue("symbol_sell")%>"size="10"></p>
        <p><b><font face="Garamond" color="#008000">PurchaseRate:</font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="buy_rate" value="<%=session.getValue("rate_buy")%>"size="10"></p>
        <p><b><font face="Garamond" color="#008000">&nbsp;SaleRate:</font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="sale_rate" value="<%=session.getValue("rate_sell")%>"size="10" size="20"></p>
        <p><b><font face="Garamond" color="#008000">Number Of Shares</font></b>:&nbsp;
        <input type="text" name="no_shares" value="<%=session.getValue("no_shr_sell")%>"size="9" size="20"><input type="button" value="Calculate" onClick="fucn_calculate()" style="color: #800080; font-family: Bookman Old Style; font-style: italic; font-weight: bold; border-style: outset; border-width: 3"></p>
        <p align="left"><i><b><font face="Garamond" color="#FF0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font></b></i></p>
        <p align="left"><i><b><font face="Garamond" color="#FF0000">The 
        following are the calculated values..</font></b></i></p>
        
        <p align="left">&nbsp;</p>
		<p><b><font face="Garamond" color="#008000">Purchase  Value: </font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="purchase_value" size="12"></p>
		<p><b><font face="Garamond" color="#008000">Sale Value: </font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="sale_value" size="12"></p>
		<p><b><font face="Garamond" color="#008000">Commission:</font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="commission" size="12"></p>
        <p><!--[if gte vml 1]><v:rect id="_x0000_s1026"
 style='position:absolute;left:106.5pt;top:255.75pt;width:287.25pt;height:188.25pt;
 z-index:-1' fillcolor="silver pure"/><![endif]--><![if !vml]><span
style='mso-ignore:vglayout;position:absolute;z-index:-1;left:141px;top:340px;
width:385px;height:253px'><img width=385 height=253
src="admin_calculate_files/image001.gif" v:shapes="_x0000_s1026"></span><![endif]><!--[if gte vml 1]><v:rect
 id="_x0000_s1025" style='position:absolute;left:109.5pt;top:42.75pt;width:285.75pt;
 height:151.5pt;z-index:-1' fillcolor="silver pure"/><![endif]--><![if !vml]><span
style='mso-ignore:vglayout;position:absolute;z-index:-1;left:145px;top:56px;
width:383px;height:204px'><img width=383 height=204
src="admin_calculate_files/image002.gif" v:shapes="_x0000_s1025"></span><![endif]><b><font face="Garamond" color="#008000">Profit/Loss(in Rs)</font></b>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="result"  size="12"></p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" value="Submit" name="B1" style="font-family: Bookman Old Style; color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
        <input type="reset" value="Reset" name="B2" style="color: #800080; font-family: Bookman Old Style; font-style: italic; font-weight: bold; border-style: outset; border-width: 3"></p>
        &nbsp;</form>
      <p>&nbsp;</td>
    </tr>
  </table>
  </center>
</div>

</body>

</html>

--%>