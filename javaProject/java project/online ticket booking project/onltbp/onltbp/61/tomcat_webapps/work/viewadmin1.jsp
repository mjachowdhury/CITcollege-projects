 
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
	   <center><b><font color="#FF9900" size="5">View Admin</font></b><br><br>
	   <logic:notEmpty name="ad" scope="session">
		<table border="1">
		<tr><th><bean:message key="fname" /></th>
		<th><bean:message key="lname" /></th>
		<th><bean:message key="uname" /></th>
		<th><bean:message key="address1" /></th>
		<th><bean:message key="address2" /></th>
		<th><bean:message key="city" /></th>
		<th><bean:message key="state" /></th>
		<th><bean:message key="email" /></th>
		<th><bean:message key="phno" /></th>
				</tr>
		<logic:iterate id="ad" name="ad" >
		<tr><td><bean:write name="ad" property="afname" /></td>
		<td><bean:write name="ad" property="alname" /></td>
		<td><bean:write name="ad" property="username" /></td>
		<td><bean:write name="ad" property="aaddress1" /></td>
		<td><bean:write name="ad" property="aaddress2" /></td>
		<td><bean:write name="ad" property="acity" /></td>
		<td><bean:write name="ad" property="astate" /></td>
		<td><bean:write name="ad" property="aemail" /></td>
		<td><bean:write name="ad" property="aphno" /></td>
			
		</tr>
		</logic:iterate>
		</table>
		</logic:notEmpty>
		<logic:empty name="ad" scope="session">
			No Admin Details Available<br/>
			
		</logic:empty>
		</center>
	</body>
</html>
