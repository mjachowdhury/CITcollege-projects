

<html>
 <head>
<title>
   District Collectorate Management System
</title>
 </head>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body background="<%=path%>/images/shade1.gif">
<center>
<font size=4 >
<u>Police Station Information</u>
</font>
<form method="get" target="main">
<br>
<br>
<table align="center">
 <tr>
  <td>
  <a href="createpolicest.jsp">CREATE</a>
  </td>
  <td>
  <a href="searchpolicest.jsp">EDIT</a>
  </td>
  <td>
  <a href="deletepolicest.jsp">DELETE</a>
  </td>
 </tr>
</table>
</form>
</body>
</html>

 