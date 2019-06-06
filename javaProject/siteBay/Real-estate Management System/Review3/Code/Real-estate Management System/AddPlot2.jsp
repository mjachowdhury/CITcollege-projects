

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
	OtherExp = Obj.OtherExp.value;
	RoadNum = Obj.RoadNum.value;
	SurNum = Obj.SurNum.value;
	Extent = Obj.Extent.value;
	CostSqYard = Obj.CostSqYard.value;
	Boundaries = Obj.Boundaries.value;
	Facing = Obj.Facing.options[Obj.Facing.options.selectedIndex].text;
	//alert(Facing);
	Status = Obj.Status.options[Obj.Status.options.selectedIndex].text;
	//alert(Status);
	if(PlotNum=="" || PlotNum==null) 
	{
		alert("Plot number is mandatory");
		Obj.PlotNum.focus();
		return false;
	}
	if(OtherExp=="" || OtherExp==null) 
	{
		Obj.OtherExp.value=0;
	}
	if(RoadNum=="" || RoadNum==null) 
	{
		alert("Road number is mandatory");
		Obj.RoadNum.focus();
		return false;
	}
	if(SurNum=="" || SurNum==null) 
	{
		Obj.SurNum.value = 0;
	}
	if(Extent=="" || Extent==null) 
	{
		alert("Extent is mandatory");
		Obj.Extent.focus();
		return false;
	}
	if(CostSqYard=="" || CostSqYard==null) 
	{
		alert("Cost per Square Yard is mandatory");
		Obj.CostSqYard.focus();
		return false;
	}
	if(Boundaries=="" || Boundaries==null) 
	{
		Obj.Boundaries.value = "Not Available";
	}

	if(Facing=="----") 
	{
		Obj.Facing.value = "Not Available";
	}

	if(Status=="----") 
	{
		Obj.Status.value = "Vacant";
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
<%
int Auth = ((Integer)session.getAttribute("auth")).intValue();
if(Auth!=0){
	%><HR><H3 align=center>You are not authorized to access this page..</H3><HR><%
   return;
}
%>
<h3 align=Center>Add new Plot</h3>
		<form method=post name="PlotForm" action="AddPlot.jsp" onsubmit = "return Validate();">
		<table align=center border=20>
		<tr>
			<td><b>Plot Number</b> </td>
			<td><input type="text" name="PlotNum"></td>
		</tr>
		<tr>
			<td><b>Road Number</b> </td>
			<td><input type="text" name="RoadNum"></td>
		</tr>
		<tr>
			<td><b>Survey Number</b> </td>
			<td><input type="text" name="SurNum"></td>
		</tr>
		<tr>
			<td><b>Cost per Sq Yard</b> </td>
			<td><input type="text" name="CostSqYard"></td>
		</tr>
		<tr>
			<td><b>Other Expences</b></td>
			<td><input type="text" name="OtherExp"></td>
		</tr>
		<tr>
			<td><b>Extent</b></td>
			<td><input type="text" name="Extent"></td>
		</tr>
		<tr>
			<td><b>Boundaries</b></td>
			<td><input type="text" name="Boundaries"></td>
		</tr>
		<tr>
			<td><b>Facing</b></td>
			<td>
				<select name="Facing">
					<option value="NA">----</option>
					<option value="East"><b>East</b></option>
					<option value="West"><b>West</b></option>
				</select>
			</td>
		</tr>

		<tr>
			<td><b>Status</b></td>
			<td>
				<select name="Status">
					<option value="NA">----</option>
					<option value="Vacant"><b>Vacant</b></option>
					<option value="Reserved"><b>Reserved</b></option>
					<option value="Sold"><b>Sold</b></option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right"><input type="submit" Value="Save" ></td>
			<td><input type="reset" Value="Clear"></td>
		</tr>
		
		</table>
		
		</form>
</BODY>

</html>
