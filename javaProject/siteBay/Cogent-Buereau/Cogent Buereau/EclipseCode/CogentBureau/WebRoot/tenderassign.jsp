 

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
 
  <%   try
   {
      
      java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","distcol","distcol");
      java.sql.Statement stmt=con.createStatement();
	  String sqlSTMT="update tender set pk='"+request.getParameter("pk")+"',flag=0 where tid='"+request.getParameter("tid")+"'";
	  int flag=stmt.executeUpdate(sqlSTMT);
	  if(flag>0) 
		 out.println("Tender ID="+request.getParameter("tid")+" is successfully assigned to Contractor ID="+request.getParameter("pk")); 
	  else
		  out.println("Failed to assign the tender");
 con.close();
	  }catch(Exception e) {System.out.println(e); }
  %>

</center>

</body>
</html>

 