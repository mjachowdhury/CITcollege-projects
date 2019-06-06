<%@ page import="java.sql.*,DBcon.DataCon,convertdate.ConvertDate" %>


<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home1.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="ExecMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Minutes Details</font></h1>  
		 
	
	<form action="Minute">
<table>
<tr>
<td><b>Meeting Id</td><td><input type="text" name="TMeetingId" value="<%=request.getParameter("prefix")%>" readonly>
</td></tr>
<tr>
<td><b>Minutes</td><td><input type="text" name="TMinutes"></td></tr>
<tr>
<td><b>Noter Name</td><td><input type="text" name="TN_Name"></td></tr>
<tr>
<td><b>Start Time</td><td><input type="text" name="TSt_Time"></td></tr>
<tr>
<td><b>End Time</td><td><input type="text" name="TEnd_Time"></td></tr>
<tr></tr>
<tr>
<td colspan="2">&nbsp;&nbsp;<input type="Submit" value="INSERT" name="TYPE"></td></tr></table>

</body>


</html>

