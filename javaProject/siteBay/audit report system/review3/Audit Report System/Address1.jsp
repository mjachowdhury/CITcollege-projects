
<html>
<body bgcolor="#E0E0E0" ><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Company Details</font></h1>
<form action="Addres">
<table><tr>
<td><b></td><td><input type="hidden" name="TAddRefNo"></td></tr>
<tr>
<td><b>Company Name</td><td><input type="text" name="TCompName"></td><td>&nbsp;&nbsp;</td><td><b>Phone1</td><td><input type="text" name="TPhno1"></td><td>&nbsp;&nbsp;</td><td><b>Contact Person</td><td><input type="text" name="TContactPerson"></td></tr>
<tr>
<td><b>Location</td><td><input type="text" name="TLocation"></td><td>&nbsp;&nbsp;</td><td><b>Phone2</td><td><input type="text" name="TPhno2"></td><td>&nbsp;&nbsp;</td><td><b>Email ID1</td><td><input type="text" name="TEmailId1"></td></tr>
<tr>
<td><b>City</td><td><input type="text" name="TCity"></td><td>&nbsp;&nbsp;</td><td><b>Mobile</td><td><input type="text" name="TMobile"></td><td>&nbsp;&nbsp;</td><td><b>Email ID2</td><td><input type="text" name="TEmailId2"></td></tr>
<tr>
<td><b>State</td><td><input type="text" name="TState"></td><td>&nbsp;&nbsp;</td><td><b></td><td></td><td>&nbsp;&nbsp;</td><td><b>Others</td><td><input type="text" name="TOthers"></td></tr>
<tr>
<td><b>Country</td><td><input type="text" name="TCountry"></td><td>&nbsp;&nbsp;</td><td><b></td><td></td><td>&nbsp;&nbsp;</td><td><b></td><td></td></tr><tr></tr>
 <tr>
<td colspan="8" align="Center">&nbsp;&nbsp;<input type="Submit" value="INSERT" name="TYPE">&nbsp;&nbsp;<input type="Button" value="DELETE"  name="TYPE" onclick="javascript:location.href='Del_Address_Master.jsp?prefix=delete'">&nbsp;&nbsp;<input type="Button" value="MODIFY"  name="TYPE" onclick="javascript:location.href='Del_Address_Master.jsp?prefix=modify'">&nbsp;&nbsp;</td></tr></table>
</body>


</html>

