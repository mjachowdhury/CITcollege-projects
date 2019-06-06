<%@ page import="java.sql.*,DBcon.DataCon" %>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
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
     String query=request.getParameter("query");
	 if(query.equals("All"))
		 query="select m.contid,m.cont_category,d.addrefno,d.con_desc,d.cont_task,d.exec_id,s.status from con_master m,con_details d,con_status s where m.contid=d.contid and m.contid=s.contid";
		 else{
         %> 
		<center> <table><tr>
                    <td ><b>Select Executive </td>
                    <td align="left"><select name="exec"><option value="0">select</option>
					<%
		         	 rs=st.executeQuery("Select e.execid,u.username from users u,exec_master e where u.utype='exec' and u.username=e.execname");
String user="";
while(rs.next()){
	user=rs.getString(1);
	%>
	<option value="<%=user%>"><%=rs.getString(2)%></option>
	<%
}
		 %></select>&nbsp;&nbsp;<input type="button" value="Get" onclick="javascript:location.href='ContractRep.jsp?exec='+document.f.exec.value+'&query='"></td>
                  </tr></table>
		 <%
			
          if(request.getParameter("exec")!=null){
             
			 query="select m.contid,m.cont_category,d.addrefno,d.con_desc,d.cont_task,d.exec_id,s.status from con_master m,con_details d,con_status s where d.exec_id="+request.getParameter("exec")+" and s.exec_id=d.exec_id";
System.out.println(query+"-----------------------");

		  }
	 }
	
  rs = st.executeQuery(query);
ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();


%>
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
</center>
</form>
</center>
</body>
</html>