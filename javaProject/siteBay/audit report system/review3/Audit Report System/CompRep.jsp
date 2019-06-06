<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home1.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="ExecMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Company Details</font></h1>
<form name="del" action="UpdateContractDetails">
<%
   String prefix=request.getParameter("prefix");
%>
 <%

		try{
			Connection con =new DataCon().getConnection(getServletContext());
Statement st = con.createStatement();
ResultSet rs=null;
     	String query="select a.addrefno,a.compname,a.location,a.city,a.state,a.country,e.cont_person,e.emailid1,p.phno1,p.mobile from address a,email e,phnos p where a.addrefno=e.addrefno and a.addrefno=p.addrefno and a.addrefno="+request.getParameter("prefix");
    rs = st.executeQuery(query);
ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();


%>
<table > <tr>

<th bgcolor=green colSpan=30><font color="white">Company ID </font></th>
<th bgcolor=green colSpan=30><font color="white">Company Name</font></th>
<th bgcolor=green colSpan=30><font color="white">Location</font></th>
<th bgcolor=green colSpan=30><font color="white">City</font></th>
<th bgcolor=green colSpan=30><font color="white">Sate</font></th>
<th bgcolor=green colSpan=30><font color="white">Country</font></th>
<th bgcolor=green colSpan=30><font color="white">Contact Person</font></th>
<th bgcolor=green colSpan=30><font color="white">Email Id</font></th>

<th bgcolor=green colSpan=30><font color="white">Phone</font></th>

<th bgcolor=green colSpan=30><font color="white">Mobile</font></th>
<%
while(rs.next())
{
	out.println("<tr>");
for(int i=1;i<=count;i++)
	{

if(i == 11)
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
			e.printStackTrace();
	}
%>
</tr>
</table>
</form>
</center>
</body>
</html>

