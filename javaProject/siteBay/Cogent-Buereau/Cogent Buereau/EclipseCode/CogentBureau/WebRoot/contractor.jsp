

<html>
 <head>
<title>
   District Collectorate Management System
</title>
 </head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body background="<%=path%>/images/shade1.gif">
<center>
<font size=4 >
<u>Contractor Information</u>
</font>
<form method="get" target="main">
<br>
<br>
<table align="center">
 <tr>
  <td>
  <a href="createcontractor.jsp">CREATE</a>
  </td>
  <td>
  <a href="searchcontractor.jsp">EDIT</a>
  </td>
  <td>
  <a href="deletecontractor.jsp">DELETE</a>
  </td>
 </tr>
</table>
</form>
</body>
</html>

 