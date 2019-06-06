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
 <a href="aadminoritycer.jsp" target="main">Approve/Denay Minority Certificate<a><br><br><br>
 <a href="aaminoritycer.jsp" target="main">Approved Minority Certificate<a><br><br><br>
 <a href="adminoritycer.jsp" target="main">Denayed Minority Certificate<a><br><br><br>
</font>
</center>
</body>
</html>
