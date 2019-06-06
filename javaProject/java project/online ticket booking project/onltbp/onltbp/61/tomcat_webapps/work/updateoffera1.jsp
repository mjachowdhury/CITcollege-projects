 
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
		<html:form action="UpdateOfferA">
		<center><b><font color="#FF9900" size="5">Update Offer</font></b><br><br>
		<input type="hidden" name="offerid" value="<%=(String)request.getAttribute("offerid")%>"/>
		
		<table>
		
		  
		    <tr><td><bean:message key="offername" />:</td><td> <html:text property="offername"/></td><td><html:errors property="offername"/><br/></td></tr>
			<tr><td><bean:message key="startdate" />:</td><td> <html:text property="startdate"/></td><td><html:errors property="startdate"/><br/></td></tr>
			<tr><td><bean:message key="enddate" />: </td><td><html:text property="enddate"/></td><td><html:errors property="enddate"/><br/></td></tr>
			<tr><td><bean:message key="applfor" />: </td><td><html:text property="applfor"/></td><td><html:errors property="applfor"/><br/></td></tr>
			<tr><td><bean:message key="descr" /> : </td><td><html:text property="descr"/></td><td><html:errors property="descr"/><br/></td></tr>
			
			

			<tr><td><html:submit>UPDATE</html:submit></td><td></td></tr>
			
		     </table>
		     <br><br>	

			
		</center>
			</html:form>
	</body>
</html>
