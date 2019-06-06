<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>

<%@ page import="java.sql.*" %>
<%

		try{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con = DriverManager.getConnection("Jdbc:Odbc:JDSN","interact","interact");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select meeting_master.meetingid,meetingdesc,time,location,address,city,phno,state,agenda from meeting_master,meeting_desc where meeting_master.meetingid="+Integer.parseInt(request.getParameter("MID")));

ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();

out.println("<center><h2>Meeting Details</h2></center>");
out.println("<table > <tr>");

//display data
while(rs.next())
{
	
for(int i=1;i<=count;i++)
	{
	out.println("<tr>");
	Object obj = rs.getObject(i);
	if(obj == null)
		obj =new String( "N A");
	out.println("<th bgcolor=gray colSpan=60>" + rsd.getColumnName(i) + "</th>");
	out.println("<th bgcolor=gray colSpan=40>" + obj.toString() + "</th>");
	out.println("</tr>");
}//for


}//while



out.println("</tr></table>");

}
		catch(Exception e)
			{
	out.println(e.toString());		
	}
%>


<input type=button name=button value=Back>


</body>
</html>


<script language=vbscript>
sub button_onclick()
window.location="http://localhost:2012/MeetRep.jsp"
end sub
</script>