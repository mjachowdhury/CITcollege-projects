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
 <a href="aadwidowcer.jsp" target="main">Approve/Denay Widow Certificate<a><br><br><br>
 <a href="aawidowcer.jsp" target="main">Approved Widow Certificate<a><br><br><br>
 <a href="adwidowcer.jsp" target="main">Denayed Widow Certificate<a><br><br><br>
</font>
</center>
</body>
</html>
