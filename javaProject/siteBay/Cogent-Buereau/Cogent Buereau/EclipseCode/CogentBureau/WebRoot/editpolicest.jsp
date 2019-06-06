<%@taglib uri="/tags/struts-html" prefix="html"%>
<html:html>
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
<html:form method="get" action="editpolicestaction.do" target="main">
<table>
 <tr><td><html:errors/></td></tr>
 <tr>
  <td><font size=4 >Police Station Name</td>
  <td><font size=4 >:</td>
  <td><html:text property="pname" size="26"/></td>
 </tr>
 <tr>
  <td><font size=4 >Address</td>
  <td><font size=4 >:</td>
  <td><html:textarea property="addr"/></td>
 </tr>
 <tr>
  <td><font size=4>Login ID</td>
  <td><font size=4>:</td>
  <td><html:text property="login" size="26"/></td>
 </tr>
 <tr>
  <td><font size=4 >Password</font></td>
  <td><font size=4 >:</td>
  <td><font size=1><html:text property="pwd" size="26"/></font></td>
 </tr>
</table>
</center>
<input type="hidden" name="method" value="edit"/>
<table align="center">
 <tr>
  <td>
  <html:submit property="submit" value="edit"/>
  </td>
  <td>
  <html:cancel property="cancel" value="cancel"/>
  </td>
 </tr>
</table>
</html:form>
</body>
</html:html>