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
 <a href="aaddomcilecer.jsp" target="main">Approve/Denay Domcile Certificate<a><br><br><br>
 <a href="aadomcilecer.jsp" target="main">Approved Domcile Certificate<a><br><br><br>
 <a href="addomcilecer.jsp" target="main">Denayed Domcile Certificate<a><br><br><br>
</font>
</center>
</body>
</html>  
