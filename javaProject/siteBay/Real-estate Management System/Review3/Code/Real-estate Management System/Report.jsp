<!--
	File : Report.jsp
	Purpose : This jsp displays the home page containing link to change user's password
			
-->

<html>
<%@ page language="java" %>
<%@ page session="true" %>
<%@page import="java.sql.*,java.io.*,java.util.Random"%>
<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);

//-->
</SCRIPT>
<Head>
	<LINK href="styles.css" type="text/css" rel="stylesheet">
</head>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>
<body Class=SC><font color="#ff0080">
<br></font>
<Title>Reports</Title>
<font face="Times New Roman" color="#ff0080" >
<center>


<BR>


<!--Retreiving user id using Session-->

<% String Userid=(String)session.getAttribute("userr");%>

<h2 class=report>Welcome <u><%=Userid%></u> to Realtors Pro V1.0</h2><br>



</center>
</font>
<p align="right">
<font color="#ff0080"><a href="ChangePassword.html">Change Password </a><br><br><% 
/* Verifying wether the user is authorised to edit emp details 
	if user is authorised he can edit the details if not he can 
	only view the details */ 
	Integer EmpAuth = (Integer)session.getAttribute("auth"); 
	int Auth = EmpAuth.intValue(); 
	System.out.println(Auth); 
			/*If authorised show Download data to Excel */ 
		if(Auth==0){ 
		%></font>

			<font color="#ff0080"><a href="AddUser.html">Add new user </a><br><br><a href="ViewUsers.jsp">View users </a></font> 
			<font color="#ff0080"><br><br><a href="DeleteUsers.jsp">Delete users </a></font> 
			<font color="#ff0080"><br><% 
		} 
 
%></font> 
		

</p>
</body>
</html>