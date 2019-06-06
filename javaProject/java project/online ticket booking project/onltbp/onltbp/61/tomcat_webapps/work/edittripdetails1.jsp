 
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

 
<html> 
	<head>
		<title>Adding BUSservices jsp form</title>
	</head>
	<body>
	<font color="red">
	   <html:messages id="msgs" message="true">
	   <%=msgs%></html:messages></font>
		<html:form action="UpdateTrip">
		<center><b><font color="#FF9900" size="5">Update Trip Details</font></b><br><br>
		<table>
		
		    <tr><td><bean:message key="bno"/>:</td>
		    <td><html:text property="bno" onfocus="form.startpoint.focus()"/></td><td></td></tr>
			<tr><td><bean:message key="startpoint" />:</td>
			<td><html:text property="startpoint" /></td>
		    <td></td></tr>
			<tr><td><bean:message key="endpoint" />:</td>
		    
			<td><html:text property="endpoint" /></td>
		    <td><a href="ViewLocation.do">View Location</a></td></tr>
				
			<tr><td><bean:message key="starttime" />:</td><td> <html:text property="starttime"/>(hh:mm(am/pm))</td><td></td></tr>
			<tr><td><bean:message key="endtime" />:</td><td> <html:text property="endtime"/>(hh:mm(am/pm))</td><td></td></tr>
			<tr><td><html:submit/></td><td><html:cancel/></td></tr>
		     </table>	
			
		</center>
			</html:form>
	</body>
</html>
