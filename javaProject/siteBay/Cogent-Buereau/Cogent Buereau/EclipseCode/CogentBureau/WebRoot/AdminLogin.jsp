

<html>
<head>
<title> District Collectorate Management System</title>
</head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<body background="<%=path%>/images/shade1.gif" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<form method="post" action="loginaction.do">
	
<div align="center">
<TABLE cellpadding="3" cellspacing="0" border="0" width="30%">
<TBODY>
<th colspan="2"><font color="#400080"><em><strong>Administrator Login Here</strong></em></font></th>
<tr>
<td><font color="#400080"><em><strong>User ID</strong></em></font></td><td><input  type="text" name="uname" size="20"></td>
</tr>
<tr>
<td><font color="#400080"><em><strong>Password</strong></em></font></td><td><input name="pwd" type="password" value size="20"></td>
</tr>
<tr> 
  <td width="100">	 
   <p><input  type="submit" name="submit" value="login"></p>
  </td> 
  <td width="100">
   <p><input type="reset" value="cancel"></p>
  </td>
</tr>
</table>
</div>
</form>

</body>
</html>
