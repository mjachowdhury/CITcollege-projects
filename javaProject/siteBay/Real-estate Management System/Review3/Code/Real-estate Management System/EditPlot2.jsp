

<html>
<HEAD>
	<LINK href="styles.css" type="text/css" rel="stylesheet">

</HEAD>
<SCRIPT LANGUAGE="JavaScript">
<!--
function Validate()
{
	var PlotNum=0,OtherExp=0,RoadNum=0,SurNum=0,Extent=0,CostSqYard=0;
	var Boundaries="",Facing="",Status="";
	var Obj = document.PlotForm;
	PlotNum = Obj.PlotNum.value;
	if(PlotNum=="" || PlotNum==null) 
	{
		alert("Plot number is mandatory");
		Obj.PlotNum.focus();
		return false;
	}
return true;
}
//-->
</SCRIPT>
<BODY CLASS=SC>

<!-- To display Menu --Start -->
<script language="JavaScript1.2" src="coolfunctions.js"></script>
<script language="JavaScript1.2" src="coolmenus.js"></script>
<!-- To display Menu --End --><br><br><br>
<h3 align=Center>Edit Plot Information</h3>
<h5 align=Center>Please Enter Plot number </h5>

<%
int Auth = ((Integer)session.getAttribute("auth")).intValue();
if(Auth!=0){
	%><HR><H3 align=center>You are not authorized to access this page..</H3><HR><%
   return;
}
%>
		<form method=post name="PlotForm" action="EditPlot.jsp" onsubmit = "return Validate();">
		<table align=center>
		<tr>
			<td>Plot Number </td>
			<td><input type="text" name="PlotNum" size=5></td>
		</tr>
		<tr>
			<td><input type="submit" Value="Edit"></td>
			<td><input type="reset" Value="Clear"></td>
		</tr>
		
		</table>
		
		</form>
</BODY>

</html>
