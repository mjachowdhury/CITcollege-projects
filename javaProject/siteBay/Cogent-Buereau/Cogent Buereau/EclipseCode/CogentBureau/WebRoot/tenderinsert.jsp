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
<form method="get" name="cform" action="tenderinsert.jsp" target="main">
<table>
 <tr><td><html:errors/></td></tr>
<%	try{
      
      java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","distcol","distcol");
      java.sql.Statement stmt=null;
	  String sqlSTMT="insert into tcassign(tid,pk,nodays,amt) values ('"+request.getParameter("tid")+"','"+
		  (String)session.getAttribute("username")+"','"+
		  request.getParameter("ndays")+"','"+
		  request.getParameter("amt")+"')";
	  System.out.println(sqlSTMT);
	  stmt=con.createStatement();
	  int flag=stmt.executeUpdate(sqlSTMT);
      if(flag==0) 
		 out.println("<pre>Tender Send Failed.</pre>");
	  else
		 out.println("<pre>Successfully Tender Sended.</pre>");
	  con.close();
	  
	 }catch(Exception e)  {
	     System.out.println("Exception"+e);
      }
%>

</body>
</html>

 