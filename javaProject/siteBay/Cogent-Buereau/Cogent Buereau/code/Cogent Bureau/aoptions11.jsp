<html>
 <head>
  <title>
  </title>
 </head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<body background="<%=path%>/images/shade1.gif">
<center>
 <font face="arial" size=4 >
 <a href="contractor.jsp" target="main">Contractor Information<a><br><br><br>
 <a href="tender.jsp" target="main">Tender Information</a><br><br><br>
 <a href="atenderassign.jsp" target="main">Tender Assign</a><br><br><br>
 <a href="atenderremain.jsp" target="main"> UnCompleted Tender</a><br><br><br>
 <a href="atendercompleted.jsp" target="main"> Completed Tender</a><br><br><br>
 </font>
</center>
</body>
</html>
