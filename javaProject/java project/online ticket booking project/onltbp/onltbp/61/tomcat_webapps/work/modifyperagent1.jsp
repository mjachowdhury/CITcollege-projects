 
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

 
<html> 
	<head>
		<title>JSP for agentReg form</title>
	</head>
	<body>
		<html:form action="UpdateAgent">
		<center><b><font color="#FF9900" size="5">Update Existing Agent</font></b><br><br>
		
		<table>
		    
		<tr><td><bean:message key="fname" /></td><td><html:text property="fname"/></td></tr>
		<tr><td><bean:message key="lname" /></td><td><html:text property="lname"/></td></tr>
		
		
		<tr><td><bean:message key="address1" /></td><td><html:text property="addr"/></td></tr>
		<tr><td><bean:message key="address2" /></td><td><html:text property="addr"/></td></tr>
		<tr><td><bean:message key="city" /></td><td><html:text property="city"/></td></tr>
		<tr><td><bean:message key="state" /></td><td><html:text property="state"/></td></tr>
		<tr><td><bean:message key="dor" /></td><td><html:text property="dor"/></td></tr>
		<tr><td><bean:message key="regno" /></td><td><html:text property="regno" /></td></tr>
		<tr><td><bean:message key="email" /></td><td><html:text property="email"/></td></tr>
		<tr><td><bean:message key="phno" /></td><td><html:text property="phno"/></td></tr>
			
			

			<tr><td><html:submit>UPDATE</html:submit></td><td></td></tr>
			
		     </table>
		     <br><br>	

			
		</center>
			</html:form>
	</body>
</html>
