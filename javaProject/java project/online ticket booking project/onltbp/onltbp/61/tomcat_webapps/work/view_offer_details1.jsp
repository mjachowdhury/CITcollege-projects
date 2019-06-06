 
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
	   <center><b><font color="#FF9900" size="5">View Offers</font></b><br><br>
	   <logic:notEmpty name="offer" scope="session">
		<table border="1">
		<tr><th><bean:message key="offerid" /></th>
		<th><bean:message key="offername" /></th>
		<th><bean:message key="startdate" /></th>
		<th><bean:message key="enddate" /></th>
		<th><bean:message key="applfor"/></th>
		<th><bean:message key="descr" /></th>
		<th><bean:message key="change"/></th>
		</tr>
	   	<logic:iterate id="offer" name="offer" >
		<tr><td><bean:write name="offer" property="offerid" /></td>
		<td><bean:write name="offer" property="offername" /></td>
		<td><bean:write name="offer" property="startdate" /></td>
		<td><bean:write name="offer" property="enddate" /></td>
		<td><bean:write name="offer" property="applfor" /></td>
		<td><bean:write name="offer" property="descr" /></td>
		
		<td><logic:match name="offer" property="valid" value="1">
		<a href="GetModifyOffer.do?offerid=<bean:write name='offer' property='offerid' />"><bean:message key="modify"/></a></logic:match>
		<a href="DeleteOffer.do?offerid=<bean:write name='offer' property='offerid' />"><bean:message key="delete"/></a></td>
		</tr>
		</logic:iterate>
		</table>
		</logic:notEmpty>
		<logic:empty name="offer" scope="session">
			No Offers available <br/>
			
		</logic:empty>
		</center>
	</body>
</html>
