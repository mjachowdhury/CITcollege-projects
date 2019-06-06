<%@ page import="java.sql.*" errorPage="ErrorPage.jsp" %>

<%! int empno=1; %>

<%
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("Jdbc:Odbc:DSN","scott","tiger");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("SELECT MAX(ENO)FROM JEMP");

if(rs.next())
empno=rs.getInt(1)+1;

con.close();

%>


<html>
<body bgcolor=pink>

<form name="" action="http://localhost:7001/Insert.jsp" method=post>

Emp No <Input name="ENO" value=<%=empno %>> <br>
Emp Name <Input name="ENAME"> <br>
Basic Pay <Input name="BASIC"> <br>

<input type=submit name=submit value=Insert>
</form>
</body>
</html>



