

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
<u>Police Station Information</u>
</font>
<form method="get" action="storepassportaction.do" target="main">
<table>
 <tr><td><html:errors/></td></tr>
 <tr>
  <td><font size=4 >Police Station Name</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="pname"></td>
  <td><font size=4 >Address</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="addr"></td>
 </tr>
 <tr>
  <td><font size=4>Login ID</td>
  <td><font size=4>:</td>
  <td><input type="text" name="login"></td>
  <td><font size=4 >Password</font></td>
  <td><font size=4 >:</td>
  <td><font size=1><input type="text" name="pwd"></font></td>
 </tr>
</table>
</center>
<br>
<br>
<table align="center">
 <tr>
  <td>
  <input type="submit" value="submit">
  </td>
  <td>
  <input type="reset" value="cancel">
  </td>
 </tr>
</table>

</body>
</html>

 