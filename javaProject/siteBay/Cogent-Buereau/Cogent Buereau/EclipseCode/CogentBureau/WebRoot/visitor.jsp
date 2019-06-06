<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'visitor.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body background="<%=path%>/images/shade1.gif" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
  <UL>
   <li><a href="options1.jsp" target="main">BirthCertificate </a></li>
 <li><a href="options2.jsp" target="main">DeathCertificate</a></li>
 <li><a href="options3.jsp" target="main">WidowCertificate</a></li>
 <li><a href="options4.jsp" target="main">IncomeCerificate</a></li>
 <li><a href="options5.jsp" target="main">ResidentialCertificate</a></li>
 <li><a href="options6.jsp" target="main">DomcileCerificate</a></li>
 <li><a href="options7.jsp" target="main">MinorityCerificate</a></li>
 <li><a href="options8.jsp" target="main">CasteCerificate</a></li>
 <li><a href="options9.jsp" target="main">PassportCerificate</a></li>
 </UL>
  </body>
</html>
