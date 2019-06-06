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
 <a href="applyresd.jsp" target="main">ApplyResidentialCertificate<a><br><br><br>
 <a href="checkresdstatus.jsp" target="main">Check Status</a><br><br><br>
 <a href="viewresd.jsp" target="main">ViewCerificate</a><br><br>
</font>
</center>
</body>
</html>
