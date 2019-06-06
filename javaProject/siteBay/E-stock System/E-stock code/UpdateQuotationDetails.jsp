<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*" %>
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
String q = "select symbol from SYMBOL";
stmt=conn.prepareStatement(q);
rs = stmt.executeQuery();

while(rs.next())
    opt += "<option>"+rs.getString(1)+"</option>\n";


    
    
}
catch(Exception e)
{
    out.print(e);
}//catch

%>




<font size = '6' color = 'magenta'>
<pre>    <form name="f1" action="Update.jsp" method="POST">
        
        Select Company Symbol: <select name="sym">
            <%=opt%>
</select>

        New Price Per Share:   <input type="text" name="nprice" value="" />
        
                    <input type="submit" value="Update" />   <input type="reset" value="Clear" />
</form>
</pre>