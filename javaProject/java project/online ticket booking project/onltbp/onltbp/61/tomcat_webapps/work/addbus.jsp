 
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
		<html:form action="Addbus">
		<center><b><font color="#FF9900" size="5">Add New Bus</font></b><br><br>
		<table>
		<%--<bean:write name='bustype' property='tid'/>--%>
		    <tr><td><bean:message key="busno" />:</td><td> <html:text property="busno"/></td><td><html:errors property="busno"/><br/></td></tr>
		   
		    <tr><td><bean:message key="btype" />:</td>
		    <td>
		    <html:select property="btype">
		    <html:optionsCollection name="bustype" value="tid" label="tname"/>
		    </html:select>
		    </td><td><a href="addbustype.jsp"><bean:message key="addbustype"/></a><br/></td></tr>
			<tr><td><bean:message key="bname" />:</td><td> <html:text property="bname"/></td><td><html:errors property="bname"/><br/></td></tr>
			
			<tr><td><html:submit/></td><td><html:reset/><html:cancel/></td></tr>
		    </table>	
			
		</center>
			</html:form>
	</body>
</html>
