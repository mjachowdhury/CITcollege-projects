
 
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
		<html:form action="AddTripHalt">
		<center><b><font color="#FF9900" size="5">Add Trip Halts</font></b><br><br>
		<table>
		    <tr><td><bean:message key="trid" />:</td><td>
		     <html:text property="trid" value="<%=(String)session.getAttribute("trid")%>" onfocus="form.lid.focus()"/></td><td><html:errors property="trid"/></td></tr>
		    <tr><td><bean:message key="lid" />:</td><td><html:select property="lid">
		    <html:optionsCollection name="loc1" value="lid" label="lname"/></html:select></td><td><html:errors property="lid"/></td></tr>
			<tr><td><html:submit/></td><td><html:reset/><html:cancel/></td></tr>
		</table>	
		</center>
			</html:form>
	</body>
</html>
