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
 <a href="aadincomecer.jsp" target="main">Approve/Denay Income Certificate<a><br><br><br>
 <a href="aaincomecer.jsp" target="main">Approved Income Certificate<a><br><br><br>
 <a href="adincomecer.jsp" target="main">Denayed Income Certificate<a><br><br><br>
</font>
</center>
</body>
</html>
