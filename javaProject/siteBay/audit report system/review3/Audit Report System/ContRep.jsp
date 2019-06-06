<%@ page import="java.sql.*,DBcon.DataCon,convertdate.ConvertDate" %>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home1.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="ExecMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Contracts Report</font></h1>
<form name="f">
		<%

		try{
			Connection con =new DataCon().getConnection(getServletContext());
Statement st = con.createStatement();
ResultSet rs=null;
     	String query="select m.contid,m.cont_category,d.addrefno,d.con_desc,d.cont_task,d.exec_id,s.status from con_master m,con_details d,con_status s where d.exec_id="+application.getAttribute("execid")+" and s.exec_id=d.exec_id";    
			 System.out.println(query);
		  
	
  rs = st.executeQuery(query);
ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();


%>
<table > <tr>
<table > <tr>
<th bgcolor=green colSpan=30><font color="white">Contract ID</font></th>
<th bgcolor=green colSpan=30><font color="white">Category</font></th>
<th bgcolor=green colSpan=30><font color="white">Company ID</font></th>
<th bgcolor=green colSpan=30><font color="white">Description</font></th>
<th bgcolor=green colSpan=30><font color="white">Task</font></th>
<th bgcolor=green colSpan=30><font color="white">Executive ID</font></th>
<th bgcolor=green colSpan=30><font color="white">Status</font></th>

<%

//display data
while(rs.next())
{
	out.println("<tr>");
for(int i=1;i<=count;i++)
	{

if(i == 8)
		i=i+1;
	Object obj = rs.getObject(i);
	if(obj == null)
		obj =new String( "N A");
	if(i==1)
         out.println("<th bgcolor=White colSpan=30><a href=EditCon.jsp?prefix="+obj.toString()+">" + obj.toString() + "</a></th>");
	else if(i==3)
         out.println("<th bgcolor=White colSpan=30><a href=CompRep.jsp?prefix="+obj.toString()+">" + obj.toString() + "</a></th>");  
    else
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
</tr>
</table>
</form></center>
</body>
</html>