

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
<u>Tender Information</u>
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
Click on the Tender ID to tender.
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
	  String sqlSTMT="select tid,tname,flag from tender where tid not in (select tid from tcassign where pk='"+(String)session.getAttribute("username")+"')  and flag=0";
      java.sql.ResultSet rs=stmt.executeQuery(sqlSTMT);
	  System.out.println(sqlSTMT);
	  boolean flag=rs.next();
	  if(flag==false)
		 out.println("<tr><td align=\"center\" colspan=\"3\">Tenders Not Avaliable</td></tr>");
	  while(flag) {
	  %>
<tr>
  <td><a href='tenderview.jsp?tid=<%=rs.getString(1)%>'/><%=rs.getString(1)%></a>&nbsp;&nbsp;&nbsp;</td>  
  <td><%=rs.getString(2)%>&nbsp;&nbsp;&nbsp;</td>
  <td><%if(rs.getString(3).equals("0")) {%>
	   open
	  <%}else { %>
	  close
	  <%}%>
		  </td>
</tr>
<%flag=rs.next();} con.close();
	  }catch(Exception e) {System.out.println(e); }
  %>
</table>
</center>

</body>
</html>

 