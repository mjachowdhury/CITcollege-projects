<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.*"%>

<HEAD>
	<script LANGUAGE="Javascript" SRC="validate.js"></script>
	<LINK href="styles.css" type="text/css" rel="stylesheet">
</HEAD>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>

<BODY class=SC>
<BR><BR><BR>
<P align=center><B>Delete User</B></P>
<%
if(!(request.getMethod().equals("POST")))
{

	Connection con=null;
	ResultSet rs=null;
	Statement stmt=null;
	String Condition = "UserId";
	String[] ConValues = new String[2000];
	int ConCount=0,i=0;
	String Query1="";
%>
<FORM Name='DelForm' METHOD=POST ACTION="">
<table width=800 height=300 background="Images/i11.jpg" align="center">
<tr>
<td><img src="Images/i8.jpg" height=200 width=500></td>
<td>
		<TABLE align=center width="25%" bordercolor=#D8D8D8 border=0>
		<TR>
		<TH class='row_title' colspan=2>Delete User</TH>
		</TR>
                <TR>
			<Td><%=Condition.replace('`',' ').toUpperCase()%></Td>
			<TD><SELECT NAME="<%=Condition%>">
			<OPTION Value="">Select</OPTION>

<%
	try{
			
                       /*Getting the connection variable from session*/
	con=(Connection)session.getAttribute("connection");
                        
			stmt =  con.createStatement();

			if(Condition.trim().equalsIgnoreCase("undefined")){
				System.out.println("in if");
				Query1 = "Select * from login";
			}
			else{
				System.out.println("in else");
				Query1 = "Select "+Condition+" from login group by "+Condition;
			}
			String str="";
			System.out.println(Query1);
			rs = stmt.executeQuery(Query1);
			int rCount=0;
				while(rs.next()){
					String x = rs.getString(1);
					ConValues[i]=x;
					%><OPTION Value=<%=ConValues[i]%>><%=ConValues[i]%></OPTION><%
					i++;
				}

		}catch(Exception e){

		}
%>
		</SELECT></TD>
		</TR>
		<TR><TD align=center Colspan=2><Input type='Submit' name='submit' value='Delete'></TD></TR>
		</TABLE>
		</td>
		</tr></table>
</FORM>
<H6 align=center> Select a record to delete from database </H6>
<%}else{%>
<%
	Connection con=null;
	ResultSet rs=null;
	Statement stmt=null;
	String Value = request.getParameter("UserId");
	try{
                        
                        /*Getting the connection variable from session*/
	con=(Connection)session.getAttribute("connection");
						stmt =  con.createStatement();
	
			
			String Query = "Delete from login where UserId='"+Value+"'";
			int result = stmt.executeUpdate(Query);
			if( result > 0)	{
				%><P align=center><B>Deleted from the database sucessfully<B></P><%
			}
			else{
				%><P align=center><B>Error in deletion..please try again<B></P><% 
			}

		}catch(Exception e){

		}
}
%>

</BODY>
