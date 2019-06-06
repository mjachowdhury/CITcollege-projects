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
 <a href="applyminority.jsp" target="main">ApplyMinorityCertificate<a><br><br><br>
 <a href="checkminoritystatus.jsp" target="main">Check Status</a><br><br><br>
 <a href="viewminority.jsp" target="main">ViewCerificate</a><br><br>
</font>
</center>
</body>
</html>
