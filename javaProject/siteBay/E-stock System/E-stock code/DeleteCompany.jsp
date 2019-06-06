<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>

<%! Connection conn = null;
    Statement st = null;
	ResultSet rs = null;

	%>
<%
String symbol = request.getParameter("cs");
String query = "delete from symbol where symbol = '"+symbol+"'";
 
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
st = conn.createStatement();
int rows = st.executeUpdate(query);

if(rows >= 1)
	{%>
	<h1 align = 'center'>Company with symbol "<%=symbol%>" deleted successfully!</h1>
<%}
else
	{
	%>
		<h1 align = 'center'>Problem in deleting Company with symbol "<%=symbol%>"!</h1>
		<%}
}//try
catch(Exception e)
{
	out.println(e);
}%>
        
