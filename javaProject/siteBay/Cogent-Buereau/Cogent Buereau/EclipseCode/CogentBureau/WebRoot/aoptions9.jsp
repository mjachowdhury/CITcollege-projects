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
 <a href="policest.jsp" target="main">Police St. Information<a><br><br><br>
 <a href="assignpolicest.jsp" target="main">Assign Police St.</a><br><br><br>
 <a href="deliverpassport.jsp" target="main">Deliver Passport</a><br><br>
 <a href="avacertificates.jsp" target="main">view approved certificates</a><br><br><br>
 <a href="avdcertificates.jsp" target="main">view deny certificates</a><br><br>
 </font>
</center>
</body>
</html>
