

<%!

String symbol, price, shares;

%>

<%

symbol = request.getParameter("sym");

price= request.getParameter("price");
        
shares = request.getParameter("n");

//out.print(symbol+" "+ price+" "+ shares);
%>

<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
<%
/*Verifying the first level login entered by the customer with the Customer_master table of the savv.mdb database. */
Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
try
{
/*String compName="", compSymbol="";
int i=0;
compName=request.getParameter("cn");
compSymbol=request.getParameter("cs");

//to store the login name as session variable
*/

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
String q = "insert into quote (symbol, CURRENT_PRICE, VOLUME, DAY_OPEN) values (?, ?, ?, ?)";
stmt=conn.prepareStatement(q);

stmt.setString(1, symbol);
stmt.setString(2, price); 
stmt.setString(3, shares); 
stmt.setString(4, price); 

int z = stmt.executeUpdate();
%><pre>



<h1 align="center"> <%
if(z == 1)
    out.print("Quotation added Successfully");
else
    out.print("Problem in giving quotation");
    
}
catch(Exception e)
{
    out.print(e);
}//catch

%>

