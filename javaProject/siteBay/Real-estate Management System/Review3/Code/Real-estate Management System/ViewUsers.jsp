<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.*"%>
<HEAD>

<script LANGUAGE="Javascript" SRC="validate.js"></script>
<LINK href="styles.css" type="text/css" rel="stylesheet">

</HEAD>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>
<BODY class=SC>
<BR><BR>
<h3 align=center>Users</h3>

<%

	Connection con=null;
	ResultSet rs=null;
	Statement stmt=null;
	try{
			
            /*Getting the connection variable from session*/
	con=(Connection)session.getAttribute("connection");
			
                        stmt =  con.createStatement();
			String Query = "Select * from login order by `userid`";
			rs = stmt.executeQuery(Query);
			%>
<table width=800 height=300 background="Images/i11.jpg" align="center">
<tr>

<td>
						<table align="center" width="60%">
							<tr class=row_title>
							<th align="left">UserID</th><th align="left">Auth</th>
							</tr>
					<%
			int rCount=0;
			while(rs.next())
			{
					%>
					<tr class= '<%=(rCount%2!=0)? "row_even" : "row_odd"%>'>
						<td><%=rs.getString(1)%></td><td><%=rs.getString(3)%></td>
					</tr>
					<%
				rCount++;
			}
			if( rCount == 0)	{%><tr class= "row_even"><td colspan=3>Sorry No records Found</td></tr><% }
			
		}catch(Exception e){

		}
	
%>
</table></td>
</tr></table>
</BODY>

