 
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
	
		<html:form action="Updatebus">
		<center><b><font color="#FF9900" size="5">Update Bus Details</font></b><br><br>
		<table>
		
		  <tr>  <td><bean:message key="busno" />:</td><td> <html:text property="busno" onfocus="form.bname.focus()"/></td><td></td></tr>
		    <tr><td><bean:message key="btype" />:</td><td><html:text property="btype" onfocus="form.bname.focus()" /></td><td></td></tr>
			
			<tr><td><bean:message key="bname" />:</td><td> <html:text property="bname"/></td><td><html:errors property="bname"/></td></tr>
			
			<tr><td><html:submit/></td><td align="right"><html:cancel/></td><td></td></tr>
		     </table>	
			
		</center>
			</html:form>
	</body>
</html>
