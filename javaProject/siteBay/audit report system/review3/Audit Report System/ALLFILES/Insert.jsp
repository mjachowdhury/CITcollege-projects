<%@ page import="java.sql.*,java.io.*" errorPage="ErrorPage.jsp" %>

<%!
String eno,ename,basic;

%>

<%
System.out.println(request.getMethod());
if(request.getMethod().equalsIgnoreCase("GET"))
{
	//read the data from QS
eno = request.getParameter("ENO");
ename = request.getParameter("ENAME");
basic = request.getParameter("BASIC");
}
else if(request.getMethod().equalsIgnoreCase("POST"))
{
	//read the data from Input Stream
DataInputStream dis = new DataInputStream(request.getInputStream());

eno = dis.readLine();
ename=dis.readLine();
basic=dis.readLine();
}

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("Jdbc:Odbc:DSN","scott","tiger");

PreparedStatement pst = con.prepareStatement("INSERT INTO JEMP VALUES(?,?,?)");

pst.setInt(1,Integer.parseInt(eno));

pst.setString(2,ename);

pst.setInt(3,Integer.parseInt(basic));

pst.execute();
con.commit();

PrintStream ps = new PrintStream (response.getOutputStream());
ps.println("Record Inserted");
%>


