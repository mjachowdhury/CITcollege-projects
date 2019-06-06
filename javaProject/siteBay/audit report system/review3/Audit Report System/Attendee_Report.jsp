<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Attendees Details</font></h1>
<form name="del" action="AttendeeMaster">
<%
   String prefix=request.getParameter("prefix");
%>
   <table>
   <tr><td><b>Select Meeting ID</td><td><select name="TMeetingId1" onchange="javascript:var v=document.del.TMeetingId1.value;location.href='Attendee_Report.jsp?id='+v+'&prefix=<%=prefix%>'">
   <option>--Select--</option>
   
<% 

int evtno=0;
try{
   Connection con=new DataCon().getConnection(getServletContext());
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery("select Meeting_id from Meeting_master");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select></td></tr>
<%  if(request.getParameter("id")!=null){
	String query="select Meetingid,name,desg,address,phno,mailid from Attendee_master where Meetingid="+request.getParameter("id");
	rs = st.executeQuery(query);

ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();

%>

<table > <tr>
<table > <tr>
<th bgcolor=green colSpan=30><font color="white">Meeting ID</font></th>
<th bgcolor=green colSpan=30><font color="white">Name</font></th>
<th bgcolor=green colSpan=30><font color="white">Designation</font></th>
<th bgcolor=green colSpan=30><font color="white">Address</font></th>
<th bgcolor=green colSpan=30><font color="white">Phone No </font></th>
<th bgcolor=green colSpan=30><font color="white">Mail ID</font></th>

<%
while(rs.next())
{
out.println("<tr>");
for(int i=1;i<=count;i++)
	{

if(i == 7)
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
}
		catch(Exception e)
			{
			e.printStackTrace();
	}

%>
</body>

</html>


