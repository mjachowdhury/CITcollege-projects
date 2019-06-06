

<html>
 <head>
<title>
   District Collectorate Management System
</title>
 </head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<body background="<%=path%>/images/shade1.gif">
<center>
<font size=4 >
<u>Tender Information</u>
</font>
<form method="get" name="pform" action="tenderassign.jsp" target="main">
Click on the Tender ID to assign tender.
<table border="2" cellpadding="2" cellspacing="0">
 <tr><td><html:errors/></td></tr>
<tr>
 <td><h3>&nbsp;Tender ID&nbsp;&nbsp;&nbsp;</td> 
 <td><h3>&nbsp;Contractor ID&nbsp;&nbsp;&nbsp;</td> 
 <td><h3>&nbsp;Days&nbsp;&nbsp;&nbsp;<b></td> 
 <td><h3>&nbsp;Amount&nbsp;&nbsp;&nbsp;<b></td> 
 </tr>
  <%   try
   {
      
      java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","distcol","distcol");
      java.sql.Statement stmt=con.createStatement();
      java.sql.Statement stmt1=con.createStatement();
	  String sqlSTMT="select t.tid,t.pk,t.NODAYs,t.amt from tcassign t,tender e where t.tid=e.tid and e.flag='0'";
      java.sql.ResultSet rs=stmt.executeQuery(sqlSTMT);
	  boolean flag=rs.next();
	  if(flag==false)
		 out.println("<tr><td align=\"center\" colspan=\"4\">Tenders Not Avaliable</td></tr>");
	  while(flag) {
		  String id=rs.getString(1);
	  %>
<tr>
  <td><a href="tenderassign.jsp?tid=<%=id%>&pk=<%=rs.getString(2)%>"/><%=id%></a>&nbsp;&nbsp;&nbsp;</td>  
  <td><%=rs.getString(2)%>(
  <%java.sql.ResultSet rs1=stmt1.executeQuery("select cname from contractor where pk='"+rs.getString(2)+"'");
  rs1.next();
    %>
  <%=rs1.getString(1)%>)&nbsp;&nbsp;&nbsp;</td>
  <td><%=rs.getString(3)%>&nbsp;&nbsp;&nbsp;</td>
  <td><%=rs.getString(4)%>&nbsp;&nbsp;&nbsp;</td>
</tr>
<%flag=rs.next();} con.close();
	  }catch(Exception e) {System.out.println(e); }
  %>
</table>
</center>

</body>
</html>

 