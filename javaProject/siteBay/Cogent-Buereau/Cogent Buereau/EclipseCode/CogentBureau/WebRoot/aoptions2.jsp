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
 <a href="addeathcer.jsp" target="main">Approve/Denay Death Certificate<a><br><br><br>
 <a href="aadeathcer.jsp" target="main">Approved Death Certificate<a><br><br><br>
 <a href="ddeathcer.jsp" target="main">Denayed Death Certificate<a><br><br><br>
</font>
</center>
</body>
</html>
