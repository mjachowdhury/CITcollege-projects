 
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html> 
	<head>
		<title>JSP for ViewPendingAgent</title>
	</head>
	<body>
	   <center><b><font color="#FF9900" size="5">View Permanent Agents</font></b><br><br>
		<table border="1">
		<tr><th><bean:message key="uname" /></th>
		<th><bean:message key="address1" /></th>
		<th><bean:message key="city" /></th>
		<th><bean:message key="state" /></th>
		<th><bean:message key="dor" /></th>
		<th><bean:message key="regno" /></th>
		<th><bean:message key="email" /></th>
		<th><bean:message key="phno" /></th>
		<th><bean:message key="updates" /></th>
		</tr>
		<logic:iterate id="pagents" name="pagents" >
		<tr><td><bean:write name="pagents" property="fname" /></td>
		<td><bean:write name="pagents" property="addr" /></td>
		<td><bean:write name="pagents" property="city" /></td>
		<td><bean:write name="pagents" property="state" /></td>
		<td><bean:write name="pagents" property="dor" /></td>
		<td><bean:write name="pagents" property="regno" /></td>
		<td><bean:write name="pagents" property="email" /></td>
		<td><bean:write name="pagents" property="phno" /></td>
		<td><a href="permanentagentdetails.jsp?regno=<bean:write name="pagents" property="regno" />"><bean:message key="viewdetail"/></td>

		
		</tr>
		</logic:iterate>
</table>
		</center>
	</body>
</html>
