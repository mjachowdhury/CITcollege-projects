
 
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
	   <center>
	       <b><font color="#FF9900" size="5">View Trip Halts</font></b><br><br>
	       <logic:notEmpty name="obj1" scope="session">
		<table border="1">
		<tr><th><bean:message key="trid" /></th>
		<th><bean:message key="locname" /></th>
		<th><bean:message key="seqno" /></th>
		<th><bean:message key="updates"/></th>
		
		</tr>
		<logic:iterate id="obj1" name="obj1">
		<tr><td><bean:write name="obj1" property="trid" /></td>
		<td><bean:write name="obj1" property="lname" /></td>
		<td><bean:write name="obj1" property="seqno" /></td>
				<td><b>
<a href="ChangeHalt.do?seqno=<bean:write name="obj1" property="seqno"/>&&id=<bean:write name="obj1" property="trid" />">Remove</a></td>
				
				
				</tr>
				
		</logic:iterate>
		</table>
		</logic:notEmpty>
		<logic:empty name="obj1" scope="session">
			No TripHalts available for this Trip<br/>
			  for this Bus
		</logic:empty>
		<br><br><a href="viewtripdetails.jsp">Back</a>
		</center>
	</body>
</html>
