
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>ViewOfferC.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <center><b><font color="#FF9900" size="5">View Offers</font></b><br><br>
	   
  <table border="1">
		<tr><th><bean:message key="offerid" /></th>
		<th><bean:message key="offername" /></th>
		<th><bean:message key="startdate" /></th>
		<th><bean:message key="enddate" /></th>
		<th><bean:message key="applfor"/></th>
		<th><bean:message key="descr" /></th>
	</tr>	
    <logic:iterate id="offer" name="offer" scope="session">
    <tr>
    <td><bean:write name="offer" property="offerid"/></td>
    <td><bean:write name="offer" property="offername"/></td>
    <td><bean:write name="offer" property="startdate"/></td>
    <td><bean:write name="offer" property="enddate"/></td>
    <td><bean:write name="offer" property="applfor"/></td>
    <td><bean:write name="offer" property="descr"/></td>
    </tr>
     </logic:iterate>
  </table>
  </body>
</html:html>
