

<html>
<HEAD>
	<LINK href="styles.css" type="text/css" rel="stylesheet">

</HEAD>
<SCRIPT LANGUAGE="JavaScript">
<!--
function Validate()
{
	var AppNo=0,NoFlats=0,PlotNo=0,Name="",Address="";
	var Obj = document.AppForm;
	AppNo = Obj.AppNo.value;
	NoFlats = Obj.NoFlats.value;
	PlotNo = Obj.PlotNo.value;
	Name = Obj.Name.value;
	Address = Obj.Address.value;
	//alert(Status);
	if(AppNo=="" || AppNo==null) 
	{
		alert("Appartment number is mandatory");
		Obj.AppNo.focus();
		return false;
	}
	if(NoFlats=="" || NoFlats==null) 
	{
		alert("Number of Flats is mandatory");
		Obj.PlotNo.focus();
		return false;
	}
	if(PlotNo=="" || PlotNo==null) 
	{
		alert("Plot Number is mandatory");
		Obj.PlotNo.focus();
		return false;
	}
	if(Name=="" || Name==null) 
	{
		alert("Appartment Name is mandatory");
		Obj.PlotNo.focus();
		return false;
	}
	if(Address=="" || Address==null) 
	{
		alert("Address is mandatory");
		Obj.Address.focus();
		return false;
	}
return true;
}
function fnEmpPopUp(URL,h,w)
	{ 
		var strTitle ='Details';
		var strUrl = URL;
		var strOptions= 'menubar=yes,toolbar=yes,scrollbars=Yes,status=yes,resizable=yes,height='+h+',width='+w+',left=300,top=0';
		window.open(strUrl,strTitle,strOptions).focus();
	} 
//-->
</SCRIPT>
<BODY CLASS=SC>

<!-- To display Menu --Start -->
<script language="JavaScript1.2" src="coolfunctions.js"></script>
<script language="JavaScript1.2" src="coolmenus.js"></script>
<!-- To display Menu --End --><br><br><br>
<h3 align=Center>Add new Appartment</h3>
		<%
int Auth = ((Integer)session.getAttribute("auth")).intValue();
if(Auth!=0){
	%><HR><H3 align=center>You are not authorized to access this page..</H3><HR><%
   return;
}
%>
		<form method=post name="AppForm" action="AddAppartment.jsp" onsubmit = "return Validate();">
		<table align=center>
		<tr>
			<td>Appartment Number </td>
			<td><input type="text" name="AppNo"></td>
		</tr>
		<tr>
			<td>Plot Number </td>
			<td><input type="text" name="PlotNo" ></td><td><input type="button" value="Show Plots" onclick="fnEmpPopUp('ListPlots.jsp',300,300)"></td>
		</tr>
		<tr>
			<td>Name </td>
			<td><input type="text" name="Name"></td>
		</tr>
		<tr>
			<td>Address </td>
			<td><input type="text" name="Address"></td>
		</tr>
		<tr>
			<td>Number of Flats</td>
			<td><input type="text" name="NoFlats"></td>
		</tr>
		<tr>
			<td><input type="submit" Value="Save"></td>
			<td><input type="reset" Value="Clear"></td>
		</tr>
		
		</table>
		
		</form>
</BODY>

</html>
