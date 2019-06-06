 
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
		<html:form action="UpdateBusType">
		<input type="hidden" name="tid" value="<bean:write name='b' property='tid' />"/>
		<center><b><font color="#FF9900" size="5">Update Bus Type</font></b><br><br><br>
		<table>
			
		    <tr><td>Type Id</td><td><%=(String)request.getParameter("tid")%></td></tr>		    
			<tr><td><bean:message key="tname" />:</td><td> <html:text property="tname"/></td><td><html:errors property="tname"/></td></tr>
			<tr><td><bean:message key="capacity" />:</td><td> <html:text property="capacity"/></td><td><html:errors property="capacity"/></td></tr>
			<tr><td><bean:message key="desc" />:</td><td> <html:text property="desc"/></td><td><html:errors property="desc"/></td></tr>
			
			<tr><td><html:submit/></td><td><html:cancel/></td></tr>
		     </table>	
			
		</center>
			</html:form>
	</body>
</html>
