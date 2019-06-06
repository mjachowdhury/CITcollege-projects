 
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
	   <center><b><font color="#FF9900" size="5">View Location</font></b><br><br>
	   <logic:notEmpty name="obj3" scope="session">
		<table border="1">
		<tr><th><bean:message key="lid" /></th>
		<th><bean:message key="lname" /></th>
		
		</tr>
		<logic:iterate id="obj3" name="obj3" >
		<tr><td><bean:write name="obj3" property="lid" /></td>
		<td><bean:write name="obj3" property="lname" /></td>
		
		</tr>
		</logic:iterate>
		</table><br><br>
		</logic:notEmpty>
		<logic:empty name="obj" scope="session">
			No Locations available <br/>

		</logic:empty>
		<a href="edittripdetails.jsp">Back</a>
		</center>
	</body>
</html>
