
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>viewcus1.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
<center><b><font color="#FF9900" size="5">View Customers</font></b><br><br>
		<table border="1">
		<tr><th><bean:message key="uname" /></th>
		<th><bean:message key="address1" /></th>
		<th><bean:message key="city" /></th>
		<th><bean:message key="state" /></th>
		<th><bean:message key="dor" /></th>
		<th><bean:message key="regno" /></th>
		<th><bean:message key="email" /></th>
		<th><bean:message key="phno" /></th>
		
		</tr>
		<logic:iterate id="al" name="al" >
		<tr><td><bean:write name="al" property="fname" /></td>
		<td><bean:write name="al" property="addr" /></td>
		<td><bean:write name="al" property="city" /></td>
		<td><bean:write name="al" property="state" /></td>
		<td><bean:write name="al" property="dor" /></td>
		<td><bean:write name="al" property="idno" /></td>
		<td><bean:write name="al" property="email" /></td>
		<td><bean:write name="al" property="phno" /></td>
			
		</tr>
		</logic:iterate>
</table>
		</center>
    

  </body>
</html:html>
