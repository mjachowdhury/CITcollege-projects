<html>
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>

<head>
	<LINK href="styles.css" type="text/css" rel="stylesheet">

<title>
	Adding new Employee Details.......
</title>
<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);

//-->
</SCRIPT>
</head>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>
<body Class=SC>
<%

/*Declaration of variables*/

Connection con=null;
Statement stmt=null;
ResultSet rs=null;

String EMPName = request.getParameter("EMPName");
String EmpNo = request.getParameter("EMPNO");
int EMPNO= Integer.parseInt(EmpNo);
String EmpEmailID = request.getParameter("EmpEmailID");
String CurrentLocation=request.getParameter("CurrentLocation");

String strJoiningDateYYYY=request.getParameter("JoiningDateYYYY");
if(strJoiningDateYYYY.equals("")) strJoiningDateYYYY="0000";
	int JoiningDateYYYY= Integer.parseInt(strJoiningDateYYYY);
String strJoiningDateMM=request.getParameter("JoiningDateMM");
if(strJoiningDateMM.equals("")) strJoiningDateMM="00";
	int JoiningDateMM= Integer.parseInt(strJoiningDateMM);
String strJoiningDateDD=request.getParameter("JoiningDateDD");
if(strJoiningDateDD.equals("")) strJoiningDateDD="00";
	int JoiningDateDD= Integer.parseInt(strJoiningDateDD);
String strJoiningDate=strJoiningDateYYYY+"-"+strJoiningDateMM+"-"+strJoiningDateDD;
Date JoiningDate= Date.valueOf(strJoiningDate);
String Role=request.getParameter("Role");
String PrimarySkillset=request.getParameter("PrimarySkillset");
String Remarks=request.getParameter("Remarks");
int Auth = ((Integer)session.getAttribute("auth")).intValue();
if(Auth!=0){
	%><HR><H3 align=center>You are not authorized to access this page..</H3><HR><%
}
else{
try
{

	/*Getting the connection variable from session*/

	con=(Connection)session.getAttribute("connection");
	stmt =  con.createStatement();
	String SelQ = "Select EMPNO from EmpMaster where EMPNO="+EMPNO;
	//System.out.println(SelQ);
	rs=stmt.executeQuery(SelQ);
if(!(rs.next())){

	String Query = "Insert into EmpMaster values(\'"+EMPName+"\',"+EMPNO+",\'"+EmpEmailID+"\',\'"+CurrentLocation+"\',\'"+JoiningDate+"\',\'"+Role+"\',\'"+PrimarySkillset+"\',\'"+Remarks+"\')";
	
	//System.out.println(Query);
	int rowsAffected=stmt.executeUpdate(Query);		
	//System.out.println("Rows Affected = " + rowsAffected);
	//If data is updated then an entry is added to Activity log 
	if(rowsAffected==1)
	{
    String AdminUser=(String)session.getAttribute("userr");
	//System.out.println(AdminUser);
	//System.out.println(EmpNo);
	Timestamp DateModified = new Timestamp(System.currentTimeMillis());
	String ModDate = DateModified.toString();
	//System.out.println(DateModified.toString());
	String InsQuery="Insert into ActivityTracker(AdminUser,EmpModified,DateModified) values(\'"+AdminUser+"\',\'"+EmpNo+"\',\'"+ModDate+"\')";
	//System.out.println(InsQuery);
	int LogEntry=stmt.executeUpdate(InsQuery);

	
	
	%>
	
	<script>
		for(i=1;i<=10;i++) document.write("<br>");
	</script>
		<H3 align="center">Details Updated Successfully </H3>
	<BR>
	<center>
		<A href="Report.jsp"> Back </A>
	</center>
	<%}
	}
else{
%>
	<H3 align="center">Sorry Employee already exists in the databases</H3>
	<%}	
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
}
}
%>

</body>
</html>