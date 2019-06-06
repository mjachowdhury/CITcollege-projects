
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>view1.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  
  <table border="+1">
  <logic:iterate id="mail" name="vmail">
  <input  type="checkbox" name="ch" value=<bean:write name="mail" property="id"/>/>
  <tr>
  <td><bean:write name="mail" property="id"/></td>
  <td><bean:write name="mail" property="from"/></td>
  <td><bean:write name="mail" property="sub"/></td>
  </tr>
   </logic:iterate>
  </table>
      <br>
      <input type="text" name="hai">
  </body>
  </html:html>
