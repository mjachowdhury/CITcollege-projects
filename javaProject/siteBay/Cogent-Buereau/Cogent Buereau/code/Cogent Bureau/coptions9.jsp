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
 <a href="tenderinfo.jsp" target="main">Newely Tender Information<a><br><br>
 <a href="tendersend.jsp" target="main">Sended Tender Information<a><br><br>
 <a href="tenderowned.jsp" target="main">Tenders Owned Information<a><br><br>
 <a href="tenderclosed.jsp" target="main">Completed Tender Information<a><br><br>
 <a href="tenderstatus.jsp" target="main">Change Tender Status<a><br><br><br>
</font> 
</center>
</body>
</html>
