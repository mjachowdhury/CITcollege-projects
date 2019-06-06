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
<br>
<br>
<font size=4 >
<u>Minority Certificate</u>
</font>
<br>
<br>
<br>
<br>
<form method="get" action="viewminaction.do" target="main">
<table>
<tr>
<td>Enter the MinorityCertificate ID</td>
<td>:</td>
<td><input type="text"  name="mcid"></td>
</tr>
<tr>
<td>
<br>
<br>
</td>
</tr>
<tr align="right">
<td>
<input type="submit" value="View">
</td>
</tr>
</table>
</form>
</center>
</body>
</html>

