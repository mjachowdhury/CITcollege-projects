<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
<body text="magenta">
<%
/*Verifying the first level login entered by the customer with the Customer_master table of the savv.mdb database. */
Connection conn=null;
PreparedStatement stmt;
ResultSet rs=null;
String opt = "";
try
{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
String q = "select symbol from symbol minus select  symbol from quote";
stmt=conn.prepareStatement(q);
rs = stmt.executeQuery();

while(rs.next())
    opt += "<option>"+rs.getString(1)+"</option>\n";

rs = stmt.executeQuery();
if(rs.next())    
{%>
    <pre><font size = 5 color = 'magenta'>
    <form name="f1" action="StoreQuote.jsp" method="POST">
    Select Symbol of a Company: <select name="sym">
            <%=opt%>
</select>
    
    Release Price Per Share:    <input type="text" name="price" value="" />
    
    No. Of Shares Released:     <input type="text" name="n" value="" />
 <center>   <input type="submit" value="Quote" />  <input type="reset" value="Clear" />
    </form>
</pre>
<%}
  else
  {
    %><pre>
        
        
        
    <h1 align="center">No Company is remaining without quotating. 
To see its functionality add a Company
&
come here.</h1>
    <%
  }
    
    
}
catch(Exception e)
{
    out.print(e);
}//catch

%>
