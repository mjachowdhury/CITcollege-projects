
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>blockticket1.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
    <body><center>
<html:form action="deltickC">
<table border="1">
<tr>
<th/>
<th>BusNo</th>

<th>TicketId</th>
<th>Date</th>
<th>No_Of_Tick</th>
</tr>
<logic:iterate id="mail" name="block">
  <tr>
<td><input type="checkbox" name="id" value="<bean:write  name="mail" property="tickid"/>"/></td> 
<td><bean:write  name="mail" property="busno"/></td>
<td><bean:write  name="mail" property="tickid"/></td>
<td><bean:write  name="mail" property="date"/></td> 
<td><bean:write  name="mail" property="not"/></td>
</tr>
</logic:iterate>
</table>
 <html:submit value="block"/>
  </html:form> 
  </center>
  </body>
</html:html>
