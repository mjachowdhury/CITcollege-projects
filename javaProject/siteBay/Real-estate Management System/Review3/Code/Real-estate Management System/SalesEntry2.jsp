

<html>
<HEAD>
	<LINK href="styles.css" type="text/css" rel="stylesheet">

</HEAD>
<script language="JavaScript" src="Images/date-picker.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function Validate()
{
	var PlotNum=0,SaleValue=0,SalesDate=0,Advance=0,Balance=0;
	var InstOpt="",SoldTo="";
	var Obj = document.SalesForm;
	PlotNum = Obj.PlotNum.value;
	SaleValue = Obj.SaleValue.value;
	SoldTo = Obj.SoldTo.value;
	SalesDate = Obj.SalesDate.value;
	Advance = Obj.Advance.value;
	Balance = Obj.Balance.value;
	InstOpt = Obj.InstOpt.options[Obj.InstOpt.options.selectedIndex].text;
	if(PlotNum=="" || PlotNum==null) 
	{
		alert("Plot number is mandatory");
		Obj.PlotNum.focus();
		return false;
	}
	if(SaleValue=="" || SaleValue==null) 
	{
		alert("Sale Value is mandatory");
		Obj.SaleValue.focus();
		return false;
	}
	if(SalesDate=="" || SalesDate==null) 
	{
		alert("Date is set to Current date");
		dt = new Date();
		y = dt.getFullYear();
		m = dt.getMonth()+1;
		d = dt.getDate();
		m = (m<10)?"0"+m:m;
		d = (d<10)?"0"+d:d;
		Obj.SalesDate.value = y+"-"+m+"-"+d;
	}
	if(SoldTo=="" || SoldTo==null) 
	{
		alert("Sold to is mandatory");
		Obj.SoldTo.focus();
		return false;
	}
	if(Advance=="" || Advance==null) 
	{
		Obj.Advance.value = 0;
	}
	if(Balance=="" || Balance==null) 
	{
		Obj.Balance.value=SaleValue-Advance
	}
	if(InstOpt=="" || InstOpt==null) 
	{
		Obj.InstOpt.value = "No";
	}

return true;
}
function compBalance()
{
	var Obj = document.SalesForm;
	SaleValue = Obj.SaleValue.value;
	Advance = Obj.Advance.value;
	Obj.Balance.value=SaleValue-Advance
}
//-->
</SCRIPT>
<BODY class=SC>
<!-- To display Menu --Start -->
<script language="JavaScript1.2" src="coolfunctions.js"></script>
<script language="JavaScript1.2" src="coolmenus.js"></script>
<!-- To display Menu --End --><br><br><br>
<h3 align=Center>Sales Entry</h3>
<%
int Auth = ((Integer)session.getAttribute("auth")).intValue();
if(Auth!=0){
	%><HR><H3 align=center>You are not authorized to access this page..</H3><HR><%
   return;
}
%>
		<form method=post name="SalesForm" action="SalesEntry.jsp" onsubmit = "return Validate();">
		<table align=center>
		<tr>
			<td>Plot Number </td>
			<td><input type="text" name="PlotNum"></td>
		</tr>
		<tr>
			<td>Sale Value </td>
			<td><input type="text" name="SaleValue"></td>
		</tr>
		<tr>
			<td>Date of Sale </td>
			<td>
				<input type="text" name="SalesDate">
				<a href="javascript:show_calendar('SalesForm.SalesDate');" onmouseover="window.status='Date Picker';return true;" onmouseout="window.status='';return true;" VALUE="SalesForm.datebox.value">
				<img src="Images/show-calendar.gif" width=24 height=22 border=0></a>
			(YYYY-MM-DD)
			</td>
		</tr>
		<tr>
			<td>Sold To </td>
			<td><input type="text" name="SoldTo"></td>
		</tr>
		<tr>
			<td>Advance</td>
			<td><input type="text" name="Advance" onchange="compBalance()"></td>
		</tr>
		<tr>
			<td>Balance</td>
			<td><input type="text" name="Balance" disabled value=""></td>
		</tr>
		<tr>
			<td>Installment Option</td>
			<td>
				<select name="InstOpt">
					<option value="No">----</option>
					<option value="Yes">Yes</option>
					<option value="No">No</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><input type="submit" Value="Store Data"></td>
			<td><input type="reset" Value="Clear"></td>
		</tr>
		
		</table>
		
		</form>
</BODY>

</html>
