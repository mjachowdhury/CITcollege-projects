

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
<form method="get" action="storecontractoraction.do" target="main">
<table>
 <tr><td><html:errors/></td></tr>
 <tr>
  <td><font size=4 >Contractor Name</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="cname" size=26></td>
 </tr>
 <tr>
  <td><font size=4 >Contractor Address</td>
  <td><font size=4 >:</td>
  <td><textarea name="caddr"></textarea></td>
 </tr>
  <tr>
  <td><font size=4 >Bank Account ID</td>
  <td><font size=4 >:</td>
  <td><input type="text" size=26 name="bid"></td>
 </tr>
  <tr>
  <td><font size=4 >Bank Name</td>
  <td><font size=4 >:</td>
  <td><textarea name="bname"></textarea></td>
 </tr>
  <tr>
  <td><font size=4 >Bank Address</td>
  <td><font size=4 >:</td>
  <td><textarea name="baddr"></textarea></td>
 </tr>
 <tr>
  <td><font size=4>Years Of Exp.</td>
  <td><font size=4>:</td>
  <td><input type="text" name="years" size=26></td>
 </tr>
  <tr>
  <td><font size=4 >Login Password</font></td>
  <td><font size=4 >:</td>
  <td><font size=1><input type="text" name="pwd" size=26></font></td>
 </tr>
</table>
</center>
<br>
<br>
<input type="hidden" name="method" value="create"/>
<table align="center">
 <tr>
  <td>
  <input type="submit" name="submit" value="create">
  </td>
  <td>
  <input type="reset" value="cancel">
  </td>
 </tr>
</table>

</body>
</html>

 