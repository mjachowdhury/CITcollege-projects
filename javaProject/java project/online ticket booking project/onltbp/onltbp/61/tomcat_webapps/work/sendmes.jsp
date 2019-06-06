 
<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for sm form</title>
	</head>
	<body>
		<html:form action="/[ACTION_PATH]">
			To 		: 	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<html:text property="To"/><html:errors property="To"/><br/>
			From 	:	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<html:text property="From"/><html:errors property="From"/><br/>
			Sub 	: 	&nbsp&nbsp&nbsp&nbsp&nbsp<html:text property="Sub"/><html:errors property="Sub"/><br/>
			
			Desc 	: 	&nbsp&nbsp&nbsp&nbsp&nbsp<html:textarea property="Desc"/><html:errors property="Desc"/>
			<br>
			<html:submit>
			</html:submit><html:cancel/>
		</html:form>
	</body>
</html>
