<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
<%
/*Verifying the first level login entered by the customer with the Customer_master table of the savv.mdb database. */
Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
try
{
String compName="", compSymbol="";
int i=0;
compName=request.getParameter("cn");
compSymbol=request.getParameter("cs");

//to store the login name as session variable


Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
String q = "insert into symbol values (?, ?)";
stmt=conn.prepareStatement(q);
stmt.setString(1, compName);
stmt.setString(2, compSymbol); 
int z = stmt.executeUpdate();
%><pre>



<h1 align="center"> <%
if(z == 1)
    out.print("Company added Successfully");
else
    out.print("Sorry Problem in Adding Comapny");
    
}
catch(Exception e)
{
    out.print(e);
}//catch

%>
