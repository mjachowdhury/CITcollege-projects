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

<br>
<br>
<br>
<br>
<br>
<br>
<center>
<%if(session.getAttribute("username")!=null) {
   session.invalidate();
   response.sendRedirect("AdminLogin.jsp");
   System.out.println("code is executed");
} %>
</center>
</body>
</html>