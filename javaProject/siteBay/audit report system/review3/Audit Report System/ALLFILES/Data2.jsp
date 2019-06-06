<html>
<body bgcolor=pink>

<%@ page import="java.sql.*"  errorPage="ErrorPage.jsp" %>

<%!
Connection con = null;
Statement st = null;
ResultSet rs = null;
Object obj = null;
%>

<%
String tname = request.getParameter("TABLE");

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

con = DriverManager.getConnection("Jdbc:Odbc:DSN","scott","tiger");

st = con.createStatement();

rs = st.executeQuery("SELECT * FROM  " + tname);

ResultSetMetaData rsd = rs.getMetaData();

int count = rsd.getColumnCount();


%>
<table border=3>

	<%

while (rs.next())
{ %>
<Tr>
	<%
	for(int i=1;i<=count;i++)
	{
	obj = rs.getObject(i);
	if(obj == null)
	obj = new String("Not Found");%>

	<td><%=obj %> </td>

	<%
	}
out.println("</tr>");
}
%>


</tr>


</body>
</html>






