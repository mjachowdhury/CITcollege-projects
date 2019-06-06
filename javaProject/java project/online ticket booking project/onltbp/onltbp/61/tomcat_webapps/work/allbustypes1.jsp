
 
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html> 
	<head>
		<title>JSP for ViewBus</title>
	</head>
	<body>
	   <center><b><font color="#FF9900" size="5">View Bus Types</font></b><br><br>
	   
		<table border="1">
		<tr><th><bean:message key="tid" /></th>
		<th><bean:message key="tname" /></th>
		<th><bean:message key="capacity" /></th>
		<th><bean:message key="desc" /></th>
				
		</tr>
		<logic:iterate id="bustype" name="bustype">
		<tr><td><bean:write name="bustype" property="tid" /></td>
		<td><bean:write name="bustype" property="tname" /></td>
		<td><bean:write name="bustype" property="capacity" /></td>
		<td><bean:write name="bustype" property="desc" /></td>
			
		</tr>
		</logic:iterate>
		</table>
		
		</center>
	</body>
</html>
