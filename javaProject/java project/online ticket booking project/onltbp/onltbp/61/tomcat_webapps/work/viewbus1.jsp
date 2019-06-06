 
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
	<table border="1">
	   <logic:iterate id="busdetails" name="busdetails" >
		<tr><td><bean:write name="busdetails" property="bid" /></td>
		<td><bean:write name="busdetails" property="bname" /></td>
		<td><bean:write name="busdetails" property="busno" /></td>
		<td><a href="ViewBusType.do?bustype=
		<bean:write name='busdetails' property='bustype'/>">
		<bean:write name="busdetails" property="bustype"/></a></td>
		<td><a href="ViewTrip.do?busno=<bean:write name='busdetails' property='busno' />"><bean:message key="detail"/></a></td>
		
		<td><a href="Getbus.do?busno=<bean:write name='busdetails' property='busno' />"><bean:message key="edit"/></a>
		<a href="DeleteBus.do?busno=<bean:write name='busdetails' property='busno' />"><bean:message key="delete"/></a></td>
		</tr>
		</logic:iterate>
		</table>
		
		</center>
	</body>
</html>
