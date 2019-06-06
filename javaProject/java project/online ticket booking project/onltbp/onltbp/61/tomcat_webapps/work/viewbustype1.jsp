 
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html> 
	<head>
		<title>JSP for ViewBus</title>
	</head>
	<body>
	   <center><b><font color="#FF9900" size="5">View Bus Type Details</font></b><br><br>
		<table border="0">
		<tr><td><bean:message key="tid" /></td><td><bean:write name="b" property="tid" /></td><td></td><td><a href="BusType.do?tid=<bean:write name='b' property='tid' />">Modify</a></td></tr>
		<tr><td><bean:message key="tname" /></td><td><bean:write name="b" property="tname" /></td><td></td><td><a href="DeleteType.do?tid=<bean:write name='b' property='tid' />">Delete</a></td></tr>
		<tr><td><bean:message key="capacity" /></td><td><bean:write name="b" property="capacity" /></td><td></td><td><a href=""></a></td></tr>
		<tr><td><bean:message key="desc" /></td><td><bean:write name="b" property="desc" /></td></tr>
				
		</table>
		<br><br><a href="viewbus.jsp">Back</a>
		</center>
	</body>
</html>
