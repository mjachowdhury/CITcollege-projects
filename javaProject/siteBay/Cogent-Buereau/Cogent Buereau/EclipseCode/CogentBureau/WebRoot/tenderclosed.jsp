

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
<form method="get" name="pform" action="tenderassign.jsp" target="main">
Tenders Owned By You
<table border="2" cellpadding="2" cellspacing="0">
 <tr><td><html:errors/></td></tr>
<tr>
 <td><h3>&nbsp;Tender ID&nbsp;&nbsp;&nbsp;</td> 
 <td><h3>&nbsp;Contractor ID&nbsp;&nbsp;&nbsp;</td> 
 <td><h3>&nbsp;Status&nbsp;&nbsp;&nbsp;<b></td> 
 </tr>
  <%   try
   {
      
      java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","distcol","distcol");
      java.sql.Statement stmt=con.createStatement();
      java.sql.Statement stmt1=con.createStatement();
	  String sqlSTMT="select tid,pk,flag from tender where  pk='"+session.getAttribute("username")+"' and flag='2'";
      java.sql.ResultSet rs=stmt.executeQuery(sqlSTMT);
	  boolean flag=rs.next();
	  if(flag==false)
		 out.println("<tr><td align=\"center\" colspan=\"4\">Tenders Not Avaliable</td></tr>");
	  while(flag) {
	  %>
<tr>
  <td><%=rs.getString(1)%>&nbsp;&nbsp;&nbsp;</td>  
  <td><%=rs.getString(2)%>&nbsp;&nbsp;&nbsp;</td>
  <td><%if(rs.getString(3).equals("2")) {%>
	   closed
	  <%}else { %>
	  cancel
	  <%}%>&nbsp;&nbsp;&nbsp;</td>
</tr>
<%flag=rs.next();} con.close();
	  }catch(Exception e) {System.out.println(e); }
  %>
</table>
</center>

</body>
</html>

 