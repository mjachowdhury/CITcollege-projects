

<html>
 <head>
<title>
   District Collectorate Management System
</title>
 </head>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body background="<%=path%>/images/shade1.gif">
<center>
<font size=4 >
<u>Opened Tender Information</u>
</font>
<form method="get" name="pform" action="pcandidateDetails.do" onSubmit="return validateAll();" target="main">
<script language="JavaScript">
  function validateAll() {
    if(document.pform.tid.length==0)  {
	  alert("ID must be select");
	  return false;
	}
	return true;
  }
</script>
<table border="2" cellpadding="2" cellspacing="0">
 <tr><td><html:errors/></td></tr>
<tr>
 <td><h3>&nbsp;Tender ID&nbsp;&nbsp;&nbsp;</td> 
 <td><h3>&nbsp;Tender Name&nbsp;&nbsp;&nbsp;</td> 
 <td><h3>&nbsp;Status&nbsp;&nbsp;&nbsp;<b></td> 
 </tr>
  <%   try
   {
      
      java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","distcol","distcol");
      java.sql.Statement stmt=con.createStatement();
	  String sqlSTMT="select t.tid,t.tname,t.flag from tender t,tcassign c where c.tid=t.tid and c.pk='"+session.getAttribute("username")+"'";
      java.sql.ResultSet rs=stmt.executeQuery(sqlSTMT);
	  while(rs.next()) {
	  %>
<tr>
  <td><%=rs.getString(1)%>&nbsp;&nbsp;&nbsp;</td>  
  <td><%=rs.getString(2)%>&nbsp;&nbsp;&nbsp;</td>
  <td><%if(rs.getString(3).equals("0")) {%>
	   open
	  <%}else if(rs.getString(3).equals("1")){ %>
	  processing
	  <%}else if(rs.getString(3).equals("2")){ %>
	  closed
	  <%}%>
		  </td>
</tr>
<%} con.close();
	  }catch(Exception e) {System.out.println(e); }
  %>
</table>
</center>

</body>
</html>

 