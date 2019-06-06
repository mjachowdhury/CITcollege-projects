<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html>
 <head>
 </head>
 <%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body background="<%=path%>/images/shade1.gif">
<table border="0">
  <td align="center"><font size=4>Login ID is existed, enter another Login ID&nbsp;&nbsp;&nbsp;<a href="createpolicest.jsp">Enter Again</a></font></td>
  <br><br>
</table>
 
</body>
</html>