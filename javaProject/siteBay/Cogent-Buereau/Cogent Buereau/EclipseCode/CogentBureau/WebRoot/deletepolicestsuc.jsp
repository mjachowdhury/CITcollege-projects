




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

<br>
<br>
<br>
<br>
<br>
<br>
<center>
<h3>Your form has been submited Successfully</h3><br>
<h3> This Police Station Id :<%String id=(String)request.getAttribute("cid");
out.println(id);%>  is deleted</h3>
</center>
</body>
</html>