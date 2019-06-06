<%@ page import="java.sql.*,DBcon.DataCon,convertdate.ConvertDate" %>
<html>
<script language="javascript" src="ts_picker.js"></script> 
<body bgcolor="#E0E0E0"><jsp:include page="Home1.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="ExecMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Events Details</font></h1>
<form name="apply">
<%
        try{
			String execid=(String)application.getAttribute("execid");
Connection con =new DataCon().getConnection(getServletContext());
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select em.evtid,em.evtname,ed.evtdesc,em.venue,em.curDate,em.address,em.location,em.city,em.phno from event_master em,event_desc ed where em.evtid=ed.evtid and ed.exec_id="+execid);

ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();

%>
<table > <tr>

<th bgcolor=green colSpan=30><font color="white">Event ID</font></th>
<th bgcolor=green colSpan=30><font color="white">Event Name</font></th>
<th bgcolor=green colSpan=30><font color="white">Event Description</font></th>
<th bgcolor=green colSpan=30><font color="white">Venue</font></th>
<th bgcolor=green colSpan=30><font color="white">Date</font></th>
<th bgcolor=green colSpan=30><font color="white">Address</font></th>
<th bgcolor=green colSpan=30><font color="white">Location</font></th>
<th bgcolor=green colSpan=30><font color="white">City</font></th>
<th bgcolor=green colSpan=30><font color="white">Phone No</font></th>
<%
//display data
while(rs.next())
{
	out.println("<tr>");
for(int i=1;i<=count;i++)
	{
if(i == 10)
		i=i+1;
	Object obj = rs.getObject(i);
	if(obj == null)
		obj =new String( "N A");
	out.println("<th bgcolor=White colSpan=30>" + obj.toString() + "</th>");
}//for
	out.println("</tr>");

}//while



out.println("</tr></table>");
}
		catch(Exception e)
			{e.printStackTrace();
	//out.println(e.toString());		
	}
%>

