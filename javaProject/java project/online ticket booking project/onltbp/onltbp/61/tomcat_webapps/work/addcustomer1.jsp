 
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
		<html:form action="AddCustomer">
		<center><b><font color="#FF9900" size="5">Add New Customer</font></b><br><br>
		<table>
		
		  <tr>  <td><bean:message key="afname" />:</td><td> <html:text property="afname"/></td><td><html:errors property="afname"/></td></tr>
		    <tr><td><bean:message key="alname" />:</td><td> <html:text property="alname"/></td><td><html:errors property="alname"/></td></tr>
		    <tr><td><bean:message key="username" /> : </td><td><html:text property="username"/></td><td><html:errors property="username"/></td></tr>
		    <tr><td><bean:message key="pass" /> : </td><td><html:text property="pass"/></td><td><html:errors property="apass"/></td></tr>
		    <tr><td><bean:message key="aemail" /> :</td><td> <html:text property="aemail"/></td><td><html:errors property="aemail"/></td></tr>
			<tr><td><bean:message key="aaddress1" />:</td><td> <html:text property="aaddress1"/></td><td><html:errors property="aaddress1"/></td></tr>
			<tr><td><bean:message key="aaddress2" />: </td><td><html:text property="aaddress2"/></td><td><html:errors property="aaddress2"/></td></tr>
			<tr><td><bean:message key="aphno" />: </td><td><html:text property="aphno"/></td><td><html:errors property="aphno"/></td></tr>
			<tr><td><bean:message key="acity" /> : </td><td><html:text property="acity"/></td><td><html:errors property="acity"/></td></tr>
			<tr><td><bean:message key="astate" /> : </td><td><html:text property="astate"/></td><td><html:errors property="astate"/></td></tr>
						
			<tr><td><html:submit/></td><td><html:reset/><html:cancel/></td></tr>
		     </table>	
			
		</center>
			</html:form>
	</body>
</html>
