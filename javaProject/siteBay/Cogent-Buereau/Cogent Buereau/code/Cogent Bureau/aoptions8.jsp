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
 <a href="aadcastecer.jsp" target="main">Approve/Denay Caste Certificate<a><br><br><br>
 <a href="adcastecer.jsp" target="main">Approved Caste Certificate<a><br><br><br>
 <a href="aacastecer.jsp" target="main">Denayed Caste Certificate<a><br><br><br>
</font>
</center>
</body>
</html>
