<%@ page import="java.sql.*,DBcon.DataCon,convertdate.ConvertDate" %>


<html>
<body bgcolor="#E0E0E0" background="conf1.jpg"><jsp:include page="Home1.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="ExecMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Event Feedback Details</font></h1>
<%

		try{
    
		 String query="select distinct(mm.evtid) from event_master mm,event_desc md where mm.evtid=md.evtidand md.exec_id="+application.getAttribute("execid");
	System.out.println(query);
	Connection con =new DataCon().getConnection(getServletContext());
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select distinct(mm.evtid) from event_master mm,event_desc md ");%>
<form action="EventFeedback">
<table><tr>
<td><b>Name</td><td><input type="text" name="TFname"></td></tr>
<tr>
<td><b>Event Id</td><td><select name="TEvtId">
<%while(rs.next()){%>
    <option><%=rs.getString(1)%></option>
<%}%></select></td></tr>
<tr>
<td><b>Feedback</td><td><input type="text" name="TFeedback"></td></tr>
<tr>
<td><b>MailId</td><td><input type="text" name="TMailId"></td></tr>
<tr>
<td><b>Phone No</td><td><input type="text" name="TContNo"></td></tr>
<tr>
<td><b>Address</td><td><input type="text" name="TAddress"></td></tr><tr></tr>
<tr>
<td colspan="2">&nbsp;&nbsp;<input type="Submit" value="INSERT" name="TYPE">&nbsp;&nbsp;<input type="Button" value="VIEW"  name="TYPE" onclick="javascript:location.href='Del_Feedback_Master.jsp?prefix=delete'"></td></tr></table>

</body>
<%}catch(Exception e){}%>

</html>



