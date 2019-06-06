<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
<form name="f1" action="InsertCompany.jsp" method="POST">
    <pre><font size="3" color="magenta"><fieldset style="position:absolute; top:10px; left:10px; width:450px" align='center'>
	<legend align='center'>Add Company</legend>
		Enter Company Name:   <input type="text" name="cn" value="" />
        
		Enter Company Symbol: <input type="text" name="cs" value="" />
        
						<input type="submit" value="Add Company" />  <input type="reset" value="Clear" />
      </fieldset>  
    </pre>
</form>
<br><br><br>
NOTE: After adding company, quotate price per share & no. of shares issued through "Quote" (3rd) hyperlink.
<%! Connection conn = null;
    Statement st = null;
	ResultSet rs = null;

	%>
<%
String query = "select symbol from symbol";
 
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");
st = conn.createStatement();
rs = st.executeQuery(query);
%>
<form name="f1" action="DeleteCompany.jsp" method="POST">
    <pre><font size="3" color="magenta"><fieldset style="position:absolute; top:10px; left:490px; width:480px" align='center'>
	<legend align='center'>Delete Company</legend>

		Choose Company Symbol: <select name="cs">
		<%while(rs.next()){%>
		<option><%=rs.getString(1)%></option>
		<%}
		
}//try
catch(Exception e)
{
	out.println(e);
}%>
        
						<input type="submit" value="Delete Company" />  <input type="reset" value="Clear" />
      </fieldset>  
    </pre>
</form>