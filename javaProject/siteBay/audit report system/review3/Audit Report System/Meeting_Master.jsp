<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0" background="conf1.jpg"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Meeting Details</font></h1>
<form action="MeetingMaster">
<table><tr>
<td><b></td><td><input type="hidden" name="TMeetingid"></td></tr>
<tr>
<td><b>Meeting Description</td><td><input type="text" name="TMeetingDesc"></td></tr>
<tr>
<td><b>Time</td><td><input type="text" name="TTime"></td></tr>
<tr>
<td><b>Date</td><td><input type="text" name="TDate"></td><td>(DD-MMM-YYYY)</td></tr>
<tr>
<td><b>Address</td><td><input type="text" name="TAddress"></td></tr>
<tr>
<td><b>Location</td><td><input type="text" name="TLocation"></td></tr>
<tr>
<td><b>City</td><td><input type="text" name="TCity"></td></tr>
<tr>
<td><b>Phone No</td><td><input type="text" name="TPhno"></td></tr>
<tr>
<td><b>State</td><td><input type="text" name="TState"></td></tr>

<tr></tr>
 <tr>
<td colspan="2">&nbsp;&nbsp;<input type="Submit" value="INSERT" name="TYPE">&nbsp;&nbsp;<input type="Button" value="DELETE"  name="TYPE" onclick="javascript:location.href='Del_Meeting_Master.jsp?prefix=delete'">&nbsp;&nbsp;<input type="Button" value="Modify"  name="TYPE" onclick="javascript:location.href='Del_Meeting_Master.jsp?prefix=modify'">&nbsp;&nbsp;</td></tr></table>
</form>
</body>
</html>

