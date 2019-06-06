<%@ page  errorPage = "ErrorPage.jsp" %>

<html>
<head>
<title>SELL SHARES</title>
<script language="javascript">
//for validating the input values
function func_subm()
{
if(document.frm_sell.cid.value=="")
{
alert("Please type your CustomerId");
document.frm_sell.cid.focus();
}
else if(document.frm_sell.company.value=="")
{
alert("Please type the name of the company");
document.frm_sell.company.focus();
}
else if(document.frm_sell.no_shares.value=="")
{
alert("Please type the number of shares");
document.frm_sell.no_shares.focus();
}
else 
{
var nshares=document.frm_sell.no_shares.value
if(isNaN(nshares))
{
alert('The number of shares entry cannot have alphabets or special characters.')
document.frm_sell.no_shares.focus();
}
}

document.frm_sell.action="preview.jsp";
document.frm_sell.submit();
document.frm_sell.method="post";
}
</script>
</head>
<body bgcolor="oldlace" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!—Creating the Sell Shares page. -->
<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
<tr > 
<td> 
<div align="center"><font face="Bookman Old Style" size="3" 
color="#008000"><b><font size="2">SELL         SHARES</font></b></font></div>
</td>
</tr>
</table>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" bgcolor="oldlace">
<tr>
<td>
<form name="frm_sell">
      <br>
      <div align="center">
        <center>
            <p><font color="#0000FF" size="3" face="Georgia, Times New Roman, Times, serif">Dear</font> 
              <font color="#800040" size="4"><%=(String)session.getValue("user_name")%></font>,</p>
            <p><font color="#FF8040" size="-1" face="Georgia, Times New Roman, Times, serif"><strong>Please 
              ensure that the Share is in your possession. </strong></font></p>
            <table width="329" border="0" cellspacing="0" cellpadding="0" height="224" bgcolor="#808080" style="border-collapse: collapse" bordercolor="#111111">
      <tr> 
<td width="206" height="1" align="right"><b>
<font face="Garamond" size="2" color="#00FF00"> CustomerID</font></b></td>
            <td colspan="2" height="1" width="173"> 
<input type="text" name="cid" disabled value="<%=(String)session.getValue("CUSTID")%>" size="20">
            </td>
      </tr>
      <tr> 
<td width="206" height="1" align="right"><b>
<font face="Garamond" size="2" color="#00FF00">
Symbol</font></b></td>
            <td colspan="2" height="1" width="173"> 
            <input type="text" name="company" size="20">
            </td>
</tr>
      <tr> 
<td width="206" height="8" align="right"><b>
<font face="Garamond" size="2" color="#00FF00"> No.Of Shares</font></b></td>
            <td colspan="2" height="8" width="173"> 
<input type="text"name="no_shares" size="20">
            </td>
      </tr>
      <tr> 
<td height="6" width="206" align="right"><b>
<font size="2" face="Garamond" color="#00FF00">Term Validity</font></b></td>
            <td height="6" colspan="2" width="173"> 
            <select name="validity">
            <option value="Day Order">Day Order</option>
            <option value="Good Till Cancelled">Good Till Cancelled</option>
			</select>
            </td>
</tr>
<tr> 
<td height="6" width="206" align="right"><b>
<font size="2" face="Garamond" color="#00FF00">Order Type</font></b></td>
            <td height="6" colspan="2" width="173"> 
            <select name="Order_Type">
            <option value="Market">Market</option>
            <option value="Stop">Stop</option>
			<option value="Limit">Limit</option>
			</select>
            </td>
</tr>

       <%--
      <tr> 
            <td width="206" height="4" align="right"><b>
            <font color="#00FF00" face="Garamond">Rate</font></b></td> 
            <td colspan="2" height="4" width="173"><input type="text" name="rate" size="20"></td>
</tr> --%>
      <tr> 
            <td height="13" width="206"> 
            <div align="center"> 
<input name="PreviewOrder" type="button" onClick="func_subm()" value="PreviewOrder" style="float: right; color: #800080; font-style: italic; font-weight: bold; border: 3px inset #FF0000"></div>
            </td>
            <td width="170" height="13">
            <div align="center"> 
<input  type="reset" name="Cancel" value="Cancel" style="color: #800080; font-style: italic; font-weight: bold; border: 3px inset #FF0000"></div>
            </td>
<td width="3" height="13">&nbsp;</td>
</tr>
      </table>
        </center>
      </div>
</form>
</td>
</tr>
</table>
<hr color=#c0003b size=1>
<table border=0 cellpadding=0 cellspacing=0 width="100%" style="border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid black; background-color: silver" fpstyle="9,011111100">
<tbody> 
<tr> 
<td align=left valign=center height="35" style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid black; background-color: maroon"> 
<p align=center><font face="Bookman Old Style" size="2"> <font color="#008000">|
</font> <a class=copyright href="home.htm"><font color="#008000">HOME</font></a><font color="#008000"> 
&nbsp;| </font> <a class=copyright href="mailto:info@Share Business Portal">
<font color="#008000">Contact us</font></a><font color="#008000"> 
&nbsp;| </font> <a class=copyright href=Privacy.htm><font color="#008000">Privacy Policy</font></a><font color="#008000"> 
&nbsp;| </font> <a class=copyright       href="http://www.sebi.gov.in/">
<font color="#008000">SEBI</font></a><font color="#008000"> 
&nbsp;| </font> <a class=copyright       href="http://www.nse-india.com/">
<font color="#008000">NSE</font></a><font color="#008000"> | <br>Share Business Portal - Your currency for online trading &amp; e-broking in        India
</font> </font></p>
</td>
</tr>
</tbody> 
</table>
</body>
</html>