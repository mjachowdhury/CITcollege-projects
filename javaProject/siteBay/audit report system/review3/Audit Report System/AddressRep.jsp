<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>

<%@ page import="java.sql.*" %>
<%

		try{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con = DriverManager.getConnection("Jdbc:Odbc:JDSN","interact","interact");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from Address,Email,Phnos where Address.AddRefNo=Email.AddRefNo AND Address.AddRefNo=Phnos.AddRefNo");

ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();

out.println("<center><h2>Address Details</h2></center>");
out.println("<table > <tr>");
//display headers
for(int i=1;i<=count;i++)
	{
		if(i == 7)
		i=i+1;
      
	   if(i == 12)
		i=i+1;
	out.println("<th bgcolor=gray colSpan=30>" + 
	rsd.getColumnName(i) + "</th>");
}//for

//display data
while(rs.next())
{
	out.println("<tr>");
for(int i=1;i<=count;i++)
	{
if(i == 7)
		i=i+1;
if(i == 12)
		i=i+1;
	Object obj = rs.getObject(i);
	if(obj == null)
		obj =new String( "N A");
	out.println("<th bgcolor=gray colSpan=30>" + obj.toString() + "</th>");
}//for
	out.println("</tr>");

}//while



out.println("</tr></table>");

}
		catch(Exception e)
			{
	out.println(e.toString());		
	}
%>


</body>
</html>