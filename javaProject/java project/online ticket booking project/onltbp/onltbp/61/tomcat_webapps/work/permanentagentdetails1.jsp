 
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html> 
	<head>
		<title>JSP for ViewPendingAgent</title>
	</head>
	<body>
	   <center><b><font color="#FF9900" size="5">View Permanent Agent</font></b><br><br>
		
		<%--<logic:iterate id="agents" name="agents" >
		<logic:equal name="agents" parameter="agregno" value="<%=request.getParameter("regno")%>">--%>
		<c:forEach items="${sessionScope.pagents}" var="row">
		<c:if test="${row.regno eq param.regno}">
		<table border="0">
		<tr><td><bean:message key="uname" /></td><td><c:out value="${row.login}"/></td></tr>
		<tr><td><bean:message key="pass" /></td><td><c:out value="${row.pass}"/></td></tr>
		<tr><td><bean:message key="fname" /></td><td><c:out value="${row.fname}"/></td></tr>
		<tr><td><bean:message key="lname" /></td><td><c:out value="${row.lname}"/></td></tr>
		<tr><td><bean:message key="address1" /></td><td><c:out value="${row.addr}"/></td></tr>
		<tr><td><bean:message key="address2" /></td><td><c:out value="${row.addr}"/></td></tr>
		<tr><td><bean:message key="city" /></td><td><c:out value="${row.city}"/></td></tr>
		<tr><td><bean:message key="state" /></td><td><c:out value="${row.state}"/></td></tr>
		<tr><td><bean:message key="dor" /></td><td><c:out value="${row.dor}"/></td></tr>
		<tr><td><bean:message key="regno" /></td><td><c:out value="${row.regno}"/></td></tr>
		<tr><td><bean:message key="email" /></td><td><c:out value="${row.email}"/></td></tr>
		<tr><td><bean:message key="phno" /></td><td><c:out value="${row.phno}"/></td></tr>
		<tr><td></td><td></td></tr>
        <tr><td><a href="ModifyAgent.do?agregno=<c:out value='${row.regno}'/>"><bean:message key="modify"/></a></td>
        <td><a href="DeleteAgent.do?agregno=<c:out value='${row.regno}'/>"><bean:message key="delete"/></a></td></tr>
		</table>		
		
	
			<%--</logic:equal>
		</logic:iterate>--%>
	</c:if>
		</c:forEach>
		
		</center>
	</body>
</html>
