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


<a href=" birthcerts.jsp" target="main">BirthCertificates </a><br><br>
   <a href="deathcert.jsp" target="main">DeathCertificates</a><br><br>
   <a href="widowcert.jsp" target="main">WidowCertificates</a><br><br>
   <a href="incomecert.jsp" target="main">IncomeCerificates</a><br><br>
   <a href="resdcert.jsp" target="main">ResidentialCertificates</a><br><br>
   <a href="domicilecert.jsp" target="main">DomicileCerificates</a><br><br>
  <a href="minoritycert.jsp" target="main">MinorityCerificates</a><br><br>
 <a href="castecert.jsp" target="main">CasteCerificates</a><br><br>

</body>
</html>