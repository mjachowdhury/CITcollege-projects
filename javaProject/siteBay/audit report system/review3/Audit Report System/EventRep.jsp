<%@ page import="java.sql.*,DBcon.DataCon,convertdate.ConvertDate" %>
<html>
<script language="javascript" src="ts_picker.js"></script> 
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Events Report</font></h1>
<form name="apply">
<%

		try{
     String query=request.getParameter("query");
	 if(query.equals("All"))
		 query="select * from event_master order by curdate";
	 else{
         %> 
		<center> <table><tr>
                    <td ><b>Select Date </td>
                    <td align="left"><input name="fromdate" type="text" id="fromdate" readonly><a href="javascript:show_calendar('document.apply.fromdate', document.apply.fromdate.value);">
<img src="cal.gif" width="18" height="18" border="0"></a>&nbsp;&nbsp;<input type="button" value="Get" onclick="fun()"></td>
                  </tr></table>
		 <%
			
          if(request.getParameter("date")!=null){
             String dates=new ConvertDate().DateConvert(request.getParameter("date"));
			 System.out.println("the dates are=="+dates);
			 query="select * from event_master where curdate='"+dates+"'";
		  }
	 }
	Connection con =new DataCon().getConnection(getServletContext());
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(query);

ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();

%>
<table > <tr>
<table > <tr>
<th bgcolor=green colSpan=30><font color="white">Event ID</font></th>
<th bgcolor=green colSpan=30><font color="white">Event Name</font></th>
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
if(i == 9)
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
			{
	//out.println(e.toString());		
	}
%>
</tr>
</table>
</tr>
</table>
</center>
</form>
