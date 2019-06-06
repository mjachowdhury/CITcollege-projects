<%@ page import="java.sql.*,DBcon.DataCon,convertdate.ConvertDate" %>

<html>
<script language="javascript" src="ts_picker.js"></script> 
<body bgcolor="#E0E0E0" background="conf1.jpg"><jsp:include page="Home1.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="ExecMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Meetings Report</font></h1>
<form name="apply">
<%

		try{
    
		 String query="select mm.Meeting_id,mm.meetingdesc,md.agenda,mm.curdate,mm.time,mm.address,mm.location,mm.city,mm.State,mm.phno from meeting_master mm,meeting_desc md where mm.meeting_id=md.meeting_id and md.exec_id="+application.getAttribute("execid");
	     System.out.println("select mm.Meeting_id,mm.meetingdesc,md.agenda,mm.curdate,mm.time,mm.address,mm.location,mm.city,mm.State,mm.phno from meeting_master mm,meeting_desc md where mm.meeting_id=md.meeting_id and md.exec_id="+application.getAttribute("execid"));
	Connection con =new DataCon().getConnection(getServletContext());
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(query);

ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();

%>
<table > <tr>
<table > <tr>
<th bgcolor=green colSpan=30><font color="white">Meeting ID</font></th>
<th bgcolor=green colSpan=30><font color="white">Meeting Description</font></th>
<th bgcolor=green colSpan=30><font color="white">Meeting Agenda</font></th>
<th bgcolor=green colSpan=30><font color="white">Date</font></th>
<th bgcolor=green colSpan=30><font color="white">Time</font></th>
<th bgcolor=green colSpan=30><font color="white">Address</font></th>
<th bgcolor=green colSpan=30><font color="white">Location</font></th>
<th bgcolor=green colSpan=30><font color="white">City</font></th>
<th bgcolor=green colSpan=30><font color="white">State</font></th>
<th bgcolor=green colSpan=30><font color="white">Phone No</font></th>
<%
	//display data
while(rs.next())
{
	out.println("<tr>");
for(int i=1;i<=count;i++)
	{
	String s="";
if(i == 11)
		i=i+1;
	Object obj = rs.getObject(i);
	if(obj == null)
		obj =new String( "N A");
	if(i==1)
        out.println("<th bgcolor=White colSpan=30><a href='Minutes.jsp?prefix="+obj.toString()+"'>" + obj.toString() + "</a></th>");
    else
		out.println("<th bgcolor=White colSpan=30>" + obj.toString() + "</th>");
}//for
	out.println("</tr>");

}//while



out.println("</tr></table>");

}
		catch(Exception e)
			{
		
	}
%>


</body>
</html>