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
 <a href="adbirthcer.jsp" target="main">Approve/Denay Birth Certificate<a><br><br><br>
 <a href="abbirthcer.jsp" target="main">Approved Birth Certificate<a><br><br><br>
 <a href="dbbirthcer.jsp" target="main">Denayed Birth Certificate<a><br><br><br>
</font>
</center>
</body>
</html>
