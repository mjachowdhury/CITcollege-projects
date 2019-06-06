<html>
 <head>
  <title>
  </title>
 </head>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body background="<%=path%>/images/shade1.gif">
<center>
 <font face="arial" size=4 >
 <a href="applypassport.jsp" target="main">Apply Passport Certificate<a><br><br><br>
 <a href="checkpassportstatus.jsp" target="main">Check Status</a><br><br><br>
 <a href="viewpassport.jsp" target="main">View Passport Cerificate</a><br><br>
</font>
</center>
</body>
</html>
