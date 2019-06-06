 
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
	   <center><b><font color="#FF9900" size="5">View Pending Agents</font></b><br><br>
		<table border="1">
		<tr><th><bean:message key="fname" /></th>
		<th><bean:message key="lname" /></th>
		<th><bean:message key="address1" /></th>
		<th><bean:message key="address2" /></th>
		<th><bean:message key="city" /></th>
		<th><bean:message key="state" /></th>
		<th><bean:message key="dor" /></th>
		<th><bean:message key="regno" /></th>
		<th><bean:message key="email" /></th>
		<th><bean:message key="phno" /></th>
		<th><bean:message key="change" /></th>
		</tr>
		<logic:iterate id="agent" name="agents">
		<tr><td><bean:write name="agent" property="fname"/></td>
		<td><bean:write name="agent" property="lname" /></td>
		<td><bean:write name="agent" property="addr" /></td>
		<td><bean:write name="agent" property="addr" /></td>
		<td><bean:write name="agent" property="city" /></td>
		<td><bean:write name="agent" property="state" /></td>
		<td><bean:write name="agent" property="dor" /></td>
		<td><bean:write name="agent" property="regno" /></td>
		<td><bean:write name="agent" property="email" /></td>
		<td><bean:write name="agent" property="phno" /></td>
		
		<td><a href="Accept.do?agregno=<bean:write name="agent" property="regno" />"><bean:message key="accept"/></a>
		<a href="Reject.do?agregno=<bean:write name="agent" property="regno" />"><bean:message key="reject"/></a></td>
		</tr>
		</logic:iterate>
				


		
		</table>
		</center>
	</body>
</html>
