<%@ page import="java.sql.*" %>
<%@ page import="DBcon.DataCon" %>
<html>
<body bgcolor="#E0E0E0">i<jsp:include page="Home1.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="ExecMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Attendencee Details</font></h1>
<%

		try{
    
		String query="select distinct(mm.Meeting_id) from meeting_master mm,meeting_desc md where mm.meeting_id=md.meeting_id and md.exec_id="+application.getAttribute("execid");
		System.out.println(query);
	String query1="select Meeting_id from meeting_master";
	Connection con =new DataCon().getConnection(getServletContext());
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select distinct(mm.Meeting_id) from meeting_master mm,meeting_desc md ");%>
<form action="AttendeeMaster">
<table><tr>
<td><b>Attendee Name</td><td><input type="text" name="TName"></td></tr>
<tr>
<td><b>Meeting Id</td><td><select name="TMeetingId">
<%while(rs.next()){%>
    <option><%=rs.getString(1)%></option>
<%}%></select></td></tr>
<tr>
<td><b>Address</td><td><input type="text" name="TAddress"></td></tr>
<tr>
<td><b>MailId</td><td><input type="text" name="TMailId"></td></tr>
<tr>
<td><b>Phone No</td><td><input type="text" name="TPhno"></td></tr>
<tr>
<td><b>Designition</td><td><input type="text" name="TDesg"></td></tr><tr></tr>
<tr>
<td colspan="2">&nbsp;&nbsp;<input type="Submit" value="INSERT" name="TYPE">&nbsp;&nbsp;<input type="Button" value="VIEW"  name="TYPE" onclick="javascript:location.href='Del_Attendee_Master.jsp?prefix=delete'"></td></tr></table>

</body>
<%}catch(Exception e){}%>

</html>



