

<%!

String symbol, price;

int cp = 0, change = 0, high = 0, low = 0, prevPrice = 0;

%>

<%

symbol = request.getParameter("sym");

price= request.getParameter("nprice").trim();
        


//out.print(symbol+" "+ price+" "+ shares);
%>

<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
<%
/*Verifying the first level login entered by the customer with the Customer_master table of the savv.mdb database. */
Connection conn=null;
PreparedStatement stmt;
Statement st = null;
ResultSet rs=null;


try
{
            

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
String q = "update quote set CURRENT_PRICE = ?, CHANGE = ?, HIGH = ?, LOW = ?, PREV_PRICE = ? where SYMBOL = ?";
stmt=conn.prepareStatement(q);

st = conn.createStatement();
String query = "select CURRENT_PRICE from quote where SYMBOL='"+symbol.trim()+"'";
rs = st.executeQuery(query);


cp = Integer.parseInt(price);  //new price

if(rs.next())
{    
    prevPrice = rs.getInt(1);
}

change = cp - prevPrice;

if(cp > prevPrice)
{
    high = cp;
    low = prevPrice;
}
else
{
    high = prevPrice;
    low = cp;
}




stmt.setInt(1, cp);
stmt.setInt(2, change); 
stmt.setInt(3, high); 
stmt.setInt(4, low);
stmt.setInt(5, prevPrice); 
stmt.setString(6, symbol); 


int z = stmt.executeUpdate();



%><pre>



<h1 align="center"> <%
if(z == 1)
    out.print("Quotation Detils Updted Successfully");
else
    out.print("Problem in Updating Quotation Detils");
    
}
catch(Exception e)
{
    out.print(e);
}//catch

%>

