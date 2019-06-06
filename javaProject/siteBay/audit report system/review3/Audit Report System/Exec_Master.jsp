<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0" background="conf1.jpg"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Executive Details</font></h1>

   <form action="ExecMaster" name="eve">
   <table>
   <tr><td><b></td><td><input type="hidden" name="TExecId" readonly></td></tr>
   <tr><td><b>Executive Name</td><td><input type="text" name="TExecName" ></td></tr>
   <tr><td><b>Location</td><td><input type="text" name="TLocation"></td></tr>
   <tr><td><b>Mobile No</td><td><input type="text" name="TMobileNo"></td></tr>
   <tr></tr><tr>
<td colspan="2">&nbsp;&nbsp;<input type="Submit" value="INSERT" name="TYPE">&nbsp;&nbsp;<input type="Button" value="DELETE"  name="TYPE" onclick="javascript:location.href='Del_exec_Master.jsp?prefix=delete'">&nbsp;&nbsp;<input type="Button" value="MODIFY"  name="TYPE" onclick="javascript:location.href='Del_exec_Master.jsp?prefix=modify'">&nbsp;&nbsp;</td></tr></table></form>
</body>

</html>
