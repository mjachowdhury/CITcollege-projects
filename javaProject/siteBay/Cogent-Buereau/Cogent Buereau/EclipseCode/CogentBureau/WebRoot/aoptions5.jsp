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
 <a href="aadresidentcer.jsp" target="main">Approve/Denay Residential Certificate<a><br><br><br>
 <a href="aaresidentcer.jsp" target="main">Approved Residential Certificate<a><br><br><br>
 <a href="adresidentcer.jsp" target="main">Denayed Residential Certificate<a><br><br><br>

</font>
</center>
</body>
</html> 
