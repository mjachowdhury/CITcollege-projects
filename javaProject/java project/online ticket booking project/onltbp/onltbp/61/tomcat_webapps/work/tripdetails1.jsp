 
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
		<html:form action="AddTrip">
		<center><b><font color="#FF9900" size="5">View Trip Details</font></b><br><br>
		<table>
		
		    <tr><td><bean:message key="bno"/>:</td><td>
		    <logic:notEmpty name="busnumber" scope="request">
		    <html:text property="bno" onfocus="form.startpoint.focus()" value="<%=(String)request.getAttribute("busnumber")%>"/>
		    </logic:notEmpty>
		    <logic:empty name="busnumber" scope="request">
		    <html:select property="bno">
		    <html:optionsCollection name="busal" value="busno" label="busno"/>
		     <%--<logic:iterate id="busal" name="busal">
		    <html:option value="<bean:write name='busal' property='bid' />">
		    <bean:write name="busal" property="busno" /></html:option>
			</logic:iterate>
			--%>
			</html:select>
			</logic:empty>
			
		    </td>
			<td></td></tr>	
		    

		    <tr><td><bean:message key="startpoint" />:</td><td>
		    <html:select property="startpoint">
		    <html:optionsCollection name="loc" value="lid" label="lname"/>
		   	</html:select></td>
		<td></td></tr>
		
		
		<tr><td><bean:message key="endpoint" />:</td><td>
		<html:select property="endpoint">
		<html:optionsCollection name="loc" value="lid" label="lname"/>
		    </html:select></td>
		<td><a href="addlocation.jsp?busnumber=<%=(String)request.getAttribute("busnumber")%>">
		<bean:message key="addloc"/></a></td></tr>
		
		
			<tr><td><bean:message key="starttime" />:</td><td> <html:text property="starttime"/>(hh:mm(am/pm))</td><td><html:errors property="starttime"/></td></tr>
			<tr><td><bean:message key="endtime" />:</td><td> <html:text property="endtime"/>(hh:mm(am/pm))</td><td><html:errors property="endtime"/></td></tr>
			<tr><td><html:submit/></td><td><html:reset/><html:cancel/></td></tr>
		     </table>	
			
		</center>
			</html:form>
	</body>
</html>
