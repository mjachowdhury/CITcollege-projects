<html>
<body bgcolor=pink>
<h2>Jsp as a Client</h2>
<hr>
<%@ page import="java.sql.*" errorPage="errorpage.jsp"%>
<form name="" action="http//localhost:7001/data1.jsp">
Select Table Name <SELECT name="TABLE">
<%

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("Jdbc:Odbc:DSN","scott","tiger");

Statement st = con.createStatement();

ResultSet rs = st.executeQuery("SELECT * FROM cat");
out.println("<SELECT name=TABLE>");
while(rs.next())
{
out.println("<option value=" + rs.getString(1) + ">" + rs.getString(1) + "</option>");
}

out.println("</select>");
%>

</form>
</body>
</html>




