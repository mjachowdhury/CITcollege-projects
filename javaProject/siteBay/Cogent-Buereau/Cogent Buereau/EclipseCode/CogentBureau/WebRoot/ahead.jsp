
<html>
  <head>
  <title>
   District Collectorate Management System
  </title>
 </head><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<body background="<%=path%>/images/shade1.gif" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
  <div align="left">
  <a href="aoptions10.jsp" target="main">Logout</a>
  
  <Ul>
  <li> <a href="aoptions1.jsp" target="main">BirthCertificate </a></li>
   <li><a href="aoptions2.jsp" target="main">DeathCertificate</a></li>
   <li><a href="aoptions3.jsp" target="main">WidowCertificate</a></li>
   <li><a href="aoptions4.jsp" target="main">IncomeCerificate</a></li>
   <li><a href="aoptions5.jsp" target="main">ResidentialCertificate</a></li>
   <li><a href="aoptions6.jsp" target="main">DomcileCerificate</a></li>
   <li><a href="aoptions7.jsp" target="main">MinorityCerificate</a></li>
   <li><a href="aoptions8.jsp" target="main">CasteCerificate</a></li>
   <li><a href="aoptions9.jsp" target="main">PassportCerificate</a></li>
   <li><a href="aoptions11.jsp" target="main">Tenders</a></li>
  </Ul>
 </div> 
</body>
</html>